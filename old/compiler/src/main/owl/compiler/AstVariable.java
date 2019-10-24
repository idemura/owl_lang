package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstVariable extends AstNode
        implements Entity {
    static final int MODULE = 0;
    static final int FIELD = 1;
    static final int LOCAL = 2;

    static abstract class Storage {
        abstract int getKind();
    }

    static final class Module extends Storage {
        @Override
        int getKind() { return MODULE; }
    }

    static final class Field extends Storage {
        @Override
        int getKind() { return FIELD; }
    }

    static final class Local extends Storage {
        int index = -1;

        @Override
        int getKind() { return LOCAL; }
    }

    private final Storage storage;
    private final String moduleName;
    private final String name;
    AstType type;
    AstNode expr;

    static AstVariable local(String name, AstNode expr) {
        return new AstVariable(new Local(), null, name, null, expr);
    }

    AstVariable(Storage storage, String moduleName, String name, AstType type, AstNode expr) {
        this.storage = storage;
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("variable");
        if (moduleName == null) {
            list.add(name);
        } else {
            list.add(moduleName + "::" + name);
        }
        if (type != null) {
            list.add("type=" + type);
        }
        if (expr != null) {
            list.add("<expr>");
        }
        return String.join(" ", list);
    }

    @Override
    public String getJvmDescriptor() {
        return getType().getJvmType();
    }

    @Override
    public int hashCode() {
        return Entity.getHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof AstVariable) {
            return Entity.equals(this, (AstVariable) other);
        }
        return false;
    }

    @Override
    public AstType getType() {
        return type;
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String getName() {
        return name;
    }

    Storage getStorage() {
        return storage;
    }
}
