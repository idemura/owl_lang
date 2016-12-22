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
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

final class Ast {
    AstNode root;

    Ast(AstNode root) {
        this.root = root;
    }

    <T extends AstNode> T getRoot() {
        return (T) root;
    }
}

interface AstVisitor<T> {
    default T visitError() {
        Util.visitError(getClass());
        return null;
    }

    default T accept(AstNode node) {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return null;
        }
    }

    default T visit(AstApply node) { return visitError(); }
    default T visit(AstArgument node) { return visitError(); }
    default T visit(AstAssign node) { return visitError(); }
    default T visit(AstBlock node) { return visitError(); }
    default T visit(AstCase node) { return visitError(); }
    default T visit(AstCond node) { return visitError(); }
    default T visit(AstConstant node) { return visitError(); }
    default T visit(AstExpr node) { return visitError(); }
    default T visit(AstFunction node) { return visitError(); }
    default T visit(AstGroup node) { return visitError(); }
    default T visit(AstIf node) { return visitError(); }
    default T visit(AstMatch node) { return visitError(); }
    default T visit(AstMember node) { return visitError(); }
    default T visit(AstModule node) { return visitError(); }
    default T visit(AstName node) { return visitError(); }
    default T visit(AstNew node) { return visitError(); }
    default T visit(AstReturn node) { return visitError(); }
    default T visit(AstType node) { return visitError(); }
    default T visit(AstValue node) { return visitError(); }
    default T visit(AstVariable node) { return visitError(); }
}

interface Typed {
    AstType getType();
}

abstract class AstNode {
    private int line;
    private int charPosition;
    private List<AstNode> children = new ArrayList<>();

    void setPosition(int line, int charPosition) {
        this.line = line;
        this.charPosition = charPosition;
    }

    int getLine() { return line; }
    int getCharPosition() { return charPosition; }

    abstract Object accept(AstVisitor visitor);
}

final class AstName extends AstNode
        implements Typed {
    String name;
    Entity entity;

    AstName() {}

    AstName(String name) {
        this();
        this.name = name;
    }

    AstName(List<String> pieces) {
        this(String.join(".", pieces));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstName) {
            AstName otherName = (AstName) other;
            return name.equals(otherName.name);
        }
        return false;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return entity.type;
    }
}

// Generic type with parameters
final class AstType extends AstNode {
    static final String ARRAY = "Array";
    static final String FUNCTION = "Fn";

    static final AstType BOOL = new AstType("Bool");
    static final AstType CHAR = new AstType("Char");
    static final AstType F32 = new AstType("F32");
    static final AstType F64 = new AstType("F64");
    static final AstType I32 = new AstType("I32");
    static final AstType I64 = new AstType("I64");
    static final AstType NONE = new AstType("None");
    static final AstType STRING = new AstType("String");

    final String name;
    final List<AstType> args = new ArrayList<>();

    static AstType arrayOf(AstType type) {
        return new AstType(ARRAY, ImmutableList.of(type));
    }

    static AstType functionOf(List<AstType> args) {
        return new AstType(FUNCTION, args);
    }

    AstType(String name) {
        this.name = name;
    }

    AstType(String name, List<AstType> args) {
        this(name);
        for (AstType t : args) {
            this.args.add(t);
        }
    }

    boolean isArray() {
        return name.equals(ARRAY);
    }

    boolean isFunction() {
        return name.equals(FUNCTION);
    }

    AstType returnType() {
        checkState(isFunction());
        return args.get(args.size() - 1);
    }

    void add(AstType node) {
        args.add(node);
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
        public String visit(AstType node) {
            String name = node.name;
            if (!node.args.isEmpty()) {
                List<String> pieces = node.args.stream().map(this::accept).collect(Collectors.toList());
                name += "(" + String.join(", ", pieces) + ")";
            }
            return name;
        }
    }
}

final class AstMember extends AstNode
        implements Typed {
    AstNode object;
    String member;
    AstType type;  // Deduced

    AstMember() {}

    AstMember(AstNode object, String member) {
        this.object = object;
        this.member = member;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}

final class AstModule extends AstNode {
    String name;
    String fileName;
    List<AstNode> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstNode node) {
        children.add(node);
    }
}

final class AstFunction extends AstNode
        implements Typed {
    String name;
    List<AstArgument> args = new ArrayList<>();
    AstType returnType = AstType.NONE;
    AstBlock block;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstArgument node) {
        args.add(node);
    }

    // Full function type, not return
    @Override
    public AstType getType() {
        // Shouldn't be called multiple times
        List<AstType> typeArgs = new ArrayList<>();
        for (AstArgument a : args) {
            if (a.getType().equals(AstType.NONE)) {
                throw new IllegalStateException("argument type is None");
            }
            typeArgs.add(a.getType());
        }
        typeArgs.add(returnType);
        return AstType.functionOf(typeArgs);
    }
}

final class AstArgument extends AstNode
        implements Typed {
    String name;
    AstType type = AstType.NONE;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}

final class AstVariable extends AstNode
        implements Typed {
    String name;
    AstType type;
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

    @Override
    public AstType getType() {
        return ((Typed) expr).getType();
    }
}

final class AstBlock extends AstNode {
    List<AstNode> children = new ArrayList<>();
    List<Entity> vars = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstNode node) {
        children.add(node);
    }
}

final class AstApply extends AstNode
        implements Typed {
    AstNode fn;
    List<AstNode> args = new ArrayList<>();
    // We can't take apply type as function return type because function return type is the result of deduction on
    // function type parameters given argument types. Consider: fn f(x, y: T): T { }. So type may vary in different
    // function application contexts.
    AstType type;  // Deduced

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }

    void add(AstNode node) {
        args.add(node);
    }

    List<AstType> getArgTypes() {
        List<AstType> types = new ArrayList<>();
        for (AstNode a  : args) {
            types.add(((Typed) a).getType());
        }
        return ImmutableList.copyOf(types);
    }
}

final class AstConstant extends AstNode
        implements Typed {
    String name;
    AstNode expr;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return ((Typed) expr).getType();
    }
}

final class AstValue extends AstNode
        implements Typed {
    enum Format {
        DEC,
        OCT,
        HEX,
        STRING,
    }

    String text;
    Format format;
    AstType type;  // Deduced

    AstValue() {}

    AstValue(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}

final class AstIf extends AstNode {
    List<AstCond> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstCond node) {
        children.add(node);
    }
}

final class AstCond extends AstNode {
    AstExpr condition;
    AstBlock block;

    AstCond() {}

    AstCond(AstExpr condition, AstBlock block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

final class AstMatch extends AstNode {
    AstExpr expr;
    List<AstCase> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstCase node) {
        children.add(node);
    }
}

final class AstCase extends AstNode {
    String label;
    String variable;
    AstBlock block;

    AstCase() {}

    AstCase(String label, String variable, AstBlock block) {
        this.label = label;
        this.variable = variable;
        this.block = block;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

final class AstReturn extends AstNode {
    AstNode expr;

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

final class AstExpr extends AstNode
        implements Typed {
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
    public AstType getType() {
        return ((Typed) expr).getType();
    }
}

final class AstGroup extends AstNode {
    List<AstNode> children = new ArrayList<>();

    AstGroup() {}

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    void add(AstNode node) {
        children.add(node);
    }
}

final class AstAssign extends AstNode {
    String op;
    AstNode l, r;

    AstAssign(String op, AstNode l, AstNode r) {
        this.op = op;
        this.l = l;
        this.r = r;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }
}

final class AstNew extends AstNode
        implements Typed {
    AstType type;
    AstGroup init;

    AstNew(AstType type, AstGroup init) {
        this.type = type;
        this.init = init;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}
