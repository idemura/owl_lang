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
            NameMap<AstAbstractType> abstractTypes,
            NameMap<Entity> variables,
            OverloadNameMap overloads,
            ErrorListener errorListener) throws OwlException {
        new Visitor(abstractTypes, variables, overloads, errorListener).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private final NameMap<AstAbstractType> abstractTypes;
        private final NestedEntityMap entityMap;
        private final Stack<AstFunction> fnStack = new Stack<>();

        private Visitor(
                NameMap<AstAbstractType> abstractTypes,
                NameMap<Entity> variables,
                OverloadNameMap overloads,
                ErrorListener errorListener) {
            this.errorListener = errorListener;
            this.abstractTypes = abstractTypes;
            this.entityMap = new NestedEntityMap(variables, overloads);
        }

        private void error(OwlException e) {
            errorListener.error(e.line, e.charPosition, e.getMessage());
        }

        @Override
        public Void visit(AstName node) throws OwlException {
            if (entityMap.isBlockVar(node.name)) {
                node.entity = entityMap.get(node.name);
            } else {
                // TODO: Support module variables
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        "name " + node.name + " not found");
            }
            return null;
        }

        @Override
        public Void visit(AstField node) throws OwlException {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstModule node) throws OwlException {
            for (AstNode f : node.variables) {
                accept(f);
            }
            for (AstNode f : node.functions) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) throws OwlException {
            entityMap.push();
            fnStack.push(node);
            for (AstVariable a : node.getArgs()) {
                try {
                    entityMap.put(a);
                } catch (OwlException e) {
                    throw new IllegalStateException("error put local");
                }
            }
            accept(node.getBlock());
            fnStack.pop();
            entityMap.pop();
            return null;
        }

        @Override
        public Void visit(AstVariable node) throws OwlException {
            accept(node.getExpr());
            if (!fnStack.isEmpty()) {
                if (entityMap.inTopBlock(node.getName())) {
                    throw new OwlException(node.getLine(), node.getCharPosition(),
                            "variable already exist in the current scope");
                }
                try {
                    entityMap.put(node);
                } catch (OwlException e) {
                    throw new IllegalStateException("error put local");
                }
                fnStack.top().addVar(node);
            }
            return null;
        }

        @Override
        public Void visit(AstBlock node) throws OwlException {
            for (AstNode s : node.children) {
                try {
                    accept(s);
                } catch (OwlException e) {
                    error(e);
                }
            }
            return null;
        }

        @Override
        public Void visit(AstApply node) throws OwlException {
            for (AstNode e : node.args) {
                accept(e);
            }
            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.fn instanceof AstName) {
                AstName fn = (AstName) node.fn;
                if (!entityMap.contains(fn.name)) {
                    // Error printed while visiting name
                    throw new OwlException(node.getLine(), node.getCharPosition(),
                            "function " + fn.name + " not found");
                }
                if (!entityMap.isFunction(fn.name)) {
                    throw new OwlException(node.getLine(), node.getCharPosition(),
                            fn.name + " is not a function");
                }
                try {
                    fn.entity = entityMap.resolve(fn.name, node.getArgTypes());
                } catch (ResolveError e) {
                    throw new OwlException(node.getLine(), node.getCharPosition(),
                            e.getMessage());
                }
                node.type = fn.entity.getType().getReturnType();
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
            return null;
        }

        @Override
        public Void visit(AstAssign node) throws OwlException {
            accept(node.l);
            accept(node.r);

            AstType lType = ((Typed) node.l).getType();
            AstType rType = ((Typed) node.r).getType();
            if (!TypeUtil.assignable(lType, rType)) {
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        rType + " not assignable to " + lType);
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
        public Void visit(AstValue node) throws OwlException {
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
        public Void visit(AstIf node) throws OwlException {
            for (AstIf.Branch b : node.branches) {
                accept(b.condition);
                accept(b.block);
            }
            return null;
        }

        @Override
        public Void visit(AstReturn node) throws OwlException {
            accept(node.expr);
            if (!TypeUtil.assignable(fnStack.top().getReturnType(), ((Typed) node.expr).getType())) {
                throw new OwlException(node.getLine(), node.getCharPosition(),
                        "return type not compatible");
            }
            return null;
        }

        @Override
        public Void visit(AstExpr node) throws OwlException {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstCast node) throws OwlException {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstGroup node) throws OwlException {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }

        @Override
        public Void visit(AstNew node) throws OwlException {
            // TODO: Resolve constructor call here
            return null;
        }
    }
}
