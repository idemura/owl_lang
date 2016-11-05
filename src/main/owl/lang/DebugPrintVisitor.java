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
        leaf(n, n.getName());
    }

    @Override
    public void visit(AstType n) {
        node(n);
        n.name.accept(this);
        for (AstType t : n.params) {
            n.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstModule n) {
        node(n);
        for (AstFunction f : n.functions) {
            f.accept(this);
            printer.print("\n");
        }
        endNode();
    }

    @Override
    public void visit(AstFunction n) {
        node(n, n.name);
        prop("returnType", n.returnType);
        for (AstVariable a : n.arguments) {
            a.accept(this);
        }
        n.block.accept(this);
        endNode();
    }

    @Override
    public void visit(AstVariable n) {
        node(n, n.name);
        n.type.accept(this);
        endNode();
    }

    @Override
    public void visit(AstBlock n) {
        node(n);
        for (AstNode s : n.nodes) {
            s.accept(this);
            printer.print(";\n");
        }
        endNode();
    }

    @Override
    public void visit(AstInvoke n) {
        node(n);
        for (AstNode e : n.nodes) {
            e.accept(this);
        }
        endNode();
    }

    @Override
    public void visit(AstConstant n) {
        leaf(n, n.value);
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
