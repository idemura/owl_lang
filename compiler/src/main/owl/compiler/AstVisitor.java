package owl.compiler;

interface AstVisitor<T> {
    default T accept(AstNode node) {
        return (T) node.accept(this);
    }

    default T visitError() {
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T visit(AstApply node) { return visitError(); }
    default T visit(AstAssign node) { return visitError(); }
    default T visit(AstAssignIndex node) { return visitError(); }
    default T visit(AstBlock node) { return visitError(); }
    default T visit(AstCoerce node) { return visitError(); }
    default T visit(AstExpr node) { return visitError(); }
    default T visit(AstIndex node) { return visitError(); }
    default T visit(AstFunction node) { return visitError(); }
    default T visit(AstGroup node) { return visitError(); }
    default T visit(AstIf node) { return visitError(); }
    default T visit(AstLiteral node) { return visitError(); }
    default T visit(AstModule node) { return visitError(); }
    default T visit(AstName node) { return visitError(); }
    default T visit(AstNew node) { return visitError(); }
    default T visit(AstObject node) { return visitError(); }
    default T visit(AstReturn node) { return visitError(); }
    default T visit(AstSelect node) { return visitError(); }
    default T visit(AstType node) { return visitError(); }
    default T visit(AstVariable node) { return visitError(); }
    default T visit(AstFor node) { return visitError(); }
}
