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

    static boolean run(Ast ast,
            NameMap<AstAbstractType> abstractTypes,
            NameMap<Entity> variables,
            OverloadNameMap overloads,
            ErrorListener errorListener) {
        return new Visitor(abstractTypes, variables, overloads, errorListener).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor<Boolean> {
        private final ErrorListener errorListener;
        private final NameMap<AstAbstractType> abstractTypes;
        private final NestedNameMap entityMap;
        private final Stack<AstFunction> fnStack = new Stack<>();

        private Visitor(
                NameMap<AstAbstractType> abstractTypes,
                NameMap<Entity> variables,
                OverloadNameMap overloads,
                ErrorListener errorListener) {
            this.errorListener = errorListener;
            this.abstractTypes = abstractTypes;
            this.entityMap = new NestedNameMap(variables, overloads);
        }

        @Override
        public Boolean visit(AstName node) {
            // TODO: Support module variables
            if (!entityMap.isBlockVar(node.name)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "name " + node.name + " not found");
                return false;
            }
            node.entity = entityMap.get(node.name);
            return true;
        }

        @Override
        public Boolean visit(AstType node) {
            return TypeMatcher.run(node, abstractTypes, errorListener);
        }

        @Override
        public Boolean visit(AstField node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Boolean visit(AstModule node) {
            boolean res = true;
            for (AstNode v : node.variables) {
                if (!accept(v)) {
                    res = false;
                }
            }
            for (AstNode f : node.functions) {
                if (!accept(f)) {
                    res = false;
                }
            }
            return res;
        }

        @Override
        public Boolean visit(AstFunction node) {
            entityMap.push();
            fnStack.push(node);
            boolean res = true;
            for (AstVariable a : node.getArgs()) {
                if (!accept(a.getType())) {
                    res = false;
                }
                if (!entityMap.put(a))  {
                    throw new IllegalStateException("error put local");
                }
            }
            if (!res) {
                return false;
            }
            if (!accept(node.getReturnType())) {
                return false;
            }
            res = accept(node.getBlock());
            fnStack.pop();
            entityMap.pop();
            return res;
        }

        @Override
        public Boolean visit(AstVariable node) {
            if (!accept(node.getExpr())) {
                return false;
            }
            node.setType(((Typed) node.getExpr()).getType());
            if (!fnStack.isEmpty()) {
                if (entityMap.inTopBlock(node.getName())) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "variable already exist in the current scope");
                    return false;
                }
                if (!entityMap.put(node)) {
                    throw new IllegalStateException("error put local");
                }
                fnStack.top().addVar(node);
            }
            return true;
        }

        @Override
        public Boolean visit(AstBlock node) {
            boolean res = true;
            for (AstNode s : node.children) {
                if (!accept(s)) {
                    res = false;
                }
            }
            return res;
        }

        @Override
        public Boolean visit(AstApply node) {
            boolean res = true;
            for (AstNode e : node.args) {
                if (!accept(e)) {
                    res = false;
                }
            }
            if (!res) {
                return false;
            }

            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.fn instanceof AstName) {
                AstName fn = (AstName) node.fn;
                if (!entityMap.contains(fn.name)) {
                    // Error printed while visiting name
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "function " + fn.name + " not found");
                    return false;
                }
                if (!entityMap.isFunction(fn.name)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            fn.name + " is not a function");
                    return false;
                }
                ResolveResult rr = entityMap.resolve(fn.name, node.getArgTypes());
                if (!rr.ok()) {
                    List<Entity> candidates = rr.getCandidates();
                    String s;
                    if (candidates == null) {
                        s = "function " + fn.name + " not found";
                    } else if (candidates.size() == 0) {
                        s = "function " + fn.name + " no candidates";
                    } else {
                        s = "ambiguous overload " + fn.name + "; candidates:\n" + Util.join("\n", candidates);
                    }
                    errorListener.error(node.getLine(), node.getCharPosition(), s);
                    return false;
                }
                fn.entity = rr.get();
                node.type = fn.entity.getType().getReturnType();
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
            return true;
        }

        @Override
        public Boolean visit(AstAssign node) {
            boolean b1 = accept(node.l);
            boolean b2 = accept(node.r);
            if (!(b1 && b2)) {
                return false;
            }

            AstType lType = ((Typed) node.l).getType();
            AstType rType = ((Typed) node.r).getType();
            if (!TypeUtil.assignable(lType, rType)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        rType + " not assignable to " + lType);
                return false;
            }

            // TODO: This is basically LValue check
            if (node.l instanceof AstName) {
                // Pass
            } else {
                throw new UnsupportedOperationException("assign left op is not a name");
            }
            return true;
        }

        @Override
        public Boolean visit(AstValue node) {
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
            return accept(node.type);
        }

        @Override
        public Boolean visit(AstIf node) {
            boolean res = true;
            for (AstIf.Branch b : node.branches) {
                if (!accept(b.condition)) {
                    res = false;
                }
                if (!accept(b.block)) {
                    res = false;
                }
            }
            return res;
        }

        @Override
        public Boolean visit(AstReturn node) {
            if (!accept(node.expr)) {
                return false;
            }
            if (!TypeUtil.assignable(fnStack.top().getReturnType(), ((Typed) node.expr).getType())) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "return type not compatible");
                return false;
            }
            return true;
        }

        @Override
        public Boolean visit(AstExpr node) {
            return accept(node.expr);
        }

        @Override
        public Boolean visit(AstCast node) {
            boolean b1 = accept(node.expr);
            boolean b2 = accept(node.type);
            return b1 && b2;
        }

        @Override
        public Boolean visit(AstGroup node) {
            boolean res = true;
            for (AstNode c : node.children) {
                if (!accept(c)) {
                    res = false;
                }
            }
            return res;
        }

        @Override
        public Boolean visit(AstNew node) {
            // TODO: Resolve constructor call here
            throw new UnsupportedOperationException("new");
        }
    }
}
