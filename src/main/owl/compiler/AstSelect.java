package owl.compiler;

final class AstSelect extends AstNode
        implements Typed {
    AstNode object;
    String field;
    AstType type;  // Deduced

    AstSelect(AstNode object, String member) {
        this.object = object;
        this.field = member;
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
