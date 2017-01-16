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

import java.util.HashMap;
import java.util.List;

// Collects module level entities (functions, variables). Checks function signature (infers argument types and checks
// no duplicates).
final class EntityCollector {
    private EntityCollector() {}

    static boolean run(Ast ast,
            NameMap<Entity> variables,
            OverloadNameMap overloads,
            ErrorListener errorListener) {
        CountErrorListener errorCounter = new CountErrorListener(errorListener);
        return ast.accept(new Visitor(variables, overloads, errorCounter));
    }

    private static final class Visitor implements AstVisitor<Boolean> {
        private final ErrorListener errorListener;
        private NameMap<Entity> variables;
        private OverloadNameMap overloads;

        private Visitor(
                NameMap<Entity> variables,
                OverloadNameMap overloads,
                ErrorListener errorListener) {
            this.variables = variables;
            this.overloads = overloads;
            this.errorListener = errorListener;
        }

        @Override
        public Boolean visit(AstModule node) {
            boolean b1 = visitAll(node.variables);
            boolean b2 = visitAll(node.functions);
            return b1 && b2;
        }

        @Override
        public Boolean visit(AstFunction node) {
            // TODO: Lambda
            boolean res = true;
            if (node.getName() == null) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "unnamed module level function");
                res = false;
            }
            if (variables.contains(node.getName())) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "duplicated entity " + node.getName());
                res = false;
            }
            List<AstVariable> args = node.getArgs();
            if (!args.isEmpty()) {
                // Deduce all arguments types
                HashMap<String, AstVariable> argNameMap = new HashMap<>();
                AstType type = null;
                for (int i = args.size(); i > 0; ) {
                    AstVariable a = args.get(--i);
                    if (argNameMap.containsKey(a.getName())) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "function argument " + a.getName() + " duplicated");
                        res = false;
                    }
                    argNameMap.put(a.getName(), a);
                    if (a.getType() == null) {
                        if (type == null) {
                            errorListener.error(node.getLine(), node.getCharPosition(),
                                    "function argument " + a.getName() + " missing type");
                            res = false;
                        }
                        a.type = type;
                    } else {
                        type = a.getType();
                    }
                }
            }
            if (res && !overloads.put(node)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "overload with same signature " + node.getName());
                res = false;
            }
            return res;
        }

        @Override
        public Boolean visit(AstVariable node) {
            if (overloads.contains(node.getName()) || !variables.put(node.getName(), node)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "duplicated entity " + node.getName());
                return false;
            }
            return true;
        }

        @Override
        public Boolean visit(AstGroup node) {
            return visitAll(node.children);
        }

        private <T extends AstNode> Boolean visitAll(List<T> nodes) {
            boolean res = true;
            for (AstNode m : nodes) {
                if (!accept(m)) {
                    res = false;
                }
            }
            return res;
        }
    }
}
