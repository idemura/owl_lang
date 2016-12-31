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

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public final class CLI {
    private static final class OwlANTLRErrorListener extends BaseErrorListener {
        private ErrorListener listener;

        OwlANTLRErrorListener(ErrorListener listener) {
            this.listener = listener;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingEntity,
                int line,
                int charPosition,
                String msg,
                RecognitionException e) {
            listener.error(line, charPosition, msg);
        }
    }

    @Parameter(description = "Owl Files")
    private List<String> files = new ArrayList<>();
    @Parameter(names = "--help", help = true)
    private boolean flagHelp = false;

    @Parameter(names = {"--analyze"}, description = "Analyze semantics", arity = 1)
    private boolean flagAnalyze = true;
    @Parameter(names = {"--generate"}, description = "Generate code", arity = 1)
    private boolean flagGenerate = true;
    @Parameter(names = {"--out"}, description = "Output directory")
    private String flagOut = ".owl_out";

    @Parameter(names = {"--print_ast"}, description = "Print AST")
    private boolean flagPrintAst = false;
    @Parameter(names = {"--print_desugar_ast"}, description = "Print desugared AST")
    private boolean flagPrintDesugarAst = false;
    @Parameter(names = {"--print_entity_map"}, description = "Print module entity map")
    private boolean flagPrintEntityMap = false;
    @Parameter(names = {"--echo"}, description = "Echo generated code to stdout")
    private boolean flagEcho = false;

    public static void main(String[] args) {
        System.exit(new CLI(args).run()? 0: 1);
    }

    private CLI(String[] args) {
        new JCommander(this, args);
    }

    private boolean run() {
        if (flagHelp) {
            System.out.println("owl_lang <files> [parameters...]");
            return true;
        }

        File outDir = new File(flagOut);
        if (!outDir.exists() && !outDir.mkdirs()) {
            System.err.println("failed to create directory " + outDir);
            return false;
        }

        int succeeded = 0, total = 0;
        for (String fileName : files) {
            CountErrorListener errorListener = new CountErrorListener(new PrintErrorListener(System.err, fileName));
            try (InputStream in = new FileInputStream(new File(fileName))) {
                try {
                    Ast ast = parse(in, fileName, errorListener);
                    compileAst(ast, errorListener, outDir, System.out);
                } catch (OwlException e) {
                    // Skip, error listener took care
                }
            } catch (IOException e) {
                errorListener.error(e.getMessage());
            }

            if (errorListener.getErrorCount() == 0) {
                succeeded++;
            }
            total++;
        }
        if (total == 0) {
            System.err.println("no input files");
            return false;
        }
        return total == succeeded;
    }

    private static Ast parse(InputStream in, String fileName, ErrorListener errorListener)
            throws OwlException {
        CountErrorListener errorCounter = new CountErrorListener(errorListener);
        ANTLRErrorListener antlrErrorListener = new OwlANTLRErrorListener(errorCounter);
        Lexer lexer;
        try {
            lexer = new OwlLexer(new ANTLRInputStream(in));
            lexer.removeErrorListeners();
            lexer.addErrorListener(antlrErrorListener);
        } catch (IOException e) {
            throw new OwlException(e);
        }
        OwlParser parser = new OwlParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(antlrErrorListener);
        try {
            OwlParser.ModuleContext context = parser.module();
            if (context.exception != null) {
                throw context.exception;
            }
            if (errorCounter.getErrorCount() > 0) {
                throw new OwlException("parse error");
            }
            return AstBuilder.run(context, fileName);
        } catch (RecognitionException e) {
            throw new OwlException(e);
        }
    }

    private boolean compileAst(Ast ast, CountErrorListener errorListener, File outDir, PrintStream debugOut) {
        if (flagPrintAst) {
            DebugPrint.printAst(ast,debugOut);
        }
        if (flagAnalyze) {
            NameMap<Entity> variables = new NameMap<>();
            OverloadNameMap overloads = Runtime.FUNCTIONS.clone();
            NameMap<AstAbstractType> abstractTypes = Runtime.ABSTRACT_TYPES.clone();
            if (!EntityCollector.run(ast, variables, overloads, errorListener)) {
                return false;
            }
            if (flagPrintEntityMap) {
                debugOut.println(variables.toString());
                debugOut.println(overloads.toString());
                debugOut.println(abstractTypes.toString());
            }
            Desugar.run(ast);
            if (flagPrintDesugarAst) {
                DebugPrint.printAst(ast,debugOut);
            }
            TypeCheckerAndEntityResolver.run(ast, abstractTypes, variables, overloads, errorListener);
            if (errorListener.getErrorCount() != 0) {
                return false;
            }
            if (flagGenerate) {
                Jvm jvm = CodeGenerator.run(ast, errorListener);
                if (errorListener.getErrorCount() != 0) {
                    return false;
                }
                try {
                    new JavaTranslator().translate(jvm, outDir, flagEcho ? System.out : null);
                } catch (OwlException e) {
                    errorListener.error(e);
                    return false;
                }
            }
        }
        return true;
    }
}
