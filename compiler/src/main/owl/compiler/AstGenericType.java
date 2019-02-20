package owl.compiler;

final class AstGenericType extends AstType {
    AstGenericType() {
        super(GENERIC);
    }

    @Override
    public String toString() {
        return "(generic)";
    }
}
