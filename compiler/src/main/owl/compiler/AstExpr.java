package owl.compiler;

final class AstExpr extends AstNode
        implements Typed {
    AstNode expr;

    AstExpr(AstNode expr) {
        this.expr = expr;
    }

    boolean discards() {
        return expr instanceof AstApply && !((AstApply) expr).getType().equals(AstType.NONE);
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return AstType.of(expr);
    }
}
