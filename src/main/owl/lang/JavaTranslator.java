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

import java.io.OutputStream;
import java.io.PrintStream;

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
        public String visit(AstName n) {
            return n.name;
        }

        @Override
        public String visit(AstType n) {
            if (n.name.equals("Array")) {
                return accept(n.args.get(0)) + "[]";
            }
            if (n.args.size() > 0) {
                throw new UnsupportedOperationException("java type name on generic");
            }
            if (n.equals(AstType.BOOL)) {
                return "boolean";
            } else if (n.equals(AstType.CHAR)) {
                return "char";
            } else if (n.equals(AstType.I32)) {
                return "int";
            } else if (n.equals(AstType.I64)) {
                return "long";
            } else if (n.equals(AstType.F32)) {
                return "float";
            } else if (n.equals(AstType.F64)) {
                return "double";
            } else if (n.equals(AstType.STRING)) {
                return "String";
            } else if (n.equals(AstType.NONE)) {
                return "void";
            } else {
                return accept(n.name);
            }
        }
    }

    private static String javaAccessModifier(JvmAccessModifier m) {
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

    private static String javaMemoryModifier(JvmMemoryModifier m) {
        switch (m) {
            case STATIC:
                return "static";
            case LOCAL:
            case MEMBER:
                return "";
        }
        throw new IllegalStateException("JvmMemoryModifier " + m);
    }

    private static final class Visitor implements JvmVisitor {
        private final IndentPrinter printer;

        private Visitor(IndentPrinter printer) {
            this.printer = printer;
        }

        @Override
        public Void visit(JvmPackage n) {
            printer.printlnAll("package", n.name, ";");
            for (JvmNode child : n.getChildren()) {
                accept(child);
            }
            return null;
        }

        @Override
        public Void visit(JvmClass n) {
            printer.printlnAll(javaAccessModifier(n.access), "class", n.name).curlyOpen();
            for (JvmNode child : n.getChildren()) {
                accept(child);
            }
            printer.curlyClose();
            return null;
        }

        @Override
        public Void visit(JvmFunction n) {
            printer.printlnAll(
                    javaAccessModifier(n.access),
                    javaMemoryModifier(n.memory),
                    javaTypeName(n.returnType),
                    n.name, "(");
            printer.indent();
            printer.indent();
            boolean first = true;
            for (JvmVariable v : n.args) {
                printer.printlnAll(first? "": ",", v.type, n.name);
                first = false;
            }
            printer.unindent();
            printer.unindent();
            printer.printlnAll(")").curlyOpen();
            printer.curlyClose();
            return null;
        }

        @Override
        public Void visit(JvmVariable n) {
            printer.printlnAll(
                    javaAccessModifier(n.access),
                    javaMemoryModifier(n.memory),
                    javaTypeName(n.type));
            // TODO: Init expression generation
            return null;
        }

        @Override
        public Void visit(JvmValue n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmApply n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmBinary n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmBlock n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmReturn n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmComment n) {
            printer.printlnAll("//", n.text);
            return null;
        }
    }
}
