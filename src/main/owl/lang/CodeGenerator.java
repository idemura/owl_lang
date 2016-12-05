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

import java.util.ArrayList;
import java.util.List;

// Check types of function applications. Resolves entity names and function overloads.
class CodeGenerator {
    static Jvm run(Ast ast, ErrorListener errorListener) throws OwlException {
        return new Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static JvmNode javaMain() {
        JvmBlock block = new JvmBlock();
        block.add(new JvmApply(null, null, "owl_main", ImmutableList.of()));
        return new JvmFunction(
                JvmAccessModifier.PUBLIC,
                JvmMemoryModifier.STATIC,
                AstType.NONE,
                "main",
                ImmutableList.of(JvmVariable.makeLocal("args", AstType.arrayOf(AstType.STRING))),
                block);
    }

    private static final class Visitor implements AstVisitor<JvmNode> {
        private final ErrorListener errorListener;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        @Override
        public JvmNode visit(AstName n) {
            return new JvmValue(n.name);
        }

        @Override
        public JvmNode visit(AstType n) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstMember n) {
            throw new UnsupportedOperationException("code generator");
       }

        @Override
        public JvmNode visit(AstModule n) {
            if (n.fileName == null) {
                errorListener.error(0, 0, "module file name is empty");
                return null;
            }
            JvmClass clazz = new JvmClass(JvmAccessModifier.PUBLIC, n.fileName);
            clazz.add(javaMain());
            for (AstNode m : n.members) {
                clazz.add(accept(m));
            }

            JvmPackage p = new JvmPackage(n.name);
            p.add(new JvmComment("Generated by " + Utils.getCompilerName()));
            p.add(clazz);
            return p;
        }

        @Override
        public JvmNode visit(AstFunction n) {
            List<JvmVariable> args = new ArrayList<>();
            for (AstArgument a : n.args) {
                args.add(JvmVariable.makeLocal(
                        a.name,
                        a.getType()));
            }
            return new JvmFunction(
                    JvmAccessModifier.PACKAGE,
                    JvmMemoryModifier.STATIC,
                    n.returnType,
                    n.name,
                    args,
                    accept(n.block));
        }

        @Override
        public JvmNode visit(AstArgument n) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstVariable n) {
            return new JvmVariable(
                    JvmAccessModifier.PACKAGE,
                    JvmMemoryModifier.STATIC,
                    n.getType(),
                    n.name);
        }

        @Override
        public JvmNode visit(AstBlock n) {
            JvmBlock block = new JvmBlock();
            for (AstNode s : n.statements) {
                block.add(accept(s));
            }
            return block;
        }

        @Override
        public JvmNode visit(AstApply n) {
            AstName fnName = (AstName) n.args.get(0);
            List<JvmNode> args = new ArrayList<>();
            for (int i = 1; i < n.args.size(); i++) {
                args.add(accept(n.args.get(i)));
            }
            return new JvmApply(
                    n.getType(),
                    null,
                    fnName.name,
                    args);
        }

        @Override
        public JvmNode visit(AstConstant n) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstLiteral n) {
            String value;
            switch (n.format) {
                case OCT:
                case HEX:
                    throw new UnsupportedOperationException("oct/hex literal");
                case DEC:
                    value = n.text;
                    break;
                case STRING:
                    value = "\"" + n.text + "\"";
                    break;
                default:
                    throw new IllegalStateException("unknown literal format " + n.format);

            }
            return new JvmValue(value);
        }

        @Override
        public JvmNode visit(AstIf n) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstMatch n) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstReturn n) {
            return new JvmReturn(accept(n.expr));
        }

        @Override
        public JvmNode visit(AstExpr n) {
            return accept(n.expr);
        }
    }
}
