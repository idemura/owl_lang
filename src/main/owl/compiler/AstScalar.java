package owl.compiler;

import java.util.List;

final class AstScalar extends AstAbstractType {
    static final AstAbstractType BOOL = new AstScalar("Bool");
    static final AstAbstractType CHAR = new AstScalar("Char");
    static final AstAbstractType F32 = new AstScalar("F32");
    static final AstAbstractType F64 = new AstScalar("F64");
    static final AstAbstractType I32 = new AstScalar("I32");
    static final AstAbstractType I64 = new AstScalar("I64");
    static final AstAbstractType NONE = new AstScalar("None");
    static final AstAbstractType STRING = new AstScalar("String");

    final String name;

    private AstScalar(String name) {
        this.name = name;
    }

    @Override
    public String getModuleName() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    <T> T accept(AstVisitor<T> v) {
        // Should not visit, because never defined in Owl code
        throw new UnsupportedOperationException("accept");
    }

    @Override
    List<TypeParam> getTypeParams() {
        return Util.listOf();
    }

    @Override
    public String toString() {
        return "Scalar " + name;
    }

    @Override
    public int hashCode() {
        // Makes hash maps output stable
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        // Only singletons of this class accessible from outside
        return this == other;
    }
}
