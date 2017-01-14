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
        private final NestedNameMap nameMap;
        private final Stack<AstFunction> fnStack = new Stack<>();

        private Visitor(
                NameMap<AstAbstractType> abstractTypes,
                NameMap<Entity> variables,
                OverloadNameMap overloads,
                ErrorListener errorListener) {
            this.errorListener = errorListener;
            this.abstractTypes = abstractTypes;
            this.nameMap = new NestedNameMap(variables, overloads);
        }

        @Override
        public Boolean visit(AstName node) {
            node.entity = nameMap.get(node.name);
            if (node.entity == null) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "name " + node.name + " not found");
                return false;
            }
            return true;
        }

        @Override
        public Boolean visit(AstType node) {
            return TypeMatcher.run(node, abstractTypes, errorListener);
        }

        @Override
        public Boolean visit(AstSelect node) {
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
            nameMap.push();
            fnStack.push(node);
            boolean res = true;
            for (AstVariable a : node.getArgs()) {
                if (!accept(a.getType())) {
                    res = false;
                }
                if (!nameMap.put(a))  {
                    throw new IllegalStateException("error put local");
                }
            }
            if (!accept(node.getReturnType())) {
                res = false;
            }
            if (!res) {
                return false;
            }
            res = accept(node.getBlock());
            fnStack.pop();
            nameMap.pop();
            if (!ReturnCheck.run(node, errorListener)) {
                res = false;
            }
            return res;
        }

        @Override
        public Boolean visit(AstVariable node) {
            if (!accept(node.getExpr())) {
                return false;
            }
            node.setType(AstType.of(node.getExpr()));
            if (!fnStack.isEmpty()) {
                if (nameMap.inTopBlock(node.getName())) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "variable " + node.getName() + " exists in this scope");
                    return false;
                }
                if (!nameMap.put(node)) {
                    throw new IllegalStateException("error put local");
                }
                fnStack.top().addVar(node);
            }
            return true;
        }

        @Override
        public Boolean visit(AstBlock node) {
            if (fnStack.top().getBlock() != node) {
                // Function block shares scope with arguments.
                nameMap.push();
            }
            boolean res = true;
            for (AstNode s : node.children) {
                if (!accept(s)) {
                    res = false;
                }
            }
            if (fnStack.top().getBlock() != node) {
                nameMap.pop();
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
                return resolveFunction(node);
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
        }

        private boolean resolveFunction(AstApply node) {
            AstName fn = (AstName) node.fn;
            if (node.args.size() == 2 &&
                    AstType.of(node.args.get(0)).equals(AstType.STRING) &&
                    AstType.of(node.args.get(1)).equals(AstType.STRING)) {
                switch (fn.name) {
                    case "<":
                    case "<=":
                    case ">":
                    case ">=":
                    case "==":
                    case "!=":
                        AstApply compare = new AstApply(
                                new AstName("compare"),
                                Util.listOf(node.args.get(0), node.args.get(1)));
                        resolveFunction(compare);
                        node.args = Util.listOf(compare, new AstLiteral(Integer.valueOf(0), AstType.I32));
                        break;
                }
            }

            if (!nameMap.contains(fn.name)) {
                // Error printed while visiting name
                errorListener.error(fn.getLine(), fn.getCharPosition(),
                        "function " + fn.name + " not found");
                return false;
            }
            if (!nameMap.isFunction(fn.name)) {
                errorListener.error(fn.getLine(), fn.getCharPosition(),
                        fn.name + " is not a function");
                return false;
            }
            ResolveResult res = nameMap.resolve(fn.name, node.getArgTypes());
            if (!res.found()) {
                List<Entity> candidates = res.getCandidates();
                StringBuilder s = new StringBuilder();
                if (candidates == null) {
                    s.append("function ").append(fn.name).append(" not found");
                } else {
                    s.append("call ").append(fn.name).append(" with ").append(Util.join(", ", node.getArgTypes()));
                    if (candidates.size() == 0) {
                        s.append(": overloads not found");
                    } else {
                        s.append(": ambiguous:\n").append(Util.join("\n", candidates));
                    }
                }
                errorListener.error(fn.getLine(), fn.getCharPosition(), s.toString());
                return false;
            }
            fn.entity = res.get();
            node.type = res.get().getType().getReturnType();
            return true;
        }

        @Override
        public Boolean visit(AstAssign node) {
            boolean b1 = accept(node.l);
            boolean b2 = accept(node.r);
            if (!(b1 && b2)) {
                return false;
            }

            AstType lType = AstType.of(node.l);
            AstType rType = AstType.of(node.r);
            if (!rType.canAssignTo(lType)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        rType + " not assignable to " + lType);
                return false;
            }

            // TODO: This is basically LValue check
            if (node.l instanceof AstName) {
                // Pass
            } else {
                throw new UnsupportedOperationException("assign left op must be a name");
            }
            return true;
        }

        @Override
        public Boolean visit(AstLiteral node) {
             return accept(node.type);
        }

        @Override
        public Boolean visit(AstIf node) {
            boolean res = true;
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    if (!accept(b.condition)) {
                        res = false;
                    }
                    if (AstType.of(b.condition) == null) {
                        res = false;
                    } else if(!AstType.of(b.condition).equals(AstType.BOOL)) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "condition must be of type Bool");
                        res = false;
                    }
                }
                if (!accept(b.block)) {
                    res = false;
                }
            }
            return res;
        }

        @Override
        public Boolean visit(AstReturn node) {
            if (node.expr != null && !accept(node.expr)) {
                return false;
            }
            AstType returnExprType = AstType.of(node.expr);
            AstType returnType = fnStack.top().getReturnType();
            if (!returnExprType.canAssignTo(returnType)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "return type " + returnType + " is not compatible with " + returnExprType);
                return false;
            }
            return true;
        }

        @Override
        public Boolean visit(AstExpr node) {
            return accept(node.expr);
        }

        @Override
        public Boolean visit(AstCoerce node) {
            boolean b1 = accept(node.expr);
            boolean b2 = accept(node.type);
            if (!(b1 && b2)) {
                return false;
            }
            AstType exprType = AstType.of(node.expr);
            if (!exprType.canCoerceTo(node.type)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "no coerce from " + exprType + " to " + node.type);
                return false;
            }
            return true;
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
