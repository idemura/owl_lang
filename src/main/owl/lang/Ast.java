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

final class Ast {
    AstNode root;

    Ast(AstNode root) {
        this.root = root;
    }

    <T> T getRootAs() {
        return (T) root;
    }
}

interface AstVisitor<T> {
    default T visitError() {
        throw new UnsupportedOperationException("AstVisitor implementation " + getClass().getName());
    }

    default T accept(AstNode node) {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return null;
        }
    }

    default T visit(AstName node) { return visitError(); }
    default T visit(AstType node) { return visitError(); }
    default T visit(AstModule node) { return visitError(); }
    default T visit(AstFunction node) { return visitError(); }
    default T visit(AstVariable node) { return visitError(); }
    default T visit(AstArgument node) { return visitError(); }
    default T visit(AstBlock node) { return visitError(); }
    default T visit(AstApply node) { return visitError(); }
    default T visit(AstConstant node) { return visitError(); }
    default T visit(AstLiteral node) { return visitError(); }
    default T visit(AstMember node) { return visitError(); }
    default T visit(AstIf node) { return visitError(); }
    default T visit(AstMatch node) { return visitError(); }
    default T visit(AstReturn node) { return visitError(); }
    default T visit(AstExpr node) { return visitError(); }
}

abstract class AstNode {
    int line;
    int charPositionInLine;

    abstract Object accept(AstVisitor visitor);
    AstType getType() {
        throw new UnsupportedOperationException("AstNode implementation " + getClass().getName());
    }
}

class AstName extends AstNode {
    static final AstName BOOL = new AstName("Bool");
    static final AstName CHAR = new AstName("Char");
    static final AstName F32 = new AstName("F32");
    static final AstName F64 = new AstName("F64");
    static final AstName I32 = new AstName("I32");
    static final AstName I64 = new AstName("I64");
    static final AstName NONE = new AstName("None");
    static final AstName STRING = new AstName("String");
    static final AstName ARRAY = new AstName("Array");
    static final AstName FUNCTION = new AstName("Fn");

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
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    AstType getType() {
        return entity.getType();
    }
}

// Generic returnType with parameters
class AstType extends AstNode {
    static final AstType BOOL = new AstType(AstName.BOOL);
    static final AstType CHAR = new AstType(AstName.CHAR);
    static final AstType F32 = new AstType(AstName.F32);
    static final AstType F64 = new AstType(AstName.F64);
    static final AstType I32 = new AstType(AstName.I32);
    static final AstType I64 = new AstType(AstName.I64);
    static final AstType NONE = new AstType(AstName.NONE);
    static final AstType STRING = new AstType(AstName.STRING);

    final AstName name;
    final List<AstType> args;

    static AstType arrayOf(AstType type) {
        return new AstType(AstName.ARRAY, ImmutableList.of(type));
    }

    static AstType functionOf(List<AstType> args) {
        return new AstType(AstName.FUNCTION, args);
    }

    AstType(AstName name) {
        this(name, ImmutableList.of());
    }

    AstType(AstName name, List<AstType> args) {
        this.name = name;
        this.args = ImmutableList.copyOf(args);
    }

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
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    private static final class TypeNameVisitor implements AstVisitor<String> {
        static String run(AstType type) {
            return new TypeNameVisitor().accept(type);
        }

        @Override
        public String visit(AstName n) {
            return n.name;
        }

        @Override
        public String visit(AstType n) {
            String name = accept(n.name);
            if (!n.args.isEmpty()) {
                name += "(";
                name += accept(n.args.get(0));
                for (int i = 1; i < n.args.size(); i++) {
                    name += ", ";
                    name += accept(n.args.get(i));
                }
                name += ")";
            }
            return name;
        }
    }
}

class AstMember extends AstNode {
    AstNode left;
    AstName name;
    AstType type = AstType.NONE;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    AstType getType() {
        return type;
    }
}

class AstModule extends AstNode {
    String name;
    String fileName;
    List<AstNode> members = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

class AstFunction extends AstNode {
    String name = "";
    List<AstArgument> args = new ArrayList<>();
    AstType returnType = AstType.NONE;
    AstBlock block;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new FunctionEntity(moduleName, name, getType());
    }

    @Override
    AstType getType() {
        // Shouldn't be called multiple times
        List<AstType> typeArgs = new ArrayList<>();
        for (AstArgument a : args) {
            if (a.type.equals(AstType.NONE)) {
                throw new IllegalStateException("argument type is None");
            }
            typeArgs.add(a.getType());
        }
        typeArgs.add(returnType);
        return AstType.functionOf(typeArgs);
    }
}

class AstArgument extends AstNode {
    String name = "";
    AstType type = AstType.NONE;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new VariableEntity(moduleName, name, getType(), VariableScope.FUNCTION);
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
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    Entity getEntity(String moduleName) {
        return new VariableEntity(moduleName, name, AstType.NONE, VariableScope.MODULE);
    }

    @Override
    AstType getType() {
        return expr.getType();
    }
}

class AstBlock extends AstNode {
    List<AstNode> statements = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

class AstApply extends AstNode {
    List<AstNode> args = new ArrayList<>();
    // We can't take apply type as function return type because function return type is the result of deduction on
    // function type parameters given argument types. Consider: fn f(x, y: T): T { }. So type may vary in different
    // function application contexts.
    AstType type = AstType.NONE;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
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
    public Object accept(AstVisitor v) {
        return v.visit(this);
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
    AstType type = AstType.NONE;

    AstLiteral() {}
    AstLiteral(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    AstType getType() {
        return type;
    }
}

class AstIf extends AstNode {
    // N conditions each with block and optionally (N + 1)-th block for else
    List<AstNode> condition = new ArrayList<>();
    List<AstNode> block = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

class AstMatch extends AstNode {
    // Label is a name of enum returnType label. Several labels might refer to the same block of index @block.
    static final class Label {
        String label = "";
        String variable = "";
        int block;
    }

    AstNode expr;
    List<Label> label = new ArrayList<>();
    List<AstBlock> block = new ArrayList<>();
    AstNode elseBlock;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

class AstReturn extends AstNode {
    AstNode expr;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

class AstExpr extends AstNode {
    AstNode expr;

    AstExpr() {}
    AstExpr(AstNode expr) {
        this.expr = expr;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    AstType getType() {
        return expr.getType();
    }
}
