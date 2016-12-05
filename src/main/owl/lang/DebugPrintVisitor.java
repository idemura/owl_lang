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
        public Void visit(AstName node) {
            leaf(node, node.name);
            return null;
        }

        @Override
        public Void visit(AstType node) {
            beginNode(node, node.name.name);
            for (AstType t : node.args) {
                accept(t);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstMember node) {
            beginNode(node);
            accept(node.left);
            accept(node.name);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstModule node) {
            beginNode(node);
            for (AstNode f : node.members) {
                accept(f);
                printer.println("");
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            beginNode(node, node.name);
            prop("returnType", node.returnType.toString());
            for (AstArgument a : node.args) {
                accept(a);
            }
            accept(node.block);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstArgument node) {
            leaf(node, node.name + ": " + node.type);
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            beginNode(node, node.name);
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            beginNode(node);
            for (AstNode s : node.statements) {
                accept(s);
                printer.println(";");
            }
            endNode();
            printer.println(";;");
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            beginNode(node);
            for (AstNode e : node.args) {
                accept(e);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstConstant node) {
            beginNode(node, node.name);
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstLiteral node) {
            leaf(node, node.format + " " + node.text);
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            beginNode(node);
            for (int i = 0; i < node.condition.size(); i++) {
                if (i != 0) {
                    printer.println("# elif condition:");
                }
                accept(node.condition.get(i));
                if (i != 0) {
                    printer.println("# then:");
                } else {
                    printer.println("# elif:");
                }
                accept(node.block.get(i));
            }
            if (node.block.size() > node.condition.size()) {
                printer.println("# else:");
                accept(node.block.get(node.block.size() - 1));
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstMatch node) {
            beginNode(node);
            int blockIndex = 0;
            for (AstMatch.Label l : node.label) {
                if (blockIndex != l.block) {
                    accept(node.block.get(blockIndex));
                    blockIndex = l.block;
                }
                printer.println("." + l.label + " " + l.variable);
            }
            accept(node.block.get(blockIndex));
            if (node.elseBlock != null) {
                printer.println("# else (default)");
                accept(node.elseBlock);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstReturn node) {
            beginNode(node);
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            beginNode(node);
            accept(node.expr);
            endNode();
            return null;
        }

        private void prop(String name, String s) {
            printer.println(name + ": " + s);
        }

        private void leaf(AstNode node) {
            printer.println(getClassName(node));
        }

        private void leaf(AstNode node, String s) {
            printer.println(getClassName(node) + " " + s);
        }

        private void beginNode(AstNode node) {
            printer.println(getClassName(node));
            printer.indent();
        }

        private void beginNode(AstNode node, String s) {
            printer.println(getClassName(node) + " " + s);
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
