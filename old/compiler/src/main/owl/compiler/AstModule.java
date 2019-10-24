package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstModule extends AstNode {
    String name;
    List<AstFunction> functions = new ArrayList<>();
    List<AstVariable> variables = new ArrayList<>();

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
