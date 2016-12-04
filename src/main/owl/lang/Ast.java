/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package owl.lang;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ast {
    AstModule module;

    Ast(AstModule module) {
        this.module = module;
    }

    void accept(AstVisitor v) {
        if (module != null) {
            module.accept(v);
        }
    }
}

interface AstVisitor {
    default void visit(AstName node) {}
    default void visit(AstType node) {}
    default void visit(AstModule node) {}
    default void visit(AstFunction node) {}
    default void visit(AstVariable node) {}
    default void visit(AstArgument node) {}
    default void visit(AstBlock node) {}
    default void visit(AstApply node) {}
    default void visit(AstConstant node) {}
    default void visit(AstLiteral node) {}
    default void visit(AstMember node) {}
    default void visit(AstIf node) {}
    default void visit(AstMatch node) {}
    default void visit(AstReturn node) {}
    default void visit(AstExpr node) {}
}

abstract class AstNode {
    int line;
    int charPositionInLine;

    abstract void accept(AstVisitor visitor);
    AstType getType() {
        throw new UnsupportedOperationException("getType on " + getClass().getSimpleName());
    }
}

class AstName extends AstNode {
    String name = "";
    Entity entity = null;

    AstName() {}
    AstName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstName) {
            AstName otherName = (AstName) other;
            return name.equals(otherName.name);
        }
        return name.equals(other);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return entity.getType();
    }
}

// Generic returnType with parameters.
class AstType extends AstNode {
    static final AstType Bool = new AstType("Bool");
    static final AstType Char = new AstType("Char");
    static final AstType F32 = new AstType("F32");
    static final AstType I32 = new AstType("I32");
    static final AstType I64 = new AstType("I64");
    static final AstType None = new AstType("None");
    static final AstType String = new AstType("String");

    AstType() {}
    AstType(String name) {
        this.name = new AstName(name);
    }

    AstName name;
    List<AstType> args = new ArrayList<>();

    @Override
    public String toString() {
        return TypeNameVisitor.run(this);
    }

    @Override
    public int hashCode() {
        int h = name.hashCode();
        for (AstType t : args) {
            h = Objects.hash(h, t.hashCode());
        }
        return h;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstType) {
            AstType otherType = (AstType) other;
            if (!Objects.equals(name, otherType.name)) {
                return false;
            }
            if (!Objects.equals(args, otherType.args)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    private static class TypeNameVisitor implements AstVisitor {
        static String run(AstType type) {
            TypeNameVisitor v = new TypeNameVisitor();
            type.accept(v);
            return v.name;
        }

        private String name = "";

        @Override
        public void visit(AstName n) {
            name += n.name;
        }

        @Override
        public void visit(AstType n) {
            n.name.accept(this);
            if (!n.args.isEmpty()) {
                name += "(";
                n.args.get(0).accept(this);
                for (int i = 1; i < n.args.size(); i++) {
                    name += ", ";
                    n.args.get(i).accept(this);
                }
                name += ")";
            }
        }
    }
}

class AstMember extends AstNode {
    AstNode left;
    AstName name;
    AstType type = AstType.None;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return type;
    }
}

class AstModule extends AstNode {
    String name = "<main>";
    List<AstNode> members = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}

class AstFunction extends AstNode {
    String name = "";
    List<AstArgument> args = new ArrayList<>();
    AstType returnType = AstType.None;
    AstBlock block;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new FunctionEntity(moduleName, name, getType());
    }

    @Override
    AstType getType() {
        // Shouldn't be called multiple times.
        AstType t = new AstType("Fn");
        for (AstArgument a : args) {
            if (a.type.equals(AstType.None)) {
                throw new IllegalStateException("argument type is None");
            }
            t.args.add(a.getType());
        }
        t.args.add(returnType);
        return t;
    }
}

class AstArgument extends AstNode {
    String name = "";
    AstType type = AstType.None;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new VariableEntity(moduleName, name, getType());
    }

    @Override
    AstType getType() {
        return type;
    }
}

class AstVariable extends AstNode {
    String name = "";
    AstNode expr;

    AstVariable() {}
    AstVariable(String name, AstNode expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new VariableEntity(moduleName, name, AstType.None);
    }

    @Override
    AstType getType() {
        return expr.getType();
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
    // We can't take apply type as function return type because function return type is the result of deduction on
    // function type parameters given argument types. Consider: fn f(x, y: T): T {}. So type may vary in different
    // function application contexts.
    AstType type = AstType.None;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return type;
    }

    List<AstType> getArgTypes() {
        List<AstType> types = new ArrayList<>();
        for (int i = 1; i < args.size(); i++) {
            types.add(args.get(i).getType());
        }
        return ImmutableList.copyOf(types);
    }
}

class AstConstant extends AstNode {
    String name = "";
    AstNode expr;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return expr.getType();
    }
}

class AstLiteral extends AstNode {
    enum Format {
        DEC,
        OCT,
        HEX,
        STRING,
    }

    String text = "";
    Format format = null;
    AstType type = AstType.None;

    AstLiteral() {}
    AstLiteral(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return type;
    }
}

class AstIf extends AstNode {
    // N conditions each with block and optionally (N + 1)-th block for else.
    List<AstNode> condition = new ArrayList<>();
    List<AstNode> block = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}

class AstMatch extends AstNode {
    // Label is a name of enum returnType label. Several labels might refer to the same block of index @block.
    static class Label {
        String label = "";
        String variable = "";
        int block;
    }

    AstNode expr;
    List<Label> label = new ArrayList<>();
    List<AstBlock> block = new ArrayList<>();
    AstNode elseBlock;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}

class AstReturn extends AstNode {
    AstNode expr;

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}

class AstExpr extends AstNode {
    AstNode expr;

    AstExpr() {}
    AstExpr(AstNode expr) {
        this.expr = expr;
    }

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }

    @Override
    AstType getType() {
        return expr.getType();
    }
}
