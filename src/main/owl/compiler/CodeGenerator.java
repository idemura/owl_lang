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

import com.google.common.io.Files;

final class CodeGenerator {
    private CodeGenerator() {}

    static owl.lang.Jvm run(Ast ast, ErrorListener errorListener) {
        return new owl.lang.Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static final class Visitor implements AstVisitor<owl.lang.JvmNode> {
        private final ErrorListener errorListener;
        private owl.lang.JvmClass clazz;
        private int functionNestLevel = 0;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        @Override
        public owl.lang.JvmNode visit(AstName node) {
            AstVariable v = (AstVariable) node.entity;
            if (node.entity.getModuleName() == null) {
                return new owl.lang.JvmGetLocal(v.index);
            } else {
                return new owl.lang.JvmGetField(clazz.name, v.getName(), v.getType());
            }
        }

        @Override
        public owl.lang.JvmNode visit(AstField node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public owl.lang.JvmNode visit(AstModule node) {
            if (node.fileName == null) {
                errorListener.error("module file name is empty");
            }
            String className = Files.getNameWithoutExtension(node.fileName);
            if (!Util.isName(className)) {
                errorListener.error("file name must be java identifier: " + className);
            }
            clazz = new owl.lang.JvmClass(owl.lang.AccessModifier.PUBLIC, className);
            for (AstNode v : node.variables) {
                clazz.addVariable(accept(v));
            }
            for (AstNode f : node.functions) {
                clazz.addFunction(accept(f));
            }

            owl.lang.JvmPackage p = new owl.lang.JvmPackage(node.name);
            p.addImport("owl.runtime._RT");
            p.addClass(clazz);
            return p;
        }

        @Override
        public owl.lang.JvmNode visit(AstFunction node) {
            node.indexLocals();
            functionNestLevel++;
            owl.lang.JvmFunction f = new owl.lang.JvmFunction(
                    node,
                    node.getName().equals("main")? owl.lang.AccessModifier.PUBLIC: owl.lang.AccessModifier.PACKAGE,
                    owl.lang.MemoryModifier.STATIC,
                    (owl.lang.JvmBlock) accept(node.getBlock()));
            functionNestLevel--;
            return f;
        }

        @Override
        public owl.lang.JvmNode visit(AstVariable node) {
            if (functionNestLevel == 0) {
                owl.lang.JvmGroup g = new owl.lang.JvmGroup();
                g.add(new owl.lang.JvmVariable(
                        node,
                        owl.lang.AccessModifier.PACKAGE,
                        owl.lang.MemoryModifier.STATIC,
                        null));
                if (node.getExpr() != null) {
                    owl.lang.JvmBlock b = new owl.lang.JvmBlock();
                    b.add(accept(node.getExpr()));
                    b.add(new owl.lang.JvmPutField(clazz.name, node.getName(), node.getType()));
                    g.add(b);
                }
                return g;
            } else {
                return new owl.lang.JvmGroup(Util.listOf(
                        accept(node.getExpr()),
                        new owl.lang.JvmPutLocal(node.index)));
            }
        }

        @Override
        public owl.lang.JvmNode visit(AstBlock node) {
            owl.lang.JvmBlock b = new owl.lang.JvmBlock();
            for (AstNode c : node.children) {
                b.add(accept(c));
            }
            return b;
        }

        @Override
        public owl.lang.JvmNode visit(AstApply node) {
            AstName fnName = (AstName) node.fn;
            if (Util.isIdFirstChar(fnName.name.charAt(0))) {
                owl.lang.JvmGroup g = new owl.lang.JvmGroup();
                for (AstNode a : node.args) {
                    g.add(accept(a));
                }
                g.add(new owl.lang.JvmApply(
                        Util.isEmpty(fnName.entity.getModuleName()) ? "_RT" : null,
                        fnName.name,
                        node.args.size(),
                        node.getType()));
                return g;
            }

            // Operators
            switch (node.args.size()) {
                case 1:
                    switch (fnName.name) {
                        case "+":
                        case "-":
                        case "~":
                        case "!":
                            return new owl.lang.JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    new owl.lang.JvmOperator(1, fnName.name, node.getType())));

                        default:
                            throw new IllegalStateException("unknown operator " + fnName.name);
                    }

                case 2:
                    switch (fnName.name) {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                        case "%":
                        case "<<":
                        case ">>":
                        case ">>>":
                        case "&":
                        case "^":
                        case "|":
                            return new owl.lang.JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    accept(node.args.get(1)),
                                    new owl.lang.JvmOperator(2, fnName.name, node.getType())));

                        case "//":
                            return new owl.lang.JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    accept(node.args.get(1)),
                                    new owl.lang.JvmApply("_RT", "fdiv", 2, node.getType())));

                        case "<":
                        case "<=":
                        case ">":
                        case ">=":
                        case "==":
                        case "!=": {
                            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
                            g.add(accept(node.args.get(0)));
                            g.add(accept(node.args.get(1)));
                            AstType tl = AstType.ofNode(node.args.get(0));
                            AstType tr = AstType.ofNode(node.args.get(1));
                            if (tl.equals(AstType.STRING) && tr.equals(AstType.STRING)) {
                                g.add(new owl.lang.JvmApply("_RT", "compare", 2, AstType.I32));
                                g.add(new owl.lang.JvmLiteral("0", AstType.I32));
                            }
                            g.add(new owl.lang.JvmOperator(2, fnName.name, node.getType()));
                            return g;
                        }

                        case "&&": {
                            return new owl.lang.JvmIf(
                                    accept(node.args.get(0)),
                                    owl.lang.JvmBlock.of(new owl.lang.JvmIf(accept(node.args.get(1)),
                                            owl.lang.JvmBlock.of(owl.lang.JvmLiteral.TRUE),
                                            owl.lang.JvmBlock.of(owl.lang.JvmLiteral.FALSE))),
                                    owl.lang.JvmBlock.of(owl.lang.JvmLiteral.FALSE));
                        }

                        default:
                            throw new IllegalStateException("unknown operator " + fnName.name);
                    }

                default:
                    throw new IllegalStateException("invalid operator arity");
            }
        }

        @Override
        public owl.lang.JvmNode visit(AstCoerce node) {
            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
            g.add(accept(node.expr));
            if (!AstType.ofNode(node.expr).equals(node.type)) {
                g.add(new owl.lang.JvmCoerce(AstType.ofNode(node), node.getType()));
            }
            return g;
        }

        @Override
        public owl.lang.JvmNode visit(AstAssign node) {
            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
            g.add(accept(node.r));
            if (node.l instanceof AstName) {
                AstName name = (AstName) node.l;
                AstVariable v = (AstVariable) name.entity;
                if (v.getModuleName() == null) {
                    g.add(new owl.lang.JvmPutLocal(v.index));
                } else {
                    g.add(new owl.lang.JvmPutField(clazz.name, v.getName(), v.getType()));
                }
                return g;
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
        }

        @Override
        public owl.lang.JvmNode visit(AstLiteral node) {
            return new owl.lang.JvmLiteral(node.text, node.getType());
        }

        @Override
        public owl.lang.JvmNode visit(AstIf node) {
            owl.lang.JvmNode r = null;
            int i = node.branches.size() - 1;
            if (node.branches.get(i).condition == null) {
                r = accept(node.branches.get(i).block);
                i--;
            }
            while (i >= 0) {
                AstIf.Branch b = node.branches.get(i);
                if (r != null && !(r instanceof owl.lang.JvmBlock)) {
                    owl.lang.JvmBlock wrapIf = new owl.lang.JvmBlock();
                    wrapIf.add(r);
                    r = wrapIf;
                }
                r = new owl.lang.JvmIf(accept(b.condition), (owl.lang.JvmBlock) accept(b.block), (owl.lang.JvmBlock) r);
                i--;
            }
            return r;
        }

        @Override
        public owl.lang.JvmNode visit(AstReturn node) {
            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
            g.add(accept(node.expr));
            g.add(new owl.lang.JvmReturn());
            return g;
        }

        @Override
        public owl.lang.JvmNode visit(AstExpr node) {
            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
            g.add(accept(node.expr));
            if (node.discards()) {
                g.add(new owl.lang.JvmPop());
            }
            return g;
        }

        @Override
        public owl.lang.JvmNode visit(AstGroup node) {
            owl.lang.JvmGroup g = new owl.lang.JvmGroup();
            for (AstNode c : node.children) {
                g.add(accept(c));
            }
            return g;
        }
    }
}
