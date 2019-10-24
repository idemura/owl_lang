package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstApply extends AstNode
        implements Typed {
    AstNode fn;
    List<AstNode> args;
    // We can't take apply type as function return type because function return type is the result of deduction on
    // function type parameters given argument types. Consider: fn f(x, y: T): T { }. So type may vary in different
    // function application contexts.
    AstType type;  // Deduced

    AstApply(AstNode fn, List<AstNode> args) {
        this.fn = fn;
        this.args = args;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }

    List<AstType> getArgTypes() {
        List<AstType> types = new ArrayList<>();
        for (AstNode a  : args) {
            AstType t = AstType.of(a);
            Util.check(t != null);
            types.add(t);
        }
        return types;
    }
}
