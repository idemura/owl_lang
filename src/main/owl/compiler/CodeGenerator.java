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

import com.google.common.io.Files;

import java.util.ArrayList;
import java.util.List;

final class CodeGenerator {
    private CodeGenerator() {}

    static Jvm run(Ast ast, ErrorListener errorListener) {
        return new Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static int getInstrType(AstType type) {
        if (type == AstType.BOOL) {
            return InstrType.kB;
        }
        if (type == AstType.CHAR) {
            return InstrType.kC;
        }
        if (type == AstType.I32) {
            return InstrType.kI;
        }
        if (type == AstType.I64) {
            return InstrType.kL;
        }
        if (type == AstType.F32) {
            return InstrType.kF;
        }
        if (type == AstType.F64) {
            return InstrType.kD;
        }
        return InstrType.kR;
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
            AstVariable v = (AstVariable) node.entity;
            if (node.entity.getModuleName() == null) {
                return new JvmGetLocal(v.index);
            } else {
                return new JvmGetField(clazz.name, v.getName(), v.getType().jvmType());
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
            clazz = new JvmClass(true, node.name, className);
            for (AstNode v : node.variables) {
                clazz.addVariable(accept(v));
            }
            for (AstNode f : node.functions) {
                clazz.addFunction(accept(f));
            }
            return clazz;
        }

        @Override
        public JvmNode visit(AstFunction node) {
            node.indexLocals();
            functionNestLevel++;
            boolean fpublic = node.getName().equals("main");
            List<JvmFunction.Var> locals = new ArrayList<>();
            for (AstVariable a : node.getArgs()) {
                locals.add(new JvmFunction.Var(a.index, getInstrType(a.getType())));
            }
            for (AstVariable v : node.getVars()) {
                locals.add(new JvmFunction.Var(v.index, getInstrType(v.getType())));
            }
            JvmBlock block = (JvmBlock) accept(node.getBlock());
            if (node.getReturnType() == AstType.NONE && !(Util.last(node.getBlock().children) instanceof AstReturn)) {
                block.add(new JvmReturn(InstrType.kV));
            }
            JvmFunction f = new JvmFunction(fpublic, true,
                    node.getName(),
                    node.getJvmDescriptor(),
                    locals,
                    block);
            functionNestLevel--;
            return f;
        }

        @Override
        public JvmNode visit(AstVariable node) {
            if (functionNestLevel == 0) {
                JvmGroup g = new JvmGroup();
                g.add(new JvmVariable(false, true, node.getName(), node.getJvmDescriptor(), null));
                if (node.getExpr() != null) {
                    JvmBlock b = new JvmBlock();
                    b.add(accept(node.getExpr()));
                    b.add(new JvmPutField(clazz.name, node.getName(), node.getType().jvmType()));
                    g.add(b);
                }
                return g;
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
                        Util.isEmpty(fnName.entity.getModuleName()) ? Runtime.NAME : clazz.name,
                        fnName.name,
                        fnName.entity.getJvmDescriptor()));
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
                            return new JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    new JvmOp(1, fnName.name, node.getType().jvmType())));

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
                                    new JvmOp(2, fnName.name, node.getType().jvmType())));

                        case "//":
                            return new JvmGroup(Util.listOf(
                                    accept(node.args.get(0)),
                                    accept(node.args.get(1)),
                                    new JvmApply(Runtime.NAME, "fdiv", node.getType().jvmType())));

                        case "<":
                        case "<=":
                        case ">":
                        case ">=":
                        case "==":
                        case "!=": {
                            JvmGroup g = new JvmGroup();
                            g.add(accept(node.args.get(0)));
                            g.add(accept(node.args.get(1)));
                            AstType tl = AstType.of(node.args.get(0));
                            AstType tr = AstType.of(node.args.get(1));
                            if (tl.equals(AstType.STRING) && tr.equals(AstType.STRING)) {
                                g.add(new JvmApply(Runtime.NAME, "compare", "(II)I"));
                                g.add(new JvmLiteral(0, Jvm.I32));
                            }
                            g.add(new JvmOp(2, fnName.name, node.getType().jvmType()));
                            return g;
                        }

                        case "&&": {
                            return new JvmIf(
                                    accept(node.args.get(0)),
                                    JvmBlock.of(new JvmIf(accept(node.args.get(1)),
                                            JvmBlock.of(JvmLiteral.TRUE),
                                            JvmBlock.of(JvmLiteral.FALSE))),
                                    JvmBlock.of(JvmLiteral.FALSE));
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
            if (AstType.of(node.expr).equals(node.type)) {
                return g;
            }
            int kind = -1;
            AstType sType = AstType.of(node.expr);
            AstType dType = node.getType();
            if (sType == AstType.I32) {
                if (dType == AstType.I64) {
                    kind = JvmCoerce.I2L;
                }
            } else if (sType == AstType.I64) {
                if (dType == AstType.I32) {
                    kind = JvmCoerce.L2I;
                }
            } else {
                throw new UnsupportedOperationException("invalid coerce");
            }
            g.add(new JvmCoerce(kind, dType.jvmType()));
            return g;
        }

        @Override
        public JvmNode visit(AstAssign node) {
            JvmGroup g = new JvmGroup();
            g.add(accept(node.r));
            if (node.l instanceof AstName) {
                AstName name = (AstName) node.l;
                AstVariable v = (AstVariable) name.entity;
                if (v.getModuleName() == null) {
                    g.add(new JvmPutLocal(v.index));
                } else {
                    g.add(new JvmPutField(clazz.name, v.getName(), v.getType().jvmType()));
                }
                return g;
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
        }

        @Override
        public JvmNode visit(AstLiteral node) {
            return new JvmLiteral(node.object, node.getType().jvmType());
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
            g.add(new JvmReturn(getInstrType(AstType.of(node.expr))));
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
