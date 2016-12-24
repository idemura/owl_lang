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

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import static com.google.common.base.Preconditions.checkState;

final class CodeGenerator {
    private CodeGenerator() {}

    static Jvm run(Ast ast, ErrorListener errorListener) throws OwlException {
        return new Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static JvmFunction javaMain() {
        JvmBlock block = new JvmBlock();
        block.add(new JvmApply(null, "owl_main", 0, Type.NONE));
        return new JvmFunction(
                AccessModifier.PUBLIC,
                MemoryModifier.STATIC,
                Type.NONE,
                "main",
                ImmutableList.of(new Entity(null, "argv", Type.arrayOf(Type.STRING))),
                1,  // numArgs
                block);
    }

    private static final class Visitor implements AstVisitor<JvmNode> {
        private final ErrorListener errorListener;
        private JvmClass clazz;
        private final Stack<JvmFunction> fnStack = new Stack<>();

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        private void addInstruction(JvmNode instr) {
            fnStack.top().block.add(instr);
        }

        @Override
        public JvmNode visit(AstName node) {
            // TODO: Support module variables
            if (node.entity.isBlockVar()) {
                addInstruction(new JvmGetLocal(node.entity.getIndex()));
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
                error(node, "module file name is empty");
                return null;
            }
            String className = Files.getNameWithoutExtension(node.fileName);
            if (!Util.isName(className)) {
                error(node, "invalid file name to generate Java class name: " + className);
            }
            clazz = new JvmClass(AccessModifier.PUBLIC, className);
            clazz.addFunction(javaMain());
            for (AstNode m : node.children) {
                accept(m);
            }

            JvmPackage p = new JvmPackage(node.name);
            p.addImport("owl.runtime.RT");
            p.addClass(clazz);
            return p;
        }

        @Override
        public JvmNode visit(AstFunction node) {
            JvmFunction f = new JvmFunction(
                    AccessModifier.PACKAGE,
                    MemoryModifier.STATIC,
                    node.returnType,
                    node.name,
                    node.getVars(),
                    node.args.size(),
                    new JvmBlock());
            fnStack.push(f);
            accept(node.block);
            clazz.addFunction(fnStack.pop());
            return null;
        }

        @Override
        public JvmNode visit(AstVariable node) {
            if (fnStack.isEmpty()) {
                // TODO: Generate initializer block
                clazz.addVariable(new JvmVariable(
                        AccessModifier.PACKAGE,
                        MemoryModifier.STATIC,
                        node.getType(),
                        node.name,
                        null));
            } else {
                accept(node.expr);
                addInstruction(new JvmPutLocal(node.entity.getIndex()));
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
            if (!Util.startsWithLetter(fnName.name)) {
                switch (fnName.name) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "%":
                        accept(node.args.get(0));
                        accept(node.args.get(1));
                        addInstruction(new JvmBinary(fnName.name, node.getType()));
                        break;

                        // In JavaTranslator, takes left and right from the stack and puts "l[r]" back on the stack
//                    case "[]":
//                        break;

                    default:
                        throw new IllegalStateException("unknown operator " + fnName.name);
                }
                return null;
            }
            for (AstNode a : node.args) {
                accept(a);
            }
            addInstruction(new JvmApply(
                    fnName.entity.isRT()? Runtime.CLASS_NAME: null,
                    fnName.name,
                    node.args.size(),
                    node.getType()));
            return null;
        }

        @Override
        public JvmNode visit(AstCast node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstAssign node) {
            checkState(node.op == null);
            accept(node.r);
            if (node.l instanceof AstName) {
                AstName name = (AstName) node.l;
                addInstruction(new JvmPutLocal(name.entity.getIndex()));
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
            return null;
        }

        @Override
        public JvmNode visit(AstValue node) {
            String value;
            switch (node.format) {
                case OCT:
                case HEX:
                    throw new UnsupportedOperationException("oct/hex literal");
                case DEC:
                    value = node.text;
                    break;
                case STRING:
                    value = Util.quote(node.text);
                    break;
                default:
                    throw new IllegalStateException("unknown literal format " + node.format);

            }
            addInstruction(new JvmValue(value, node.getType()));
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
