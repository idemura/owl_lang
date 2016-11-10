package owl.lang;

import java.util.ArrayList;
import java.util.List;


public class Ast {
    AstNode root;
}


interface AstVisitor {
    default void visit(AstName node) {}
    default void visit(AstType node) {}
    default void visit(AstModule node) {}
    default void visit(AstFunction node) {}
    default void visit(AstVariable node) {}
    default void visit(AstBlock node) {}
    default void visit(AstApply node) {}
    default void visit(AstConstant node) {}
}


abstract class AstNode {
    abstract void accept(AstVisitor visitor);
}


class TypeNameVisitor implements AstVisitor {
    static String typeStr(AstType type) {
        TypeNameVisitor v = new TypeNameVisitor();
        type.accept(v);
        return v.name;
    }

    private String name;

    @Override
    public void visit(AstName n) {
        name += n.getName();
    }

    @Override
    public void visit(AstType n) {
        n.name.accept(this);
        if (!n.params.isEmpty()) {
            name += "(";
            n.params.get(0).accept(this);
            for (int i = 1; i < n.params.size(); i++) {
                name += ",";
                n.params.get(i).accept(this);
            }
            name += ")";
        }
    }
}


class AstName extends AstNode {
    List<String> name = new ArrayList<>();

    AstName() {}
    AstName(String... parts) {
        for (String s : parts) {
            name.add(s);
        }
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    void add(String s) {
        name.add(s);
    }

    String getName() {
        return String.join(".", name);
    }
}


// Generic type with parameters.
class AstType extends AstNode {
    static final AstType Bool = fromName("Bool");
    static final AstType Char = fromName("Char");
    static final AstType F32 = fromName("F32");
    static final AstType I32 = fromName("I32");
    static final AstType None = fromName("None");

    static AstType fromName(String... parts) {
        AstType t = new AstType();
        t.name = new AstName(parts);
        return t;
    }

    AstName name;
    List<AstType> params = new ArrayList<>();

    AstType() {}

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstModule extends AstNode {
    List<AstFunction> functions = new ArrayList<>();

    @Override
    public void accept(AstVisitor visitor) {
        visitor.visit(this);
    }

    final void addFunction(AstFunction f) {
        functions.add(f);
    }
}


class AstFunction extends AstNode {
    String name;
    List<AstVariable> arguments = new ArrayList<>();
    AstType returnType = AstType.None;
    AstBlock block;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstVariable extends AstNode {
    String name;
    AstType type = AstType.None;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstBlock extends AstNode {
    List<AstNode> nodes = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstApply extends AstNode {
    List<AstNode> nodes = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstConstant extends AstNode {
    String value;

    AstConstant() {}
    AstConstant(String value) {
        this.value = value;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}
