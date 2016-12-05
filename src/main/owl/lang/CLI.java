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
                Object offendingEntity,
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
    private boolean flagHelp = false;
    @Parameter(names = {"--analyze"}, description = "Analyze semantics")
    int flagAnalyze = 1;
    @Parameter(names = {"--print_ast"}, description = "Print AST")
    int flagPrintAst = 0;
    @Parameter(names = {"--print_entity_map"}, description = "Print module entity map")
    int flagPrintEntityMap = 0;
    @Parameter(names = {"--generate"}, description = "Generate code")
    int flagGenerate = 1;

    public static void main(String[] args) {
        CLI cli = new CLI();
        new JCommander(cli, args);
        cli.run();
    }

    private void run() {
        if (flagHelp) {
            System.out.println("owl_lang <files> [parameters...]");
            return;
        }
        int succeeded = 0, total = 0;
        for (String fileName : files) {
            CountErrorListener errorListener = new CountErrorListener(new PrintErrorListener(fileName));
            try (InputStream in = new FileInputStream(new File(fileName))) {
                Ast ast = parse(in, new ParserErrorListener(errorListener));
                AstModule module = ast.<AstModule>getRootAs();
                module.name = "owl_test";
                module.fileName = fileName;
                try {
                    compileAst(ast, errorListener, System.out, System.out);
                } catch (OwlException e) {
                    // Skip, error listener took care.
                }
            } catch (IOException | OwlException e) {
                errorListener.error(0, 0, e.getMessage());
            }

            if (errorListener.getErrorCount() == 0) {
                succeeded++;
            }
            total++;
        }
        System.exit(succeeded != total ? 1 : 0);
    }

    private static Ast parse(InputStream in, ANTLRErrorListener errorListener) throws OwlException {
        Lexer lexer;
        try {
            lexer = new OwlLexer(new ANTLRInputStream(in));
        } catch (IOException e) {
            throw new OwlException(e);
        }
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        OwlParser parser = new OwlParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        try {
            OwlParser.ModuleContext context = parser.module();
            if (context.exception != null) {
                throw context.exception;
            }
            return new Ast(context.r);
        } catch (RecognitionException e) {
            throw new OwlException(e);
        }
    }

    private void compileAst(Ast ast, CountErrorListener errorListener, OutputStream out, PrintStream debugOut) throws OwlException {
        if (flagPrintAst != 0) {
            DebugPrint.printAst(ast,debugOut);
        }
        if (flagAnalyze != 0) {
            EntityMap entityMap = EntityCollector.run(ast, errorListener);
            if (flagPrintEntityMap != 0) {
                entityMap.print(debugOut);
            }
            TypeCheckerAndEntityResolver.run(ast, entityMap, errorListener);
            if (errorListener.getErrorCount() == 0 && flagGenerate != 0) {
                Jvm jvm = CodeGenerator.run(ast, errorListener);
                new JavaTranslator().translate(jvm, out);
            }
        }
    }
}
