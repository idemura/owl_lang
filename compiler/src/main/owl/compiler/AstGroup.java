package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstGroup extends AstNode {
    List<AstNode> children = new ArrayList<>();

    AstGroup() {}
    AstGroup(List<AstNode> children) {
        this.children = children;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    void add(AstNode node) {
        children.add(node);
    }
}
