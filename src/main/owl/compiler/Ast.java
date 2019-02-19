package owl.compiler;

final class Ast {
    AstNode root;
    String fileName;

    Ast(AstNode root) {
        this.root = root;
    }

    AstModule getModule() {
        return (AstModule) root;
    }

    <T> T accept(AstVisitor<T> visitor) {
        return root.accept(visitor);
    }
}
