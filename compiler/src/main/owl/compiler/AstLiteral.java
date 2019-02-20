package owl.compiler;

final class AstLiteral extends AstNode
        implements Typed {
    Object object;
    AstType type;

    AstLiteral(Object object, AstType type) {
        this.object = object;
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
