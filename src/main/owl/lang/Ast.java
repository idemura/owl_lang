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
    default void visit(AstMember node) {}
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
        name = n.name;
    }

    @Override
    public void visit(AstType n) {
        n.name.accept(this);
        if (!n.args.isEmpty()) {
            name += "(";
            n.args.get(0).accept(this);
            for (int i = 1; i < n.args.size(); i++) {
                name += ",";
                n.args.get(i).accept(this);
            }
            name += ")";
        }
    }
}


class AstName extends AstNode {
    String name;

    AstName() {}
    AstName(String name) {
        this.name = name;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}

// Generic type with parameters.
class AstType extends AstNode {
    static final AstType Bool = new AstType("Bool");
    static final AstType Char = new AstType("Char");
    static final AstType F32 = new AstType("F32");
    static final AstType I32 = new AstType("I32");
    static final AstType None = new AstType("None");

    AstType() {}
    AstType(String name) {
        this.name = new AstName(name);
    }

    AstName name;
    List<AstType> args = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstMember extends AstNode {
    AstNode left;
    AstName name;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstModule extends AstNode {
    List<AstFunction> functions = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    final void addFunction(AstFunction f) {
        functions.add(f);
    }
}


class AstFunction extends AstNode {
    String name;
    List<AstVariable> args = new ArrayList<>();
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
    List<AstNode> statements = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


class AstApply extends AstNode {
    List<AstNode> args = new ArrayList<>();

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


final class AstUtil {
    private AstUtil() {}
}
