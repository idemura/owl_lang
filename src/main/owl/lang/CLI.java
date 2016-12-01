/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package owl.lang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CLI {
    static class ParserErrorListener extends BaseErrorListener {
        private ErrorListener listener;

        ParserErrorListener(ErrorListener listener) {
            this.listener = listener;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line,
                int charPositionInLine,
                String msg,
                RecognitionException e) {
            listener.error(line, charPositionInLine, msg);
        }
    }

    @Parameter(description = "Owl Files")
    private List<String> files = new ArrayList<>();
    @Parameter(names = "--help", help = true)
    private boolean help;
    @Parameter(names = {"--print_ast"}, description = "Print AST")
    int flagPrintAst = 0;
    @Parameter(names = {"--analyze"}, description = "Analyze semantics")
    int flagAnalyze = 1;
    @Parameter(names = {"--print_symbol_map"}, description = "Print module symbol map")
    int flagPrintSymbolMap = 0;

    public static void main(String[] args) {
        CLI cli = new CLI();
        new JCommander(cli, args);
        cli.run();
    }

    private void run() {
        if (help) {
            System.out.println("owl_lang <files> [parameters...]");
            return;
        }
        int succeeded = 0, total = 0;
        for (String fileName : files) {
            ErrorListener errorListener = new ErrorListener(fileName);
            try (InputStream in = new FileInputStream(new File(fileName))) {
                Ast ast = parse(new ANTLRInputStream(in), new ParserErrorListener(errorListener));
                analyze(ast, errorListener);
            } catch (IOException | RecognitionException | OwlException e) {
                errorListener.error(0, 0, e.getMessage());
            }

            if (errorListener.getErrorCount() == 0) {
                succeeded++;
            }
            total++;
        }
        System.exit(succeeded != total ? 1 : 0);
    }

    private static Ast parse(CharStream in, ANTLRErrorListener errorListener) throws RecognitionException {
        Lexer lexer = new OwlLexer(in);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        OwlParser parser = new OwlParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        OwlParser.ModuleContext moduleContext = parser.module();
        if (moduleContext.exception != null) {
            throw moduleContext.exception;
        }
        return new Ast(moduleContext.r);
    }

    private void analyze(Ast ast, ErrorListener errorListener) throws OwlException {
        if (flagPrintAst != 0) {
            ast.accept(new DebugPrintVisitor());
        }
        if (flagAnalyze != 0) {
            MetaAnalyzer.Context ctx = MetaAnalyzer.analyze(ast, errorListener);
            if (flagPrintSymbolMap != 0) {
                ctx.printSymbolMap(System.out);
            }
        }
    }
}
