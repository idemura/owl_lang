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
    private int tab = 0;
    private boolean newLine = true;

    void print(String s) {
        int t = tab;
        if (s.endsWith("{\n")) {
            tab++;
            s = s.substring(0, s.length() - 2) + "\n";
        }
        if (s.endsWith("}\n")) {
            tab--;
            if (s.length() == 2) {
                newLine = true;
                return;
            }
            s = s.substring(0, s.length() - 2) + "\n";
        }
        if (newLine) {
            for (int i = 0; i < t; i++) {
                System.out.print("  ");
            }
        }
        newLine = s.endsWith("\n");
        System.out.print(s);
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
            printer.print("\n");
        }
        endNode();
    }

    @Override
    public void visit(AstFunction n) {
        node(n, n.name);
        prop("returnType", n.returnType);
        for (AstArgument a : n.args) {
            a.accept(this);
        }
        n.block.accept(this);
        endNode();
    }

    @Override
    public void visit(AstArgument n) {
        node(n, n.name);
        n.type.accept(this);
        endNode();
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
            printer.print(";\n");
        }
        endNode();
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
        leaf(n, n.text);
    }

    @Override
    public void visit(AstIf n) {
        node(n);
        for (int i = 0; i < n.condition.size(); i++) {
            if (i != 0) {
                printer.print("# elif condition:\n");
            }
            n.condition.get(i).accept(this);
            if (i != 0) {
                printer.print("# then:\n");
            } else {
                printer.print("# elif:\n");
            }
            n.block.get(i).accept(this);
        }
        if (n.block.size() > n.condition.size()) {
            printer.print("# else:\n");
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
            printer.print("." + l.label + " " + l.variable + "\n");
        }
        n.block.get(blockIndex).accept(this);
        if (n.elseBlock != null) {
            printer.print("# Else (default)\n");
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
        printer.print(name + ": " + s + "\n");
    }

    private void prop(String name, AstNode node) {
        printer.print(name + ": ");
        node.accept(this);
    }

    private void leaf(AstNode n) {
        printer.print(getClassName(n) + "\n");
    }

    private void leaf(AstNode n, String s) {
        printer.print(getClassName(n) + " " + s + "\n");
    }

    private void node(AstNode n) {
        printer.print(getClassName(n) + "{\n");
    }

    private void node(AstNode n, String s) {
        printer.print(getClassName(n) + " " + s + "{\n");
    }

    private void endNode() {
        printer.print("}\n");
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
