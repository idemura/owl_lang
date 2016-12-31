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
            case MEMBER:
                return "";
        }
        throw new IllegalStateException("MemoryModifier " + m);
    }

    private static final class Visitor implements JvmVisitor<Void> {
        private static final class TypedId {
            AstType type;
            String id;

            TypedId(AstType type, String id) {
                this.type = type;
                this.id = id;
            }
        }

        private final IndentPrinter printer;
        private final NameGen gen = new NameGen("_t_");
        private final Stack<TypedId> stack = new Stack<>();
        private final Stack<JvmFunction> fnStack = new Stack<>();
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
            printer.println("// Generated by", Util.getCompilerName());
            printer.println("package", node.name, ";");
            for (String name : node.getImports()) {
                printer.println("import", name, ";");
            }
            for (JvmClass c : node.getClasses()) {
                accept(c);
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
            for (JvmVariable v : node.getVariables()) {
                accept(v);
            }
            for (JvmFunction f : node.getFunctions()) {
                accept(f);
            }
            printer.unindent();
            printer.println("}");
            gen.pop();
            return null;
        }

        @Override
        public Void visit(JvmFunction node) {
            gen.push();
            fnStack.push(node);
            printer.println(
                    javaAccessModifier(node.access),
                    javaMemoryModifier(node.memory),
                    node.function.getReturnType().javaType(),
                    node.function.getName() , "(");
            if (!node.function.getArgs().isEmpty()) {
                printer.indent();
                printer.indent();
                for (AstVariable a : node.function.getArgs()) {
                    printer.println(a.getType().javaType(), a.getName(),
                            a == Util.last(node.function.getArgs())? "": ",");
                }
                printer.unindent();
                printer.unindent();
            }
            printer.println(")");
            accept(node.block);
            fnStack.pop();
            gen.pop();
            return null;
        }

        @Override
        public Void visit(JvmVariable node) {
            // TODO: Init expression generation
            printer.println(
                    javaAccessModifier(node.access),
                    javaMemoryModifier(node.memory),
                    node.variable.getType().javaType(),
                    node.variable.getName(), ";");
            return null;
        }

        @Override
        public Void visit(JvmLiteral node) {
            stack.push(new TypedId(node.type, node.text));
            return null;
        }

        @Override
        public Void visit(JvmGetLocal node) {
            Entity ent = fnStack.top().function.getLocal(node.index);
            stack.push(new TypedId(ent.getType(), ent.getUniqueName()));
            return null;
        }

        @Override
        public Void visit(JvmApply node) {
            // Reverse order to the actual argument order
            List<String> args = new ArrayList<>();
            for (int i = 0; i < node.numArgs; i++) {
                args.add(stack.pop().id);
            }
            if (!node.returnType.equals(AstType.NONE)) {
                this.stack.push(new TypedId(node.returnType, gen.newName()));
                printer.print(
                        node.returnType.javaType(),
                        this.stack.top().id,
                        "=");
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
            String r = stack.pop().id;
            String l = stack.pop().id;
            stack.push(new TypedId(node.returnType, gen.newName()));
            printer.println(node.returnType.javaType(), stack.top().id, "=", l, node.op, r, ";");
            return null;
        }

        @Override
        public Void visit(JvmPutLocal node) {
            Entity ent = fnStack.top().function.getLocal(node.index);
            String e = stack.pop().id;
            printer.println(ent.getUniqueName(), "=", e, ";");
            return null;
        }

        @Override
        public Void visit(JvmBlock node) {
            printer.println("{");
            printer.indent();
            if (fnStack.top().block == node) {
                for (Entity ent : fnStack.top().function.getVars()) {
                    printer.println(ent.getType().javaType(), ent.getUniqueName(), ";");
                    if (!ent.getName().equals(ent.getUniqueName())) {
                        printer.println("// " + ent.getUniqueName() + " is " + ent.getName());
                    }
                }
            }
            for (JvmNode s : node.getInstructions()) {
                accept(s);
            }
            printer.unindent();
            printer.println("}");
            return null;
        }

        @Override
        public Void visit(JvmReturn node) {
            printer.println("return", stack.pop().id, ";");
            return null;
        }

        @Override
        public Void visit(JvmComment node) {
            printer.println("//", node.text);
            return null;
        }

        @Override
        public Void visit(JvmPop node) {
            return null;
        }

        @Override
        public Void visit(JvmCast node) {
            String l = stack.pop().id;
            stack.push(new TypedId(node.dstType, gen.newName()));
            String dstJavaType = node.dstType.javaType();
            printer.println(dstJavaType, stack.top().id, "=", "(" + dstJavaType + ")", l, ";");
            return null;
        }
    }
}
