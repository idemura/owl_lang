package owl.compiler;

final class AstCoerce extends AstNode
        implements Typed {
    AstNode expr;
    AstType type;

    AstCoerce(AstNode expr, AstType type) {
        this.expr = expr;
        this.type = type;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}
