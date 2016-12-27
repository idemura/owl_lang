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
import java.util.List;

// Collects module level entities (functions, variables). Checks function signature (infers argument types and checks
// no duplicates).
final class EntityCollector {
    private EntityCollector() {}

    static void run(Ast ast,
            EntityMap variables,
            OverloadEntityMap overloads,
            ErrorListener errorListener) throws OwlException {
        CountErrorListener errorCounter = new CountErrorListener(errorListener);
        Visitor v = new Visitor(variables, overloads, errorCounter);
        v.accept(ast.root);
        if (errorCounter.getErrorCount() > 0) {
            throw new OwlException("metadata analysis error");
        }
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private EntityMap variables;
        private OverloadEntityMap overloads;

        private Visitor(
                EntityMap variables,
                OverloadEntityMap overloads,
                ErrorListener errorListener) {
            this.variables = variables;
            this.overloads = overloads;
            this.errorListener = errorListener;
        }

        private void error(OwlException e) {
            errorListener.error(e.line, e.charPosition, e.getMessage());
        }

        @Override
        public Void visit(AstModule node) throws OwlException {
            visitAll(node.variables);
            visitAll(node.functions);
            return null;
        }

        @Override
        public Void visit(AstFunction node) throws OwlException {
            // TODO: Lambda
            if (node.getName() == null) {
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        "unnamed module level function");
            }
            if (variables.contains(node.getName())) {
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        "duplicated entity " + node.getName());
            }
            List<AstVariable> args = node.getArgs();
            if (!args.isEmpty()) {
                // Deduce all arguments types
                HashMap<String, AstVariable> argNameMap = new HashMap<>();
                AstType type = null;
                for (int i = args.size(); i > 0; ) {
                    AstVariable a = args.get(--i);
                    if (argNameMap.containsKey(a.getName())) {
                        throw new OwlException(node.getLine(), node.getCharPosition(),
                                "function argument " + a.getName() + " duplicated");
                    }
                    argNameMap.put(a.getName(), a);
                    if (a.getType() == null) {
                        if (type == null) {
                            throw new OwlException(node.getLine(), node.getCharPosition(),
                                    "function argument " + a.getName() + " missing type");
                        }
                        a.setType(type);
                    } else {
                        type = a.getType();
                    }
                }
            }
            overloads.put(node);
            return null;
        }

        @Override
        public Void visit(AstVariable node) throws OwlException {
            if (overloads.contains(node.getName())) {
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        "duplicated entity " + node.getName());
            }
            variables.put(node);
            return null;
        }

        @Override
        public Void visit(AstGroup node) throws OwlException {
            visitAll(node.children);
            return null;
        }

        private <T extends AstNode> void visitAll(List<T> nodes) {
            for (AstNode m : nodes) {
                try {
                    accept(m);
                } catch (OwlException e) {
                    error(e);
                }
            }
        }
    }
}
