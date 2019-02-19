package owl.compiler;

abstract class TypeParam {
    abstract boolean match(AstType type);

    static TypeParam getScalarTypeParam() {
        return new ScalarTypeParam();
    }

    private final static class ScalarTypeParam extends TypeParam {
        boolean match(AstType type) {
            return type.isGeneric() || type.abstractType instanceof AstScalar;
        }

        @Override
        public String toString() {
            return "type parameter (scalar)";
        }
    }
}
