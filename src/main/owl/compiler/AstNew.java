package owl.compiler;

import java.util.List;

final class AstNew extends AstNode
        implements Typed {
    AstType type;
    final List<AstNode> init;

    AstNew(AstType type, List<AstNode> init) {
        this.type = type;
        this.init = init;
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
