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

    private static final class Context {

    }

    private static String javaAccessModifierStr(JvmAccessModifier m) {
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

    private static String javaMemoryModifierStr(JvmMemoryModifier m) {
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
        private final Context context = new Context();

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
            printer.printlnAll(javaAccessModifierStr(n.access), "class", n.name).curlyOpen();
            printer.curlyClose();
            return null;
        }

        @Override
        public Void visit(JvmFunction n) {
            return (Void) visitError();
        }

        @Override
        public Void visit(JvmVariable n) {
            return (Void) visitError();
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
    }
}
