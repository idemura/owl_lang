package owl.compiler;

final class AstName extends AstNode
        implements Typed {
    String name;
    Entity entity;

    AstName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof AstName) {
            return name.equals(((AstName) other).name);
        }
        return false;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return entity == null? null: entity.getType();
    }
}
