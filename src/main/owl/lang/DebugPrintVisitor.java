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

import java.io.PrintStream;

final class DebugPrint {
    private DebugPrint() {}

    static void printAst(Ast ast, PrintStream out) {
        new AstPrintVisitor(new IndentPrinter(out)).accept(ast.root);
    }

    private static final class AstPrintVisitor implements AstVisitor {
        private final IndentPrinter printer;

        private AstPrintVisitor(IndentPrinter printer) {
            this.printer = printer;
        }

        @Override
        public Void visit(AstName n) {
            leaf(n, n.name);
            return null;
        }

        @Override
        public Void visit(AstType n) {
            node(n, n.name.name);
            for (AstType t : n.args) {
                accept(t);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstMember n) {
            node(n);
            accept(n.left);
            accept(n.name);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstModule n) {
            node(n);
            for (AstNode f : n.members) {
                accept(f);
                printer.println("");
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstFunction n) {
            node(n, n.name);
            prop("returnType", n.returnType.toString());
            for (AstArgument a : n.args) {
                accept(a);
            }
            accept(n.block);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstArgument n) {
            leaf(n, n.name + ": " + n.type);
            return null;
        }

        @Override
        public Void visit(AstVariable n) {
            node(n, n.name);
            accept(n.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstBlock n) {
            node(n);
            for (AstNode s : n.statements) {
                accept(s);
                printer.println(";");
            }
            endNode();
            printer.println(";;");
            return null;
        }

        @Override
        public Void visit(AstApply n) {
            node(n);
            for (AstNode e : n.args) {
                accept(e);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstConstant n) {
            node(n, n.name);
            accept(n.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstLiteral n) {
            leaf(n, n.format + " " + n.text);
            return null;
        }

        @Override
        public Void visit(AstIf n) {
            node(n);
            for (int i = 0; i < n.condition.size(); i++) {
                if (i != 0) {
                    printer.println("# elif condition:");
                }
                accept(n.condition.get(i));
                if (i != 0) {
                    printer.println("# then:");
                } else {
                    printer.println("# elif:");
                }
                accept(n.block.get(i));
            }
            if (n.block.size() > n.condition.size()) {
                printer.println("# else:");
                accept(n.block.get(n.block.size() - 1));
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstMatch n) {
            node(n);
            int blockIndex = 0;
            for (AstMatch.Label l : n.label) {
                if (blockIndex != l.block) {
                    accept(n.block.get(blockIndex));
                    blockIndex = l.block;
                }
                printer.println("." + l.label + " " + l.variable);
            }
            accept(n.block.get(blockIndex));
            if (n.elseBlock != null) {
                printer.println("# else (default)");
                accept(n.elseBlock);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstReturn n) {
            node(n);
            accept(n.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstExpr n) {
            node(n);
            accept(n.expr);
            endNode();
            return null;
        }

        private void prop(String name, String s) {
            printer.println(name + ": " + s);
        }

        private void leaf(AstNode n) {
            printer.println(getClassName(n));
        }

        private void leaf(AstNode n, String s) {
            printer.println(getClassName(n) + " " + s);
        }

        private void node(AstNode n) {
            printer.println(getClassName(n));
            printer.indent();
        }

        private void node(AstNode n, String s) {
            printer.println(getClassName(n) + " " + s);
            printer.indent();
        }

        private void endNode() {
            printer.unindent();
        }

        private static String getClassName(AstNode node) {
            String fullName = node.getClass().getName();
            String name = fullName.substring(fullName.lastIndexOf('.') + 1);
            if (name.startsWith("Ast")) {
                name = name.substring(3);
            }
            return name;
        }
    }
}
