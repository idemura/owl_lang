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

// Check types of function applications. Resolves entity names and function overloads.
final class TypeCheckerAndEntityResolver {
    private TypeCheckerAndEntityResolver() {}

    static void run(Ast ast,
            EntityMap variables,
            OverloadEntityMap overloads,
            ErrorListener errorListener) throws OwlException {
        new Visitor(variables, overloads, errorListener).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private final NestedEntityMap entityMap;
        private final Stack<AstFunction> fnStack = new Stack<>();
        private final NameGen gen = new NameGen("_l_");

        private Visitor(
                EntityMap variables,
                OverloadEntityMap overloads,
                ErrorListener errorListener) {
            this.errorListener = errorListener;
            this.entityMap = new NestedEntityMap(variables, overloads);
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        @Override
        public Void visit(AstName node) {
            if (entityMap.isBlockVar(node.name)) {
                node.entity = entityMap.get(node.name);
            } else {
                // TODO: Support module variables
                error(node, "name " + node.name + " not found");
            }
            return null;
        }

        @Override
        public Void visit(AstField node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstModule node) {
            for (AstNode f : node.children) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            entityMap.push();
            gen.push();
            fnStack.push(node);
            for (AstArgument a : node.args) {
                try {
                    Entity ent = new Entity(null, a.name, a.getType());
                    node.addVar(ent);
                    entityMap.put(ent);
                } catch (OwlException e) {
                    throw new IllegalStateException("argument " + a.name + " duplicated in " + a.name);
                }
            }
            accept(node.block);
            fnStack.pop();
            gen.pop();
            entityMap.pop();
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            accept(node.expr);
            if (!fnStack.isEmpty()) {
                if (entityMap.inTopBlock(node.name)) {
                    error(node, "variable already exist in the current scope");
                    return null;
                }
                Entity ent = new Entity(null, node.name, ((Typed) node.expr).getType());
                try {
                    entityMap.put(ent);
                } catch (OwlException e) {
                    // Never here
                    throw new IllegalStateException("error put local");
                }
                node.entity = ent;
                fnStack.top().addVar(ent);
            }
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            for (AstNode s : node.children) {
                accept(s);
            }
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            for (AstNode e : node.args) {
                accept(e);
            }
            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.fn instanceof AstName) {
                AstName fn = (AstName) node.fn;
                if (!entityMap.contains(fn.name)) {
                    // Error printed while visiting name
                    error(node, "function " + fn.name + " not found");
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
            accept(node.l);
            accept(node.r);

            Type lType = ((Typed) node.l).getType();
            Type rType = ((Typed) node.r).getType();
            if (!TypeUtil.assignable(lType, rType)) {
                error(node, rType + " not assignable to " + lType);
                return null;
            }

            // TODO: This is basically LValue check
            if (node.l instanceof AstName) {
                // Pass
            } else {
                throw new UnsupportedOperationException("assign left op is not a name");
            }
            return null;
        }

        @Override
        public Void visit(AstValue node) {
            switch (node.format) {
                case DEC:
                case HEX:
                case OCT:
                    node.type = Type.I32;
                    break;
                case STRING:
                    node.type = Type.STRING;
                    break;
                default:
                    throw new IllegalStateException("unknown literal format " + node.format);
            }
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            for (AstIfBlock n : node.children) {
                accept(n.condition);
                accept(n.block);
            }
            return null;
        }

        @Override
        public Void visit(AstReturn node) {
            accept(node.expr);
            if (!TypeUtil.assignable(fnStack.top().returnType, ((Typed) node.expr).getType())) {
                error(node, "return type not compatible");
                return null;
            }
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstCast node) {
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

        @Override
        public Void visit(AstNew node) {
            // TODO: Resolve constructor call here
            return null;
        }
    }
}
