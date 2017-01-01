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

import static com.google.common.base.Preconditions.checkState;

final class CodeGenerator {
    private CodeGenerator() {}

    static Jvm run(Ast ast, ErrorListener errorListener) {
        return new Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static final class Visitor implements AstVisitor<JvmNode> {
        private final ErrorListener errorListener;
        private JvmClass clazz;
        private int functionNestLevel = 0;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        @Override
        public JvmNode visit(AstName node) {
            // TODO: Support module variables
            if (node.entity.isLocal()) {
                return new JvmGetLocal(((AstVariable) node.entity).index);
            } else {
                throw new UnsupportedOperationException("non-local entity");
            }
        }

        @Override
        public JvmNode visit(AstField node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstModule node) {
            if (node.fileName == null) {
                errorListener.error("module file name is empty");
            }
            String className = Files.getNameWithoutExtension(node.fileName);
            if (!Util.isName(className)) {
                errorListener.error("file name must be java identifier: " + className);
            }
            clazz = new JvmClass(AccessModifier.PUBLIC, className);
            for (AstNode v : node.variables) {
                clazz.addVariable((JvmVariable) accept(v));
            }
            for (AstNode f : node.functions) {
                clazz.addFunction((JvmFunction) accept(f));
            }

            JvmPackage p = new JvmPackage(node.name);
            p.addImport("owl.runtime.RT");
            p.addClass(clazz);
            return p;
        }

        @Override
        public JvmNode visit(AstFunction node) {
            node.indexLocals();
            functionNestLevel++;
            JvmFunction f = new JvmFunction(
                    node,
                    node.getName().equals("main")? AccessModifier.PUBLIC: AccessModifier.PACKAGE,
                    MemoryModifier.STATIC,
                    (JvmBlock) accept(node.getBlock()));
            functionNestLevel--;
            return f;
        }

        @Override
        public JvmNode visit(AstVariable node) {
            if (functionNestLevel == 0) {
                // TODO: Generate initializer block
                return new JvmVariable(
                        node,
                        AccessModifier.PACKAGE,
                        MemoryModifier.STATIC,
                        null);
            } else {
                return new JvmGroup(Util.listOf(
                        accept(node.getExpr()),
                        new JvmPutLocal(node.index)));
            }
        }

        @Override
        public JvmNode visit(AstBlock node) {
            JvmBlock b = new JvmBlock();
            for (AstNode c : node.children) {
                b.add(accept(c));
            }
            return b;
        }

        @Override
        public JvmNode visit(AstApply node) {
            AstName fnName = (AstName) node.fn;
            if (Util.isIdFirstChar(fnName.name.charAt(0))) {
                JvmGroup g = new JvmGroup();
                for (AstNode a : node.args) {
                    g.add(accept(a));
                }
                g.add(new JvmApply(
                        Util.isEmpty(fnName.entity.getModuleName()) ? "RT" : null,
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
                            return new JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    new JvmOperator(1, fnName.name, node.getType())));

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
                            return new JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    accept(node.args.get(1)),
                                    new JvmOperator(2, fnName.name, node.getType())));

                        case "<":
                        case "<=":
                        case ">":
                        case ">=":
                        case "==":
                        case "!=": {
                            JvmGroup g = new JvmGroup();
                            g.add(accept(node.args.get(0)));
                            g.add(accept(node.args.get(1)));
                            AstType tl = AstType.ofNode(node.args.get(0));
                            AstType tr = AstType.ofNode(node.args.get(1));
                            if (tl.equals(AstType.STRING) && tr.equals(AstType.STRING)) {
                                g.add(new JvmApply("RT", "compare", 2, AstType.I32));
                                g.add(new JvmLiteral("0", AstType.I32));
                            }
                            g.add(new JvmOperator(2, fnName.name, node.getType()));
                            return g;
                        }

                        default:
                            throw new IllegalStateException("unknown operator " + fnName.name);
                    }

                default:
                    throw new IllegalStateException("invalid operator arity");
            }
        }

        @Override
        public JvmNode visit(AstCoerce node) {
            JvmGroup g = new JvmGroup();
            g.add(accept(node.expr));
            if (!AstType.ofNode(node.expr).equals(node.type)) {
                g.add(new JvmCoerce(AstType.ofNode(node), node.getType()));
            }
            return g;
        }

        @Override
        public JvmNode visit(AstAssign node) {
            JvmGroup g = new JvmGroup();
            g.add(accept(node.r));
            if (node.l instanceof AstName) {
                AstName name = (AstName) node.l;
                g.add(new JvmPutLocal(((AstVariable) name.entity).index));
                return g;
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
        }

        @Override
        public JvmNode visit(AstLiteral node) {
            return new JvmLiteral(node.text, node.getType());
        }

        @Override
        public JvmNode visit(AstIf node) {
            JvmNode r = null;
            int i = node.branches.size() - 1;
            if (node.branches.get(i).condition == null) {
                r = accept(node.branches.get(i).block);
                i--;
            }
            while (i >= 0) {
                AstIf.Branch b = node.branches.get(i);
                if (r != null && !(r instanceof JvmBlock)) {
                    JvmBlock wrapIf = new JvmBlock();
                    wrapIf.add(r);
                    r = wrapIf;
                }
                r = new JvmIf(accept(b.condition), (JvmBlock) accept(b.block), (JvmBlock) r);
                i--;
            }
            return r;
        }

        @Override
        public JvmNode visit(AstReturn node) {
            JvmGroup g = new JvmGroup();
            g.add(accept(node.expr));
            g.add(new JvmReturn());
            return g;
        }

        @Override
        public JvmNode visit(AstExpr node) {
            JvmGroup g = new JvmGroup();
            g.add(accept(node.expr));
            if (node.discards()) {
                g.add(new JvmPop());
            }
            return g;
        }

        @Override
        public JvmNode visit(AstGroup node) {
            JvmGroup g = new JvmGroup();
            for (AstNode c : node.children) {
                g.add(accept(c));
            }
            return g;
        }
    }
}
