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
    default T visit(AstType node) throws OwlException { return visitError(); }
    default T visit(AstValue node) throws OwlException { return visitError(); }
    default T visit(AstVariable node) throws OwlException { return visitError(); }
}

abstract class AstNode {
    private int line;
    private int charPosition;

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
    public AstType getType() {
        return entity.getType();
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

    AstType getReturnType() {
        checkState(isFunction());
        return args.get(args.size() - 1);
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public String toString() {
        if (args.isEmpty()) {
            return name;
        } else {
            List<String> pieces = args.stream().map(AstType::toString).collect(Collectors.toList());
            return name + "(" + String.join(", ", pieces) + ")";
        }
    }

    @Override
    public int hashCode() {
        int h = name.hashCode();
        for (AstType t : args) {
            h = Objects.hash(h, t);
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

    String javaType() {
        if (isArray()) {
            return args.get(0).javaType() + "[]";
        }
        if (args.size() > 0) {
            throw new UnsupportedOperationException("java type name on generic");
        }
        if (equals(AstType.BOOL)) {
            return "boolean";
        } else if (equals(AstType.CHAR)) {
            return "char";
        } else if (equals(AstType.I32)) {
            return "int";
        } else if (equals(AstType.I64)) {
            return "long";
        } else if (equals(AstType.F32)) {
            return "float";
        } else if (equals(AstType.F64)) {
            return "double";
        } else if (equals(AstType.STRING)) {
            return "String";
        } else if (equals(AstType.NONE)) {
            return "void";
        } else {
            return name;
        }
    }
}

final class AstModule extends AstNode {
    String name;
    String fileName;
    List<AstFunction> functions = new ArrayList<>();
    List<AstVariable> variables = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }
}

final class AstFunction extends AstNode
        implements Entity {
    private String moduleName;
    private String name;
    private List<AstVariable> args = new ArrayList<>();
    private List<AstVariable> vars = new ArrayList<>();
    private AstType returnType = AstType.NONE;
    private AstBlock block;

    AstFunction(
            String moduleName,
            String name,
            List<AstVariable> args,
            AstType returnType,
            AstBlock block) {
        this.moduleName = moduleName;
        this.name = name;
        this.args = args;
        this.returnType = returnType == null? AstType.NONE: returnType;
        this.block = block;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    void addVar(AstVariable node) {
        vars.add(node);
    }

    List<AstVariable> getArgs() {
        return ImmutableList.copyOf(args);
    }

    List<AstVariable> getVars() {
        return ImmutableList.copyOf(vars);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("function");
        if (moduleName == null) {
            list.add(name);
        } else {
            list.add(moduleName + "." + name);
        }
        list.add("type=" + getType());
        return String.join(" ", list);
    }

    @Override
    public int hashCode() {
        return Entity.getHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstFunction) {
            return Entity.equals(this, (AstFunction) other);
        }
        return false;
    }

    // Full function type, not just return
    @Override
    public AstType getType() {
        List<AstType> typeArgs = new ArrayList<>();
        for (AstVariable a : args) {
            if (a.getType().equals(AstType.NONE)) {
                throw new IllegalStateException("no argument type");
            }
            typeArgs.add(a.getType());
        }
        typeArgs.add(returnType);
        return AstType.functionOf(typeArgs);
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
    public String getUniqueName() {
        return name;
    }

    AstType getReturnType() {
        return returnType;
    }

    AstBlock getBlock() {
        return block;
    }

    void indexLocals() {
        int i = 0;
        for (AstVariable a : args) {
            a.index = i++;
        }
        NameGen gen = new NameGen("_l_");
        for (AstVariable v : vars) {
            v.setUniqueName(gen.newName());
            v.index = i++;
        }
    }

    Entity getLocal(int index) {
        if (index < args.size()) {
            return args.get(index);
        } else {
            return vars.get(index - args.size());
        }
    }
}

final class AstVariable extends AstNode
        implements Entity {
    private String moduleName;
    private String name;
    private String uniqueName;
    private AstType type;
    private AstNode expr;
    int index = -1;

    AstVariable(String moduleName, String name, AstType type, AstNode expr) {
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    // Needed by argument type deduction and type inference.
    void setType(AstType type) {
        this.type = type;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("variable");
        if (moduleName == null) {
            list.add(name);
        } else {
            list.add(moduleName + "." + name);
        }
        if (uniqueName != null) {
            list.add("(" + uniqueName + ")");
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
    public int hashCode() {
        return Entity.getHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
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

    @Override
    public String getUniqueName() {
        // TODO: Use hash for this?
        return uniqueName != null? uniqueName: name;
    }

    void setUniqueName(String uniqueName) {
        checkState(this.uniqueName == null);
        this.uniqueName = uniqueName;
    }

    AstNode getExpr() {
        return expr;
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
    AstType type;  // Deduced

    AstApply(AstNode fn, List<AstNode> args) {
        this.fn = fn;
        this.args = args;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }

    List<AstType> getArgTypes() {
        List<AstType> types = new ArrayList<>();
        for (AstNode a  : args) {
            types.add(((Typed) a).getType());
        }
        return ImmutableList.copyOf(types);
    }
}

final class AstField extends AstNode
        implements Typed {
    AstNode object;
    String field;
    AstType type;  // Deduced

    AstField(AstNode object, String member) {
        this.object = object;
        this.field = member;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
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
    AstType type;  // Deduced

    AstValue(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}

final class AstIf extends AstNode {
    final class Branch {
        AstExpr condition;
        AstBlock block;

        Branch(AstExpr condition, AstBlock block) {
            this.condition = condition;
            this.block = block;
        }
    }
    List<Branch> branches = new ArrayList<>();

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }
}

final class AstReturn extends AstNode {
    AstNode expr;

    AstReturn(AstNode expr) {
        this.expr = expr;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }
}

final class AstExpr extends AstNode
        implements Typed {
    AstNode expr;

    AstExpr(AstNode expr) {
        this.expr = expr;
    }

    boolean discards() {
        return expr instanceof AstApply && !((AstApply) expr).getType().equals(AstType.NONE);
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
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
    AstGroup(List<AstNode> children) {
        this.children = children;
    }

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
    AstType type;
    AstGroup init;

    AstNew(AstType type, AstGroup init) {
        this.type = type;
        this.init = init;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}

final class AstCast extends AstNode
        implements Typed {
    AstNode expr;
    AstType type;

    AstCast(AstNode expr, AstType type) {
        this.expr = expr;
        this.type = type;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}
