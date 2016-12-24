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
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T accept(AstNode node) throws OwlException {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return null;
        }
    }

    default T visit(AstApply node) throws OwlException { return visitError(); }
    default T visit(AstAssign node) throws OwlException { return visitError(); }
    default T visit(AstBlock node) throws OwlException { return visitError(); }
    default T visit(AstCast node) throws OwlException { return visitError(); }
    default T visit(AstExpr node) throws OwlException { return visitError(); }
    default T visit(AstField node) throws OwlException { return visitError(); }
    default T visit(AstFunction node) throws OwlException { return visitError(); }
    default T visit(AstGroup node) throws OwlException { return visitError(); }
    default T visit(AstIf node) throws OwlException { return visitError(); }
    default T visit(AstModule node) throws OwlException { return visitError(); }
    default T visit(AstName node) throws OwlException { return visitError(); }
    default T visit(AstNew node) throws OwlException { return visitError(); }
    default T visit(AstReturn node) throws OwlException { return visitError(); }
    default T visit(AstValue node) throws OwlException { return visitError(); }
    default T visit(AstVariable node) throws OwlException { return visitError(); }
}

interface Typed {
    Type getType();
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

    abstract Object accept(AstVisitor visitor) throws OwlException;
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
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return entity.type;
    }
}

final class AstField extends AstNode
        implements Typed {
    AstNode object;
    String field;
    Type type;  // Deduced

    AstField() {}

    AstField(AstNode object, String member) {
        this.object = object;
        this.field = member;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return type;
    }
}

final class AstModule extends AstNode {
    String name;
    String fileName;
    List<AstNode> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
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
    Type returnType = Type.NONE;
    AstBlock block;
    private List<Entity> vars = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    void addArg(AstArgument node) {
        args.add(node);
    }

    void addVar(Entity ent) {
        ent.setIndex(vars.size());
        vars.add(ent);
    }

    List<Entity> getVars() {
        return ImmutableList.copyOf(vars);
    }

    // Full function type, not return
    @Override
    public Type getType() {
        // Shouldn't be called multiple times
        List<Type> typeArgs = new ArrayList<>();
        for (AstArgument a : args) {
            if (a.getType().equals(Type.NONE)) {
                throw new IllegalStateException("argument type is None");
            }
            typeArgs.add(a.getType());
        }
        typeArgs.add(returnType);
        return Type.functionOf(typeArgs);
    }
}

final class AstArgument
        implements Typed {
    String name;
    Type type = Type.NONE;

    @Override
    public Type getType() {
        return type;
    }
}

final class AstVariable extends AstNode
        implements Typed {
    String name;
    AstNode expr;
    Entity entity;

    AstVariable(String name, AstNode expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return ((Typed) expr).getType();
    }
}

final class AstBlock extends AstNode {
    List<AstNode> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
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
    Type type;  // Deduced

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return type;
    }

    void add(AstNode node) {
        args.add(node);
    }

    List<Type> getArgTypes() {
        List<Type> types = new ArrayList<>();
        for (AstNode a  : args) {
            types.add(((Typed) a).getType());
        }
        return ImmutableList.copyOf(types);
    }
}

final class AstValue extends AstNode
        implements Typed {
    enum Format {
        DEC,
        HEX,
        OCT,
        STRING,
    }

    String text;
    Format format;
    Type type;  // Deduced

    AstValue() {}

    AstValue(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return type;
    }
}

final class AstIf extends AstNode {
    List<AstIfBlock> children = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    void add(AstIfBlock node) {
        children.add(node);
    }
}

final class AstIfBlock {
    AstExpr condition;
    AstBlock block;

    AstIfBlock() {}

    AstIfBlock(AstExpr condition, AstBlock block) {
        this.condition = condition;
        this.block = block;
    }
}

final class AstReturn extends AstNode {
    AstNode expr;

    @Override
    public Object accept(AstVisitor v) throws OwlException {
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

    boolean discards() {
        return expr instanceof AstApply && !((AstApply) expr).getType().equals(Type.NONE);
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return ((Typed) expr).getType();
    }
}

final class AstGroup extends AstNode {
    List<AstNode> children = new ArrayList<>();

    AstGroup() {}

    @Override
    public Object accept(AstVisitor v) throws OwlException {
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
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }
}

final class AstNew extends AstNode
        implements Typed {
    Type type;
    AstGroup init;

    AstNew(Type type, AstGroup init) {
        this.type = type;
        this.init = init;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return type;
    }
}

final class AstCast extends AstNode
        implements Typed {
    AstNode expr;
    Type type;

    AstCast(AstNode expr, Type type) {
        this.expr = expr;
        this.type = type;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public Type getType() {
        return type;
    }
}
