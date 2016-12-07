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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

final class CodeGenerator {
    private CodeGenerator() {}

    static Jvm run(Ast ast, ErrorListener errorListener) throws OwlException {
        return new Jvm(new Visitor(errorListener).accept(ast.root));
    }

    private static JvmNode javaMain() {
        JvmBlock block = new JvmBlock();
        block.add(new JvmApply(AstType.NONE, null, "owl_main", ImmutableList.of()));
        return new JvmFunction(
                AccessModifier.PUBLIC,
                MemoryModifier.STATIC,
                AstType.NONE,
                "main",
                ImmutableList.of(JvmVariable.local(AstType.arrayOf(AstType.STRING), "args")),
                block);
    }

    private static String fileNameToClassName(String fileName) throws OwlException {
        char[] chars = Files.getNameWithoutExtension(fileName).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char lower = Character.toLowerCase(chars[i]);
            int index = "abcdefghijklmnopqrstuvwxyz0123456789_".indexOf(lower);
            if (index < 0) {
                throw new OwlException("invalid character at " + (i + 1));
            }
            if (i == 0 && index >= 26) {
                throw new OwlException("must start with letter");
            }
            chars[i] = i == 0? Character.toUpperCase(chars[i]): chars[i];
        }
        return String.valueOf(chars);
    }

    private static final class Visitor implements AstVisitor<JvmNode> {
        private final ErrorListener errorListener;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        @Override
        public JvmNode visit(AstName node) {
            return new JvmValue(node.name, node.getType());
        }

        @Override
        public JvmNode visit(AstType node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstMember node) {
            throw new UnsupportedOperationException("code generator");
       }

        @Override
        public JvmNode visit(AstModule node) {
            if (node.fileName == null) {
                error(node, "module file name is empty");
                return null;
            }
            String className = null;
            try {
                className = fileNameToClassName(node.fileName);
            } catch (OwlException e) {
                error(node, "invalid file name to generate Java class name: " + e.getMessage());
            }
            JvmClass clazz = new JvmClass(AccessModifier.PUBLIC, className);
            clazz.add(javaMain());
            for (AstNode m : node.children) {
                clazz.add(accept(m));
            }

            JvmPackage p = new JvmPackage(node.name);
            p.add(new JvmComment("Generated by " + Util.getCompilerName()));
            p.add(Runtime.generate());
            p.add(clazz);
            return p;
        }

        @Override
        public JvmNode visit(AstFunction node) {
            return new JvmFunction(
                    AccessModifier.PACKAGE,
                    MemoryModifier.STATIC,
                    node.returnType,
                    node.name,
                    node.args.stream()
                            .map(a -> JvmVariable.local(a.getType(), a.name))
                            .collect(Collectors.toList()),
                    accept(node.block));
        }

        @Override
        public JvmNode visit(AstArgument node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstVariable node) {
            return new JvmVariable(
                    AccessModifier.PACKAGE,
                    MemoryModifier.STATIC,
                    node.getType(),
                    node.name);
        }

        @Override
        public JvmNode visit(AstBlock node) {
            JvmBlock block = new JvmBlock();
            for (AstNode s : node.children) {
                block.add(accept(s));
            }
            return block;
        }

        @Override
        public JvmNode visit(AstApply node) {
            AstName fnName = (AstName) node.args.get(0);
            switch (fnName.name) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "%": {
                    return new JvmBinary(
                            node.getType(),
                            fnName.name,
                            accept(node.args.get(1)),
                            accept(node.args.get(1)));
                }

                case "[]": {
                    throw new UnsupportedOperationException("array index");
                }
            }
            List<JvmNode> args = new ArrayList<>();
            for (int i = 1; i < node.args.size(); i++) {
                args.add(accept(node.args.get(i)));
            }
            return new JvmApply(
                    node.getType(),
                    fnName.entity.isRT()? Runtime.CLASS_NAME: null,
                    fnName.name,
                    args);
        }

        @Override
        public JvmNode visit(AstConstant node) {
            throw new UnsupportedOperationException("code generator");
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
            return new JvmValue(value, node.getType());
        }

        @Override
        public JvmNode visit(AstIf node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstCond node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstMatch node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstCase node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public JvmNode visit(AstReturn node) {
            return new JvmReturn(accept(node.expr));
        }

        @Override
        public JvmNode visit(AstExpr node) {
            return accept(node.expr);
        }
    }
}
