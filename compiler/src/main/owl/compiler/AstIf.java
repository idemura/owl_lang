package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstIf extends AstNode {
    final static class Branch {
        AstNode condition;
        AstBlock block;

        Branch(AstNode condition, AstBlock block) {
            this.condition = condition;
            this.block = block;
        }
    }

    final List<Branch> branches = new ArrayList<>();

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    void add(AstNode condition, AstBlock block) {
        branches.add(new Branch(condition, block));
    }
}
