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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

final class JavaTranslator implements JvmTranslator {
    JavaTranslator() {}

    @Override
    public void translate(Jvm jvm, File outDir, PrintStream echo) throws OwlException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Visitor v = new Visitor(outDir, new IndentPrinter(new PrintStream(bos), echo));
            v.accept(jvm.root);
            try (OutputStream fos = new FileOutputStream(v.outJavaCode)) {
                fos.write(bos.toByteArray());
            }
        } catch (IOException e) {
            throw new OwlException(e);
        }
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
            if (node.isArray()) {
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
                return node.name;
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
        private final NameGen gen = new NameGen("_t_");
        private final Stack<String> args = new Stack<>();
        private File outDir;
        private File outJavaCode;

        private Visitor(File outDir, IndentPrinter printer) {
            this.outDir = outDir;
            this.printer = printer;
        }

        @Override
        public Void visit(JvmPackage node) {
            outDir = new File(outDir, node.name.replace('.', File.separatorChar));
            outDir.mkdirs();
            printer.println("package", node.name, ";");
            for (JvmNode child : node.getChildren()) {
                accept(child);
            }
            return null;
        }

        @Override
        public Void visit(JvmClass node) {
            outJavaCode = new File(outDir, node.name + ".java");
            gen.push();
            printer.println(javaAccessModifier(node.access), "final class", node.name);
            printer.println("{");
            printer.indent();
            for (JvmNode child : node.getChildren()) {
                accept(child);
            }
            printer.unindent();
            printer.println("}");
            gen.pop();
            return null;
        }

        @Override
        public Void visit(JvmImport node) {
            printer.println("import", node.name, ";");
            return null;
        }

        @Override
        public Void visit(JvmFunction node) {
            gen.push();
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
            printer.println(")");
            accept(node.block);
            gen.pop();
            return null;
        }

        @Override
        public Void visit(JvmVariable node) {
            if (node.memory == MemoryModifier.LOCAL) {
                accept(node.expr);
                printer.println(
                        javaTypeName(node.type),
                        node.name,
                        "=",
                        args.pop(),
                        ";");
            } else {
                // TODO: Init expression generation
                printer.println(
                        javaAccessModifier(node.access),
                        javaMemoryModifier(node.memory),
                        javaTypeName(node.type),
                        node.name, ";");
            }
            return null;
        }

        @Override
        public Void visit(JvmValue node) {
            args.push(gen.newName());
            printer.println(javaTypeName(node.type), args.top(), "=", node.text, ";");
            return null;
        }

        @Override
        public Void visit(JvmNameRef node) {
            args.push(node.name);
            return null;
        }

        @Override
        public Void visit(JvmApply node) {
            for (JvmNode a : node.args) {
                accept(a);
            }
            // In reverse to the right order
            List<String> args = new ArrayList<>();
            for (int i = 0; i < node.args.size(); i++) {
                args.add(this.args.pop());
            }
            if (!node.returnType.equals(AstType.NONE)) {
                this.args.push(gen.newName());
                printer.print(
                        javaTypeName(node.returnType),
                        this.args.top(),
                        "= ");  // Extra space before expression
            }
            printer.println(
                    (node.object == null? "": node.object + ".") + node.method,
                    "(",
                    String.join(", ", Lists.reverse(args)),
                    ");");
            return null;
        }

        @Override
        public Void visit(JvmBinary node) {
            accept(node.l);
            accept(node.r);
            String r = args.pop();
            String l = args.pop();
            args.push(gen.newName());
            printer.println(javaTypeName(node.returnType), args.top(), "=", l, node.op, r, ";");
            return null;
        }

        @Override
        public Void visit(JvmAssign node) {
            accept(node.l);
            accept(node.r);
            String r = args.pop();
            String l = args.pop();
            printer.println(l, "=", r, ";");
            return null;
        }

        @Override
        public Void visit(JvmBlock node) {
            printer.println("{");
            printer.indent();
            if (node.vars != null) {
                for (Entity e : node.vars) {
                    printer.println(javaTypeName(e.type), e.name, ";");
                }
            }
            for (JvmNode s : node.getStatements()) {
                accept(s);
            }
            printer.unindent();
            printer.println("}");
            return null;
        }

        @Override
        public Void visit(JvmReturn node) {
            accept(node.expr);
            printer.println("return", args.pop(), ";");
            return null;
        }

        @Override
        public Void visit(JvmComment node) {
            printer.println("//", node.text);
            return null;
        }

        @Override
        public Void visit(JvmGroup node) {
            for (JvmNode child : node.getChildren()) {
                accept(child);
            }
            return null;
        }
    }
}
