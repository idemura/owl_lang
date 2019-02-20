package owl.compiler;

import java.util.List;
import java.util.Objects;

final class AstObject extends AstAbstractType {
    final String moduleName;
    final String name;
    final List<AstVariable> fields;

    AstObject(String moduleName, String name, List<AstVariable> fields) {
        this.moduleName = moduleName;
        this.name = name;
        this.fields = fields;
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    List<TypeParam> getTypeParams() {
        return Util.listOf();
    }

    @Override
    public String toString() {
        return "object type " + moduleName + "::" + name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof AstObject) {
            AstObject otherObject = (AstObject) other;
            return moduleName.equals(otherObject.moduleName) && name.equals(otherObject.name);
        }
        return false;
    }
}
