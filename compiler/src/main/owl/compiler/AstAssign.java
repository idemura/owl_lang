package owl.compiler;

final class AstAssign extends AstNode {
    AstNode l, r;

    AstAssign(AstNode l, AstNode r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
