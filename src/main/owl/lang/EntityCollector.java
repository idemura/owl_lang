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

import java.util.HashMap;

// Collects module level entities (functions, variables). Checks function signature (infers argument types and checks
// no duplicates).
final class EntityCollector {
    private EntityCollector() {}

    static void run(Ast ast,
            EntityMap variables,
            OverloadEntityMap overloads,
            ErrorListener errorListener) throws OwlException {
        CountErrorListener newErrorListener = new CountErrorListener(errorListener);
        Visitor v = new Visitor(variables, overloads, newErrorListener);
        v.accept(ast.root);
        if (newErrorListener.getErrorCount() > 0) {
            throw new OwlException("metadata analysis error");
        }
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private EntityMap variables;
        private OverloadEntityMap overloads;
        private String moduleName;

        private Visitor(
                EntityMap variables,
                OverloadEntityMap overloads,
                ErrorListener errorListener) {
            this.variables = variables;
            this.overloads = overloads;
            this.errorListener = errorListener;
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        @Override
        public Void visit(AstModule node) {
            moduleName = node.name;
            for (AstNode m : node.children) {
                accept(m);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            // TODO: Lambda
            try {
                if (node.name == null) {
                    throw new OwlException("unnamed module level function");
                }
                if (variables.contains(node.name)) {
                    throw new OwlException("duplicated entity " + node.name);
                }
                if (!node.args.isEmpty()) {
                    // Deduce all arguments types
                    HashMap<String, AstArgument> arguments = new HashMap<>();
                    AstType t = AstType.NONE;
                    for (int i = node.args.size(); i > 0; ) {
                        AstArgument a = node.args.get(--i);
                        if (arguments.containsKey(a.name)) {
                            throw new OwlException("function argument " + a.name + " duplicated");
                        }
                        arguments.put(a.name, a);
                        if (a.type == AstType.NONE) {
                            if (t == AstType.NONE) {
                                throw new OwlException("function argument " + a.name + " missing type");
                            }
                            a.type = t;
                        } else {
                            t = a.type;
                        }
                    }
                }
                overloads.put(new Entity(moduleName, node.name, node.getType()));
            } catch (OwlException e) {
                error(node, e.getMessage());
            }
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            try {
                if (overloads.contains(node.name)) {
                    throw new OwlException("duplicated entity " + node.name);
                }
                variables.put(new Entity(moduleName, node.name, node.getType()));
            } catch (OwlException e) {
                error(node, e.getMessage());
            }
            return null;
        }

        @Override
        public Void visit(AstGroup node) {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }
    }
}
