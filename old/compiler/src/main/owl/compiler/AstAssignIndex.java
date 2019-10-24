package owl.compiler;

final class AstAssignIndex extends AstNode {
    AstNode array, index, r;

    AstAssignIndex(AstNode array, AstNode index, AstNode r) {
        this.array = array;
        this.index = index;
        this.r = r;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
