package owl.compiler;

final class AstIndex extends AstNode
        implements Typed {
    AstNode array, index;

    AstIndex(AstNode array, AstNode index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        AstType type = AstType.of(array);
        if (type.isArray()) {
            return type.args.get(0);
        }
        if (type.equals(AstType.STRING)) {
            return AstType.CHAR;
        }
        Util.checkFail("invalid type of array");
        return type;
    }
}
