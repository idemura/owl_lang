package owl.compiler;

final class AstFor extends AstNode {
    static abstract class Condition {}

    final static class ForBoolean extends Condition {
        AstNode expr;

        ForBoolean(AstNode expr) {
            this.expr = expr;
        }
    }

    final static class ForRange extends Condition {
        AstVariable iter;
        AstVariable last;
        AstNode expr;
        AstNode increment;

        ForRange(AstVariable iter, AstVariable last) {
            this.iter = iter;
            this.last = last;
        }
    }

    Condition condition;
    AstBlock block;

    AstFor(AstNode expr, AstBlock block) {
        this.condition = new ForBoolean(expr);
        this.block = block;
    }

    AstFor(AstVariable init, AstVariable last, AstBlock block) {
        this.condition = new ForRange(init, last);
        this.block = block;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
