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

import java.util.List;

// Check types of function applications. Resolves entity names and function overloads.
final class TypeCheckerAndEntityResolver {
    private TypeCheckerAndEntityResolver() {}

    static void run(Ast ast,
            EntityMap variables,
            OverloadEntityMap overloads,
            ErrorListener errorListener) throws OwlException {
        new Visitor(variables, overloads, errorListener).accept(ast.root);
    }

    private static final class Context {

    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private final NestedEntityMap entityMap;
        private String moduleName;
        private Stack<List<AstType>> applyContext = new Stack<>();
        private Stack<AstBlock> block = new Stack<>();

        private Visitor(
                EntityMap variables,
                OverloadEntityMap overloads,
                ErrorListener errorListener) {
            this.errorListener = new CountErrorListener(errorListener);
            this.entityMap = new NestedEntityMap(variables, overloads);
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        @Override
        public Void visit(AstName node) {
            if (entityMap.contains(node.name)) {
                // Should not resolve named function here, but in apply
                if (!entityMap.isFunction(node.name)) {
                    node.entity = entityMap.get(node.name);
                }
            } else {
                error(node, "name " + node.name + " not found");
            }
            return null;
        }

        @Override
        public Void visit(AstType node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMember node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstModule node) {
            moduleName = node.name;
            for (AstNode f : node.children) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            entityMap.push();
            for (AstArgument a : node.args) {
                accept(a);
            }
            accept(node.block);
            entityMap.pop();
            return null;
        }

        @Override
        public Void visit(AstArgument node) {
            try {
                entityMap.put(new Entity(null, node.name, node.getType()));
            } catch (OwlException e) {
                throw new IllegalStateException("argument " + node.name + " duplicated in " + node.name);
            }
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            block.push(node);
            for (AstNode s : node.children) {
                accept(s);
            }
            block.pop();
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            for (AstNode e : node.args) {
                accept(e);
            }
            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.args.get(0) instanceof AstName) {
                AstName fn = (AstName) node.args.get(0);
                if (!entityMap.contains(fn.name)) {
                    // Error printed while visiting name
                    return null;
                }
                if (!entityMap.isFunction(fn.name)) {
                    error(fn, fn.name + " is not a function");
                    return null;
                }
                try {
                    fn.entity = entityMap.resolve(fn.name, node.getArgTypes());
                } catch (ResolveError e) {
                    error(fn, e.getMessage());
                    return null;
                }
                node.type = fn.entity.type.returnType();
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
            return null;
        }

        @Override
        public Void visit(AstAssign node) {
            if (node.l instanceof AstName) {
                accept(node.r);
                AstName local = (AstName) node.l;
                Entity ent;
                if (entityMap.top().contains(local.name)) {
                    // TODO: What if it's a function, lambda?
                    ent = entityMap.get(local.name);
                } else {
                    ent = new Entity(null, local.name, node.r.getType());
                    try {
                        entityMap.put(ent);
                    } catch (OwlException e) {
                        // Never here
                        throw new IllegalStateException("error put local");
                    }
                    block.top().vars.add(ent);
                }
                local.entity = ent;
            } else {
                accept(node.l);
                accept(node.r);
            }
            return null;
        }

        @Override
        public Void visit(AstConstant node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstValue node) {
            switch (node.format) {
                case DEC:
                case HEX:
                case OCT:
                    node.type = AstType.I32;
                    break;
                case STRING:
                    node.type = AstType.STRING;
                    break;
                default:
                    throw new IllegalStateException("unknown literal format " + node.format);
            }
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            for (AstNode n : node.children) {
                accept(n);
            }
            return null;
        }

        @Override
        public Void visit(AstCond node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMatch node) {
            for (AstNode n : node.children) {
                accept(n);
            }
            return null;
        }

        @Override
        public Void visit(AstCase node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstReturn node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            accept(node.expr);
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
