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

final class DebugPrinter {
    private static final String TAB = "  ";
    private int tab = 0;

    void indent() {
        tab++;
    }

    void unindent() {
        tab--;
    }

    void print(String s) {
        for (int i = 0; i < tab; i++) {
            System.out.print(TAB);
        }
        System.out.println(s);
    }
}

class DebugPrintVisitor implements AstVisitor {
    private DebugPrinter printer = new DebugPrinter();

    @Override
    public void visit(AstName n) {
        leaf(n, n.name);
    }

    @Override
    public void visit(AstType n) {
        node(n, n.name.name);
        for (AstType t : n.args) {
            t.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstMember n) {
        node(n);
        n.left.accept(this);
        n.name.accept(this);
        endNode();
    }

    @Override
    public void visit(AstModule n) {
        node(n);
        for (AstNode f : n.members) {
            f.accept(this);
            printer.print("");
        }
        endNode();
    }

    @Override
    public void visit(AstFunction n) {
        node(n, n.name);
        prop("returnType", n.returnType.toString());
        for (AstArgument a : n.args) {
            a.accept(this);
        }
        n.block.accept(this);
        endNode();
    }

    @Override
    public void visit(AstArgument n) {
        leaf(n, n.name + ": " + n.type);
    }

    @Override
    public void visit(AstVariable n) {
        node(n, n.name);
        n.expr.accept(this);
        endNode();
    }

    @Override
    public void visit(AstBlock n) {
        node(n);
        for (AstNode s : n.statements) {
            s.accept(this);
            printer.print(";");
        }
        endNode();
        printer.print(";;");
    }

    @Override
    public void visit(AstApply n) {
        node(n);
        for (AstNode e : n.args) {
            e.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstConstant n) {
        node(n, n.name);
        n.expr.accept(this);
        endNode();
    }

    @Override
    public void visit(AstLiteral n) {
        leaf(n, n.format + " " + n.text);
    }

    @Override
    public void visit(AstIf n) {
        node(n);
        for (int i = 0; i < n.condition.size(); i++) {
            if (i != 0) {
                printer.print("# elif condition:");
            }
            n.condition.get(i).accept(this);
            if (i != 0) {
                printer.print("# then:");
            } else {
                printer.print("# elif:");
            }
            n.block.get(i).accept(this);
        }
        if (n.block.size() > n.condition.size()) {
            printer.print("# else:");
            n.block.get(n.block.size() - 1).accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstMatch n) {
        node(n);
        int blockIndex = 0;
        for (AstMatch.Label l : n.label) {
            if (blockIndex != l.block) {
                n.block.get(blockIndex).accept(this);
                blockIndex = l.block;
            }
            printer.print("." + l.label + " " + l.variable);
        }
        n.block.get(blockIndex).accept(this);
        if (n.elseBlock != null) {
            printer.print("# else (default)");
            n.elseBlock.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstReturn n) {
        node(n);
        if (n.expr != null) {
            n.expr.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstExpr n) {
        node(n);
        n.expr.accept(this);
        endNode();
    }

    private void prop(String name, String s) {
        printer.print(name + ": " + s);
    }

    private void leaf(AstNode n) {
        printer.print(getClassName(n));
    }

    private void leaf(AstNode n, String s) {
        printer.print(getClassName(n) + " " + s);
    }

    private void node(AstNode n) {
        printer.print(getClassName(n));
        printer.indent();
    }

    private void node(AstNode n, String s) {
        printer.print(getClassName(n) + " " + s);
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
