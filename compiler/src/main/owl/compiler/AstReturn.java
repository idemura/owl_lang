package owl.compiler;

final class AstReturn extends AstNode {
    AstNode expr;

    AstReturn(AstNode expr) {
        this.expr = expr;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
