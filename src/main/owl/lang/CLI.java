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
    static class ErrorListener extends BaseErrorListener {
        private String fileName;
        int errorCount = 0;

        ErrorListener(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol,
                int line,
                int charPositionInLine,
                String msg,
                RecognitionException e) {
            error(line, charPositionInLine, msg);
        }

        void error(
                int line,
                int charPositionInLine,
                String msg) {
            String position = null;
            if (line > 0) {
                position = String.valueOf(line);
            }
            if (msg == null) {
                msg = "unknown";
            }
            print(position, "error: " + msg);
            errorCount++;
        }

        private void print(String position, String text) {
            String fileWithPosition = fileName;
            if (position != null) {
                fileWithPosition += ":" + position;
            }
            System.err.println(fileWithPosition + ": " + text);
        }
    }

    @Parameter(description = "Owl Files")
    private List<String> files = new ArrayList<>();
    @Parameter(names={"--print_ast"}, description="Print AST")
    int flagPrintAst = 0;
    @Parameter(names={"--analyze"}, description="Analyze semantics")
    int flagAnalyze = 1;

    public static void main(String[] args) {
        CLI cli = new CLI();
        new JCommander(cli, args);
        cli.run();
    }

    private void run() {
        int succeeded = 0, total = 0;
        for (String fileName : files) {
            ErrorListener errorListener = new ErrorListener(fileName);
            try (InputStream in = new FileInputStream(new File(fileName))) {
                analyze(parse(new ANTLRInputStream(in), errorListener));
            } catch (IOException | RecognitionException | OwlException e) {
                errorListener.error(0, 0, e.getMessage());
            }

            if (errorListener.errorCount == 0) {
                succeeded++;
            }
            total++;
        }
        System.exit(succeeded != total ? 1 : 0);
    }

    private Ast parse(CharStream in, ANTLRErrorListener errorListener) throws RecognitionException {
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

    private void analyze(Ast ast) throws OwlException {
        if (flagPrintAst != 0) {
            ast.accept(new DebugPrintVisitor());
        }
        if (flagAnalyze != 0) {
            Analyzer.analyze(ast);
        }
    }
}
