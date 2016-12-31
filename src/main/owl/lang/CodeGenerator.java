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
        private final Stack<JvmFunction> fnStack = new Stack<>();

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        private void addInstruction(JvmNode instr) {
            fnStack.top().block.add(instr);
        }

        @Override
        public JvmNode visit(AstName node) {
            // TODO: Support module variables
            if (node.entity.isLocal()) {
                addInstruction(new JvmGetLocal(((AstVariable) node.entity).index));
            } else {
                throw new UnsupportedOperationException("non-local entity");
            }
            return null;
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
            for (AstNode m : node.variables) {
                accept(m);
            }
            for (AstNode m : node.functions) {
                accept(m);
            }

            JvmPackage p = new JvmPackage(node.name);
            p.addImport("owl.runtime.RT");
            p.addClass(clazz);
            return p;
        }

        @Override
        public JvmNode visit(AstFunction node) {
            node.indexLocals();
            fnStack.push(new JvmFunction(
                    node,
                    node.getName().equals("main")? AccessModifier.PUBLIC: AccessModifier.PACKAGE,
                    MemoryModifier.STATIC,
                    new JvmBlock()));
            accept(node.getBlock());
            clazz.addFunction(fnStack.pop());
            return null;
        }

        @Override
        public JvmNode visit(AstVariable node) {
            if (fnStack.isEmpty()) {
                // TODO: Generate initializer block
                clazz.addVariable(new JvmVariable(
                        node,
                        AccessModifier.PACKAGE,
                        MemoryModifier.STATIC,
                        null));
            } else {
                accept(node.getExpr());
                addInstruction(new JvmPutLocal(node.index));
            }
            return null;
        }

        @Override
        public JvmNode visit(AstBlock node) {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }

        @Override
        public JvmNode visit(AstApply node) {
            AstName fnName = (AstName) node.fn;
            if (!Util.isIdFirstChar(fnName.name.charAt(0))) {
                switch (node.args.size()) {
                    case 1:
                        switch (fnName.name) {
                            case "+":
                            case "-":
                            case "~":
                                accept(node.args.get(0));
                                addInstruction(new JvmOperator(1, fnName.name, node.getType()));
                                break;

                            default:
                                throw new IllegalStateException("unknown operator " + fnName.name);
                        }
                        break;

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
                                accept(node.args.get(0));
                                accept(node.args.get(1));
                                addInstruction(new JvmOperator(2, fnName.name, node.getType()));
                                break;

                            case "<":
                            case "<=":
                            case ">":
                            case ">=":
                            case "==":
                            case "!=": {
                                accept(node.args.get(0));
                                accept(node.args.get(1));
                                AstType tl = TypeUtil.getType(node.args.get(0));
                                AstType tr = TypeUtil.getType(node.args.get(1));
                                if (tl.equals(AstType.STRING) && tr.equals(AstType.STRING)) {
                                    addInstruction(new JvmApply("RT", "compare", 2, AstType.I32));
                                    addInstruction(new JvmLiteral("0", AstType.I32));
                                }
                                addInstruction(new JvmOperator(2, fnName.name, node.getType()));
                                break;
                            }

                            default:
                                throw new IllegalStateException("unknown operator " + fnName.name);
                        }
                        break;

                    default:
                        throw new IllegalStateException("invalid operator arity");
                }
                return null;
            }
            for (AstNode a : node.args) {
                accept(a);
            }
            addInstruction(new JvmApply(
                    Util.isEmpty(fnName.entity.getModuleName()) ? "RT": null,
                    fnName.name,
                    node.args.size(),
                    node.getType()));
            return null;
        }

        @Override
        public JvmNode visit(AstCast node) {
            accept(node.expr);
            if (!TypeUtil.getType(node.expr).equals(node.type)) {
                addInstruction(new JvmCast(TypeUtil.getType(node), node.getType()));
            }
            return null;
        }

        @Override
        public JvmNode visit(AstAssign node) {
            checkState(node.op == null);
            accept(node.r);
            if (node.l instanceof AstName) {
                AstName name = (AstName) node.l;
                addInstruction(new JvmPutLocal(((AstVariable) name.entity).index));
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
            return null;
        }

        @Override
        public JvmNode visit(AstLiteral node) {
            addInstruction(new JvmLiteral(node.text, node.getType()));
            return null;
        }

        @Override
        public JvmNode visit(AstIf node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstReturn node) {
            accept(node.expr);
            addInstruction(new JvmReturn());
            return null;
        }

        @Override
        public JvmNode visit(AstExpr node) {
            accept(node.expr);
            if (node.discards()) {
                addInstruction(new JvmPop());
            }
            return null;
        }

        @Override
        public JvmNode visit(AstGroup node) {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }
    }
}
