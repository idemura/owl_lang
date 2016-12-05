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

import com.google.common.collect.Lists;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

final class JavaTranslator implements JvmTranslator {
    JavaTranslator() {}

    @Override
    public void translate(Jvm jvm, OutputStream out) {
        new Visitor(new IndentPrinter(new PrintStream(out))).accept(jvm.root);
    }

    private static String javaTypeName(AstType type) {
        return new JavaTypeNameVisitor().accept(type);
    }

    private static final class JavaTypeNameVisitor implements AstVisitor<String> {
        @Override
        public String visit(AstName node) {
            return node.name;
        }

        @Override
        public String visit(AstType node) {
            if (node.name.equals(AstName.ARRAY)) {
                return accept(node.args.get(0)) + "[]";
            }
            if (node.args.size() > 0) {
                throw new UnsupportedOperationException("java type name on generic");
            }
            if (node.equals(AstType.BOOL)) {
                return "boolean";
            } else if (node.equals(AstType.CHAR)) {
                return "char";
            } else if (node.equals(AstType.I32)) {
                return "int";
            } else if (node.equals(AstType.I64)) {
                return "long";
            } else if (node.equals(AstType.F32)) {
                return "float";
            } else if (node.equals(AstType.F64)) {
                return "double";
            } else if (node.equals(AstType.STRING)) {
                return "String";
            } else if (node.equals(AstType.NONE)) {
                return "void";
            } else {
                return accept(node.name);
            }
        }
    }

    private static String javaAccessModifier(AccessModifier m) {
        switch (m) {
            case PUBLIC:
                return "public";
            case PRIVATE:
                return "private";
            case PACKAGE:
                return "";
        }
        throw new IllegalStateException("JvmAccessModifer " + m);
    }

    private static String javaMemoryModifier(MemoryModifier m) {
        switch (m) {
            case STATIC:
                return "static";
            case LOCAL:
            case MEMBER:
                return "";
        }
        throw new IllegalStateException("MemoryModifier " + m);
    }

    private static final class Visitor implements JvmVisitor {
        private final IndentPrinter printer;
        private final NameGen gen = new NameGen();
        private final List<String> names = new ArrayList<>();

        private Visitor(IndentPrinter printer) {
            this.printer = printer;
        }

        private String genTempLocal() {
            String s = gen.newName();
            names.add(s);
            return s;
        }

        private String popTempLocal() {
            int last = names.size() - 1;
            String s = names.get(last);
            names.remove(last);
            return s;
        }

        @Override
        public Void visit(JvmPackage node) {
            printer.println("package", node.name, ";");
            for (JvmNode child : node.getChildren()) {
                accept(child);
            }
            return null;
        }

        @Override
        public Void visit(JvmClass node) {
            printer.println(javaAccessModifier(node.access), "final class", node.name).curlyOpen();
            for (JvmNode child : node.getChildren()) {
                accept(child);
            }
            printer.curlyClose();
            return null;
        }

        @Override
        public Void visit(JvmFunction node) {
            printer.println(
                    javaAccessModifier(node.access),
                    javaMemoryModifier(node.memory),
                    javaTypeName(node.returnType),
                    node.name, "(");
            printer.indent();
            printer.indent();
            boolean first = true;
            for (JvmVariable v : node.args) {
                printer.println(first? "": ",", javaTypeName(v.type), v.name);
                first = false;
            }
            printer.unindent();
            printer.unindent();
            printer.println(")").curlyOpen();
            accept(node.block);
            printer.curlyClose();
            return null;
        }

        @Override
        public Void visit(JvmVariable node) {
            printer.println(
                    javaAccessModifier(node.access),
                    javaMemoryModifier(node.memory),
                    javaTypeName(node.type),
                    node.name, ";");
            // TODO: Init expression generation
            return null;
        }

        @Override
        public Void visit(JvmValue node) {
            printer.println(javaTypeName(node.type), genTempLocal(), "=", node.text, ";");
            return null;
        }

        @Override
        public Void visit(JvmApply node) {
            for (JvmNode a : node.args) {
                accept(a);
            }
            // In reverse to the right order
            List<String> temps = new ArrayList<>();
            for (int i = 0; i < node.args.size(); i++) {
                   temps.add(popTempLocal());
            }
            if (!node.returnType.equals(AstType.NONE)) {
                printer.print(
                        javaTypeName(node.returnType),
                        genTempLocal(),
                        "= ");  // Extra space before expression
            }
            printer.println(
                    (node.object == null? "": node.object + ".") + node.method,
                    "(",
                    String.join(", ", Lists.reverse(temps)),
                    ");");
            return null;
        }

        @Override
        public Void visit(JvmBinary node) {
            accept(node.l);
            accept(node.r);
            String r = popTempLocal();
            String l = popTempLocal();
            printer.println(javaTypeName(node.returnType), genTempLocal(), "=", l, node.op, r, ";");
            return null;
        }

        @Override
        public Void visit(JvmBlock node) {
            for (JvmNode s : node.getStatements()) {
                accept(s);
            }
            return null;
        }

        @Override
        public Void visit(JvmReturn node) {
            accept(node.expr);
            printer.println("return", popTempLocal(), ";");
            return null;
        }

        @Override
        public Void visit(JvmComment node) {
            printer.println("//", node.text);
            return null;
        }
    }
}
