package owl.compiler;

import java.util.List;

final class AstArrayType extends AstAbstractType {
    static final AstArrayType INSTANCE = new AstArrayType();
    static final String NAME = "Array";

    private final List<TypeParam> params = Util.listOf(TypeParam.getScalarTypeParam());

    private AstArrayType() {}

    @Override
    public String getModuleName() {
        return "";
    }

    @Override
    public String getName() {
        return "Array";
    }

    @Override
    <T> T accept(AstVisitor<T> v) {
        // Should not visit, because never defined in Owl code
        throw new UnsupportedOperationException("accept");
    }

    @Override
    List<TypeParam> getTypeParams() {
        return params;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public int hashCode() {
        return NAME.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AstArrayType;
    }
}
