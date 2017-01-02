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
        new Visitor(new IndentPrinter(out)).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor<Void> {
        private final IndentPrinter printer;

        private Visitor(IndentPrinter printer) {
            this.printer = printer;
        }

        @Override
        public Void visit(AstName node) {
            leaf(node, node.name);
            return null;
        }

        @Override
        public Void visit(AstField node) {
            beginNode(node, node.field);
            accept(node.object);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstModule node) {
            beginNode(node);
            for (AstNode f : node.variables) {
                accept(f);
                printer.println("");
            }
            for (AstNode f : node.functions) {
                accept(f);
                printer.println("");
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            beginNode(node, node.getName());
            if (node.getReturnType() != null) {
                prop("ReturnType", node.getReturnType().toString());
            }
            for (AstVariable a : node.getArgs()) {
                leaf(a, a.getName() + ": " + a.getType());
            }
            accept(node.getBlock());
            endNode();
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            beginNode(node, node.getName());
            accept(node.getExpr());
            endNode();
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            beginNode(node);
            for (AstNode s : node.children) {
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
            accept(node.fn);
            for (AstNode e : node.args) {
                accept(e);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstLiteral node) {
            leaf(node, node.text + " " + node.getType());
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            beginNode(node);
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    accept(b.condition);
                }
                accept(b.block);
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

        @Override
        public Void visit(AstCoerce node) {
            beginNode(node, node.getType().toString());
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstGroup node) {
            beginNode(node);
            for (AstNode c : node.children) {
                accept(c);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstAssign node) {
            beginNode(node);
            accept(node.l);
            accept(node.r);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstNew node) {
            beginNode(node, node.type.toString());
            accept(node.init);
            endNode();
            return null;
        }

        private void prop(String name, String s) {
            printer.println(name + ": " + s);
        }

        private void leaf(Object node) {
            printer.println(getClassName(node));
        }

        private void leaf(Object node, String s) {
            printer.println(getClassName(node), s);
        }

        private void beginNode(Object node) {
            printer.println(getClassName(node));
            printer.indent();
        }

        private void beginNode(Object node, String s) {
            printer.println(getClassName(node), s);
            printer.indent();
        }

        private void endNode() {
            printer.unindent();
        }

        private static String getClassName(Object node) {
            String fullName = node.getClass().getName();
            String name = fullName.substring(fullName.lastIndexOf('.') + 1);
            if (name.startsWith("Ast")) {
                name = name.substring(3);
            }
            return name;
        }
    }
}
