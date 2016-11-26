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


public class Analyzer {
    static class Context {
        HashMap<String, AstNode> nameMap;

        void printNameMap(PrintStream out) {
            for (String name : nameMap.keySet()) {
                out.println(name);
            }
        }
    }

    static Context analyze(Ast ast, ErrorListener errorListener) throws OwlException {
        return (new Analyzer(errorListener)).run(ast);
    }

    private ErrorListener errorListener;
    private int errorCount = 0;
    private HashMap<String, AstNode> nameMap = new HashMap<>();

    private Analyzer(ErrorListener listener) {
        this.errorListener = listener;
    }

    private void error(AstNode n, String msg) {
        errorListener.error(n.line, n.charPositionInLine, msg);
        errorCount++;
    }

    private Context run(Ast ast) throws OwlException {
        AstVisitor v = new Visitor();
        ast.module.accept(v);
        if (errorCount > 0) {
            throw new OwlException("analysis failed");
        }
        Context ctx = new Context();
        ctx.nameMap = nameMap;
        return ctx;
    }

    private final class Visitor implements AstVisitor {
        @Override
        public void visit(AstName n) {
        }

        @Override
        public void visit(AstType n) {
        }

        @Override
        public void visit(AstMember n) {
        }

        @Override
        public void visit(AstModule n) {
            for (AstNode m : n.members) {
                m.accept(this);
            }
        }

        @Override
        public void visit(AstFunction n) {
            // TODO: Lambda
            if (!n.args.isEmpty()) {
                HashMap<String, AstArgument> arguments = new HashMap<>();
                AstType t = AstType.None;
                for (int i = n.args.size(); i > 0; ) {
                    AstArgument a = n.args.get(--i);
                    if (arguments.containsKey(a.name)) {
                        error(n, "function " + n.name + " argument " + a.name + " duplicated, first at line " +
                                arguments.get(a.name).line);
                        continue;
                    }
                    arguments.put(a.name, a);
                    if (a.type == AstType.None) {
                        if (t == AstType.None) {
                            error(n, "function " + n.name + " argument " + a.name + " type None");
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
            } else {
                if (nameMap.containsKey(n.name)) {
                    errorListener.error(n.line, n.charPositionInLine, "duplicated module member " + n.name);
                } else {
                    nameMap.put(n.name, n);
                }
            }
        }

        @Override
        public void visit(AstArgument n) {
        }

        @Override
        public void visit(AstVariable n) {
            if (nameMap.containsKey(n.name)) {
                errorListener.error(n.line, n.charPositionInLine, "duplicated module member " + n.name);
            } else {
                nameMap.put(n.name, n);
            }
        }

        @Override
        public void visit(AstBlock n) {
        }

        @Override
        public void visit(AstApply n) {
        }

        @Override
        public void visit(AstConstant n) {
        }

        @Override
        public void visit(AstIf n) {
        }

        @Override
        public void visit(AstMatch n) {
        }

        @Override
        public void visit(AstReturn n) {
        }

        @Override
        public void visit(AstExpr n) {
        }
    }
}
