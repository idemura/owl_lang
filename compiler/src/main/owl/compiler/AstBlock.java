package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstBlock extends AstNode {
    List<AstNode> children = new ArrayList<>();
    boolean scope = true;

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    void add(AstNode node) {
        children.add(node);
    }
}
