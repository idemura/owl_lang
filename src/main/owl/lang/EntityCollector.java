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

    static EntityMap run(Ast ast, ErrorListener errorListener) throws OwlException {
        CountErrorListener newErrorListener = new CountErrorListener(errorListener);
        Visitor v = new Visitor(newErrorListener);
        v.accept(ast.root);
        if (newErrorListener.getErrorCount() > 0) {
            throw new OwlException("metadata analysis error");
        }
        return v.entityMap.freeze();
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private final EntityMap entityMap = Runtime.ENTITY_MAP.clone();
        private String moduleName;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.line, node.charPositionInLine, msg);
        }

        @Override
        public Void visit(AstModule node) {
            moduleName = node.name;
            for (AstNode m : node.members) {
                accept(m);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            // TODO: Lambda
            boolean err = false;
            if (!node.args.isEmpty()) {
                HashMap<String, AstArgument> arguments = new HashMap<>();
                AstType t = AstType.NONE;
                for (int i = node.args.size(); i > 0; ) {
                    AstArgument a = node.args.get(--i);
                    if (arguments.containsKey(a.name)) {
                        error(node, "function " + node.name + " argument " + a.name + " duplicated, first at line " +
                                arguments.get(a.name).line);
                        err = true;
                        continue;
                    }
                    arguments.put(a.name, a);
                    if (a.type == AstType.NONE) {
                        if (t == AstType.NONE) {
                            error(node, "function " + node.name + " argument " + a.name + " type None");
                            err = true;
                        } else {
                            a.type = t;
                        }
                    } else {
                        t = a.type;
                    }
                }
            }
            if (node.name.isEmpty()) {
                error(node, "function unnamed");
            } else if (!err) {
                // Add only if no errors during function signature analysis
                Entity s = node.getEntity(moduleName);
                try {
                    entityMap.put(s);
                } catch (OwlException e) {
                    errorListener.error(node.line, node.charPositionInLine, "duplicated module member " + node.name);
                }
            }
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            Entity s = node.getEntity(moduleName);
            try {
                entityMap.put(s);
            } catch (OwlException e) {
                errorListener.error(node.line, node.charPositionInLine, "duplicated module member " + node.name);
            }
            return null;
        }
    }
}
