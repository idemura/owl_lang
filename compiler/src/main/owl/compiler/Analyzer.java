package owl.compiler;

import owl.compiler.AstFor.ForBoolean;
import owl.compiler.AstFor.ForRange;

import java.util.List;

// Check types of function applications. Resolves entity names and function overloads.
final class Analyzer {
    private Analyzer() {}

    static boolean run(Ast ast,
            NameMap<AstAbstractType> abstractTypes,
            NameMap<Entity> variables,
            OverloadNameMap overloads,
            ErrorListener errorListener) {
        return ast.accept(new Visitor(abstractTypes, variables, overloads, errorListener)) == 0;
    }

    private static final class Visitor implements AstVisitor<Integer> {
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
        public Integer visit(AstName node) {
            node.entity = nameMap.get(node.name);
            if (node.entity == null) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "name " + node.name + " not found");
                return 1;
            }
            return 0;
        }

        @Override
        public Integer visit(AstType node) {
            return TypeResolver.run(node, abstractTypes, errorListener)? 0: 1;
        }

        @Override
        public Integer visit(AstSelect node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Integer visit(AstModule node) {
            int e = 0;
            for (AstNode v : node.variables) {
                e += accept(v);
            }
            for (AstNode f : node.functions) {
                e += accept(f);
            }
            return e;
        }

        @Override
        public Integer visit(AstFunction node) {
            nameMap.pushScopeId();
            nameMap.push();
            fnStack.push(node);
            int e = 0;
            for (AstVariable a : node.getArgs()) {
                e += accept(a.type);
                if (!nameMap.put(a))  {
                    throw new IllegalStateException("error put local");
                }
            }
            e += accept(node.getReturnType());
            if (e != 0) {
                return 1;
            }
            e += accept(node.block);
            fnStack.pop();
            nameMap.pop();
            nameMap.popScopeId();
            if (!ReturnCheck.run(node, errorListener)) {
                e++;
            }
            return e;
        }

        @Override
        public Integer visit(AstVariable node) {
            if (accept(node.expr) != 0) {
                return 1;
            }
            node.type = AstType.of(node.expr);
            if (accept(node.type) != 0) {
                Util.checkFail("variable abstract type not resolved");
            }
            Util.check(node.type.abstractType != null);
            if (node.getName() != null) {
                if (!fnStack.isEmpty()) {
                    if (nameMap.shadows(node.getName())) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "variable " + node.getName() + " shadows existing local");
                        return 1;
                    }
                    if (!nameMap.put(node)) {
                        throw new IllegalStateException("error put local");
                    }
                    fnStack.top().addVar(node);
                }
            }
            return 0;
        }

        @Override
        public Integer visit(AstBlock node) {
            if (node.scope) {
                // Function block shares scope with arguments.
                nameMap.push();
            }
            int e = 0;
            for (AstNode s : node.children) {
                e += accept(s);
            }
            if (node.scope) {
                nameMap.pop();
            }
            return e;
        }

        @Override
        public Integer visit(AstApply node) {
            int e = 0;
            for (AstNode a : node.args) {
                e += accept(a);
            }
            if (e != 0) {
                return 1;
            }

            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.fn instanceof AstName) {
                return resolveFunction(node)? 0: 1;
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
        }

        @Override
        public Integer visit(AstIndex node) {
            int e = 0;
            e += accept(node.array);
            e += accept(node.index);
            if (e != 0) {
                return 1;
            }
            if (!AstType.of(node.array).isArray() && !AstType.of(node.array).equals(AstType.STRING)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "array or string expected on the left of []");
                return 1;
            }
            if (!AstType.of(node.index).equals(AstType.I32)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "array index must be I32");
                return 1;
            }
            return 0;
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
                    s.append("call ").append(fn.name).append(" with ");
                    if (node.getArgTypes().isEmpty()) {
                        s.append("no arguments");
                    } else {
                        s.append(Util.join(", ", node.getArgTypes()));
                    }
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
        public Integer visit(AstAssign node) {
            int e = accept(node.l) + accept(node.r);
            if (e != 0) {
                return 1;
            }

            AstType lType = AstType.of(node.l);
            AstType rType = AstType.of(node.r);
            if (!rType.compatible(lType)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        rType + " not assignable to " + lType);
                return 1;
            }

            return isLValue(node.l)? 0: 1;
        }

        private boolean isLValue(AstNode node) {
            if (node instanceof AstName) {
                return true;
            }
            if (node instanceof AstIndex) {
                AstIndex index = (AstIndex) node;
                if (AstType.of(index.array).equals(AstType.STRING)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "string is immutable, can't assign to index in it");
                    return false;
                }
                return true;
            }
            errorListener.error(node.getLine(), node.getCharPosition(),
                    "left of assignment is not lvalue");
            return false;
        }

        @Override
        public Integer visit(AstLiteral node) {
            if (accept(node.type) != 0) {
                return 1;
            }
            String text = (String) node.object;
            if (node.getType().equals(AstType.I32)) {
                // TODO: Check range
                // TODO: Deduce type
                node.object = Integer.valueOf(text);
            } else if (node.getType().equals(AstType.BOOL)) {
                node.object = text.equals("true")? Boolean.TRUE: Boolean.FALSE;
            } else if (node.getType().equals(AstType.CHAR) || node.getType().equals(AstType.STRING)) {
                // TODO: Escape codes
                if (node.getType().equals(AstType.CHAR)) {
                    String s = (String) node.object;
                    if (s.length() != 1) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "char string must have length of 1");
                        return 1;
                    }
                    node.object = (int) s.charAt(0);
                }
            } else {
                Util.checkFail("unknown literal type");
            }
            return 0;
        }

        @Override
        public Integer visit(AstIf node) {
            int e = 0;
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    e += accept(b.condition);
                    if (AstType.of(b.condition) == null) {
                        e++;
                    } else if(!AstType.of(b.condition).equals(AstType.BOOL)) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "condition must be of type Bool");
                        e++;
                    }
                }
                e += accept(b.block);
            }
            return e;
        }

        @Override
        public Integer visit(AstFor node) {
            int e = 0;
            nameMap.push();
            if (node.condition instanceof ForBoolean) {
                ForBoolean cond = (ForBoolean) node.condition;
                e += accept(cond.expr);
                if (e == 0 && !AstType.of(cond.expr).equals(AstType.BOOL)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "'for' condition must be of type Bool");
                    e++;
                }
            } else {
                ForRange range = (ForRange) node.condition;
                e += accept(range.iter);  // Puts @iter into the name map.
                e += accept(range.last);
                if (e == 0) {
                    // TODO: More checks
                    if (!range.iter.type.equals(AstType.of(range.last))) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "for-range: range expression types mismatch");
                        e++;
                    }
                    if (!range.iter.type.equals(AstType.I32)) {
                        errorListener.error(node.getLine(), node.getCharPosition(),
                                "for-range: only integer allowed");
                    }
                    range.expr = new AstApply(new AstName("<"), Util.listOf(
                            new AstName(range.iter.getName()),
                            new AstName(range.last.getName())));
                    e += accept(range.expr);
                    range.increment = new AstAssign(
                            new AstName(range.iter.getName()),
                            new AstApply(new AstName("+"), Util.listOf(
                                    new AstName(range.iter.getName()),
                                    new AstLiteral("1", AstType.I32))));
                    e += accept(range.increment);
                }
            }
            e += accept(node.block);
            nameMap.pop();
            return e;
        }

        @Override
        public Integer visit(AstReturn node) {
            if (node.expr != null && accept(node.expr) != 0) {
                return 1;
            }
            AstType returnExprType = AstType.of(node.expr);
            AstType returnType = fnStack.top().getReturnType();
            if (!returnExprType.compatible(returnType)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "return type " + returnType + " is not compatible with " + returnExprType);
                return 1;
            }
            return 0;
        }

        @Override
        public Integer visit(AstExpr node) {
            return accept(node.expr);
        }

        @Override
        public Integer visit(AstCoerce node) {
            int e = accept(node.expr) + accept(node.type);
            if (e != 0) {
                return 1;
            }
            AstType exprType = AstType.of(node.expr);
            if (!exprType.canCoerceTo(node.type)) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "no coerce from " + exprType + " to " + node.type);
                return 1;
            }
            return 0;
        }

        @Override
        public Integer visit(AstGroup node) {
            int e = 0;
            for (AstNode c : node.children) {
                e += accept(c);
            }
            return e;
        }

        @Override
        public Integer visit(AstNew node) {
            int e = 0;
            for (AstNode n : node.init) {
                e += accept(n);
            }
            if (e != 0) {
                return 1;
            }
            if (node.type.isFunction()) {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "new of a function");
                return 1;
            }
            if (node.type.isArray()) {
                AstType elemType = node.type.args.get(0);
                if (elemType.equals(AstType.NONE)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "array of time None is invalid");
                    return 1;
                }
                if (elemType.isArray()) {
                    throw new UnsupportedOperationException("new");
                }
                if (elemType.isFunction()) {
                    throw new UnsupportedOperationException("new");
                }
                if (node.init.size() != 1) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "array init must have one argument");
                    return 1;
                }
                AstType sizeType = AstType.of(node.init.get(0));
                if (!sizeType.equals(AstType.I32)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "array init must I32, provided " + sizeType);
                    return 1;
                }
                return 0;
            } else {
                errorListener.error(node.getLine(), node.getCharPosition(),
                        "new of non-array is not supported");
                return 1;
            }
        }
    }
}
