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
package owl.compiler;

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

    @Parameter(names = {"--stop_phase"}, description = "Stop after phase #")
    private int flagStopPhase = 0;
    @Parameter(names = {"--out"}, description = "Output directory")
    private String flagOut = ".owl_out";

    @Parameter(names = {"--print_ast"}, description = "Print AST")
    private boolean flagPrintAst = false;
    @Parameter(names = {"--print_name_map"}, description = "Print module name map")
    private boolean flagPrintNameMap = false;
    @Parameter(names = {"--time"}, description = "Compilation time")
    private boolean flagTime = false;

    private long timeParse = 0;
    private long timeBuildAst = 0;
    private long timeAnalysis = 0;
    private long timeCodeGen = 0;

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
                    Ast ast = parse(in, errorListener);
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
            if (flagTime) {
                System.out.println("Times compiling " + fileName + ":");
                System.out.println("  parse: " + formatPerfTime(timeParse));
                System.out.println("  build ast: " + formatPerfTime(timeBuildAst));
                System.out.println("  analysis: " + formatPerfTime(timeAnalysis));
                System.out.println("  code gen: " + formatPerfTime(timeCodeGen));
                System.out.println("  total: " + formatPerfTime(timeParse + timeBuildAst + timeAnalysis + timeCodeGen));
            }
        }
        if (total == 0) {
            System.err.println("no input files");
            return false;
        }
        return total == succeeded;
    }

    private Ast parse(InputStream in, ErrorListener errorListener)
            throws OwlException {
        long start = System.nanoTime();
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
            timeParse = System.nanoTime() - start;
            start = System.nanoTime();
            Ast ast = AstBuilder.run(context);
            timeBuildAst = System.nanoTime() - start;
            return ast;
        } catch (RecognitionException e) {
            throw new OwlException(e);
        }
    }

    private boolean compileAst(Ast ast, CountErrorListener errorListener, File outDir, PrintStream debugOut) {
        if (flagPrintAst) {
            DebugPrint.printAst(ast,debugOut);
        }
        if (flagStopPhase == 1) {
            return true;
        }
        long start = System.nanoTime();
        NameMap<AstAbstractType> abstractTypes = Runtime.getAbstractTypes();
        OverloadNameMap overloads = Runtime.getFunctions();
        NameMap<Entity> variables = new NameMap<>();
        if (!EntityCollector.run(ast, variables, overloads, errorListener)) {
            return false;
        }
        if (flagPrintNameMap) {
            debugOut.println(variables.toString());
            debugOut.println(overloads.toString());
            debugOut.println(abstractTypes.toString());
        }
        TypeCheckerAndEntityResolver.run(ast, abstractTypes, variables, overloads, errorListener);
        if (errorListener.getErrorCount() != 0) {
            return false;
        }
        timeAnalysis = System.nanoTime() - start;
        if (flagStopPhase == 2) {
            return true;
        }
        start = System.nanoTime();
        try {
            BytecodeGenerator.run(ast, outDir);
        } catch (OwlException e) {
            errorListener.error(e);
            return false;
        }
        timeCodeGen = System.nanoTime() - start;
        if (flagStopPhase == 3) {
            return true;
        }
        return true;
    }

    private static String formatPerfTime(long nano) {
        return String.format("%.3f", nano / 1e9);
    }
}
