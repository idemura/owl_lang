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

import java.io.PrintStream;
import java.util.HashMap;

public class MetaAnalyzer {
    static class Context {
        HashMap<Symbol, AstNode> symbolMap;

        void printSymbolMap(PrintStream out) {
            symbolMap.keySet().forEach(out::println);
        }
    }

    static Context analyze(Ast ast, ErrorListener errorListener) throws OwlException {
        return (new MetaAnalyzer(errorListener)).run(ast);
    }

    private ErrorListener errorListener;
    private int errorCount = 0;
    private HashMap<Symbol, AstNode> symbolMap = new HashMap<>();

    private MetaAnalyzer(ErrorListener listener) {
        this.errorListener = listener;
    }

    private void error(AstNode n, String msg) {
        errorListener.error(n.line, n.charPositionInLine, msg);
        errorCount++;
    }

    private Context run(Ast ast) throws OwlException {
        AstVisitor v = new NameMapVisitor();
        ast.module.accept(v);
        if (errorCount > 0) {
            throw new OwlException("meta analysis failed");
        }
        Context ctx = new Context();
        ctx.symbolMap = symbolMap;
        return ctx;
    }

    private final class NameMapVisitor implements AstVisitor {
        @Override
        public void visit(AstModule n) {
            for (AstNode m : n.members) {
                m.accept(this);
            }
        }

        @Override
        public void visit(AstFunction n) {
            // TODO: Lambda
            boolean err = false;
            if (!n.args.isEmpty()) {
                HashMap<String, AstArgument> arguments = new HashMap<>();
                AstType t = AstType.None;
                for (int i = n.args.size(); i > 0; ) {
                    AstArgument a = n.args.get(--i);
                    if (arguments.containsKey(a.name)) {
                        error(n, "function " + n.name + " argument " + a.name + " duplicated, first at line " +
                                arguments.get(a.name).line);
                        err = true;
                        continue;
                    }
                    arguments.put(a.name, a);
                    if (a.type == AstType.None) {
                        if (t == AstType.None) {
                            error(n, "function " + n.name + " argument " + a.name + " type None");
                            err = true;
                        } else {
                            a.type = t;
                        }
                    } else {
                        t = a.type;
                    }
                }
            }
            if (n.name.isEmpty()) {
                error(n, "function unnamed");
            } else if (!err) {
                // Add only if no errors during function signature analysis.
                Symbol s = n.getSymbol();
                if (symbolMap.containsKey(s)) {
                    errorListener.error(n.line, n.charPositionInLine, "duplicated module member " + n.name);
                } else {
                    symbolMap.put(s, n);
                }
            }
        }

        @Override
        public void visit(AstVariable n) {
            Symbol s = n.getSymbol();
            if (symbolMap.containsKey(s)) {
                errorListener.error(n.line, n.charPositionInLine, "duplicated module member " + n.name);
            } else {
                symbolMap.put(s, n);
            }
        }
    }
}
