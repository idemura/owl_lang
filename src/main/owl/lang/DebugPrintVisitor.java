package owl.lang;

class DebugPrintVisitor implements AstNode.Visitor {
    private TabPrinter printer = new TabPrinter();

    @Override
    public void visit(AstName n) {
        node(n);
        if (n.name != null) {
            prop("name", String.join("/", n.name));
        }
        endNode();
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
        }
        endNode();
    }

    @Override
    public void visit(AstFunction n) {
        node(n);
        prop("name", n.name);
        prop("returnType", n.returnType);
        for (AstVariable a : n.arguments) {
            a.accept(this);
        }
        n.block.accept(this);
        endNode();
    }

    @Override
    public void visit(AstVariable n) {
        node(n);
        if (n.name != null) {
            prop("name", n.name);
        }
        n.type.accept(this);
        endNode();
    }

    @Override
    public void visit(AstBlock n) {
        leaf(n, null);
    }

    private void prop(String name, String s) {
        printer.print(name + ": " + s + "\n");
    }

    private void prop(String name, AstNode node) {
        printer.print(name + ": ");
        node.accept(this);
    }

    private void leaf(AstNode node, String s) {
        String text = getClassName(node);
        if (s != null) {
            text += " " + s;
        }
        printer.print(text + "\n");
    }

    private void node(AstNode node) {
        printer.print(getClassName(node) + "{\n");
    }

    private void endNode() {
        printer.print("}\n");
    }

    private static String getClassName(AstNode node) {
        String fullName = node.getClass().getName();
        return fullName.substring(fullName.lastIndexOf('.') + 1);
    }
}
