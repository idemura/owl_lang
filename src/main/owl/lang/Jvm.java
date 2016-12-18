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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

final class Jvm {
    JvmNode root;

    Jvm(JvmNode root) {
        this.root = root;
    }

    <T extends JvmNode> T getRoot() {
        return (T) root;
    }
}

interface JvmTranslator {
    void translate(Jvm jvm, File outDir, PrintStream echo) throws OwlException;
}

interface JvmVisitor<T> {
    default T visitError() {
        Util.visitError(getClass());
        return null;
    }

    default T accept(JvmNode node) {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return visitError();
        }
    }

    default T visit(JvmApply node) { return visitError(); }
    default T visit(JvmAssign node) { return visitError(); }
    default T visit(JvmBlock node) { return visitError(); }
    default T visit(JvmBinary node) { return visitError(); }
    default T visit(JvmClass node) { return visitError(); }
    default T visit(JvmComment node) { return visitError(); }
    default T visit(JvmFunction node) { return visitError(); }
    default T visit(JvmGroup node) { return visitError(); }
    default T visit(JvmImport node) { return visitError(); }
    default T visit(JvmNameRef node) { return visitError(); }
    default T visit(JvmPackage node) { return visitError(); }
    default T visit(JvmReturn node) { return visitError(); }
    default T visit(JvmValue node) { return visitError(); }
    default T visit(JvmVariable node) { return visitError(); }
}

abstract class JvmNode {
    abstract Object accept(JvmVisitor v);
}

enum AccessModifier {
    PUBLIC,
    PRIVATE,
    PACKAGE,
}

enum MemoryModifier {
    STATIC,
    LOCAL,
    MEMBER,
}

final class JvmPackage extends JvmNode {
    final String name;
    private List<JvmNode> children = new ArrayList<>();

    JvmPackage(String name) {
        this.name = name;
    }

    void add(JvmNode node) {
        children.add(node);
    }

    List<JvmNode> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmClass extends JvmNode {
    final AccessModifier access;
    final String name;
    private List<JvmNode> children = new ArrayList<>();

    JvmClass(AccessModifier access, String name) {
        this.access = access;
        this.name = name;
    }

    void add(JvmNode node) {
        children.add(node);
    }

    List<JvmNode> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmVariable extends JvmNode {
    final AccessModifier access;
    final MemoryModifier memory;
    final String name;
    final AstType type;
    final JvmNode expr;

    static JvmVariable fnArg(AstType type, String name) {
        return new JvmVariable(AccessModifier.PRIVATE, MemoryModifier.LOCAL, type, name, null);
    }
    static JvmVariable local(AstType type, String name, JvmNode expr) {
        return new JvmVariable(AccessModifier.PRIVATE, MemoryModifier.LOCAL, type, name, expr);
    }

    JvmVariable(
            AccessModifier access,
            MemoryModifier memory,
            AstType type,
            String name,
            JvmNode expr) {
        this.access = access;
        this.memory = memory;
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmFunction extends JvmNode {
    final AccessModifier access;
    final MemoryModifier memory;
    final AstType returnType;
    final String name;
    final List<JvmVariable> args;
    final JvmNode block;

    JvmFunction(
            AccessModifier access,
            MemoryModifier memory,
            AstType returnType,
            String name,
            List<JvmVariable> args,
            JvmNode block) {
        this.access = access;
        this.memory = memory;
        this.name = name;
        this.args = ImmutableList.copyOf(args);
        this.returnType = returnType;
        this.block = block;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmValue extends JvmNode {
    final String text;
    final AstType type;

    JvmValue(String text, AstType type) {
        this.text = text;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmNameRef extends JvmNode {
    final String name;
    final AstType type;

    JvmNameRef(String name, AstType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmApply extends JvmNode {
    final AstType returnType;
    final String object;
    final String method;
    final List<JvmNode> args;

    JvmApply(
            AstType returnType,
            String object,
            String method,
            List<JvmNode> args) {
        this.returnType = returnType;
        this.object = object;
        this.method = method;
        this.args = ImmutableList.copyOf(args);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmBinary extends JvmNode {
    final String op;
    final AstType returnType;
    final JvmNode l;
    final JvmNode r;

    JvmBinary(AstType returnType, String op, JvmNode l, JvmNode r) {
        this.returnType = returnType;
        this.op = op;
        this.l = l;
        this.r = r;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmAssign extends JvmNode {
    final JvmNode l;
    final JvmNode r;

    JvmAssign(JvmNode l, JvmNode r) {
        this.l = l;
        this.r = r;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmBlock extends JvmNode {
    private List<JvmNode> statements = new ArrayList<>();
    List<Entity> vars;

    void add(JvmNode node) {
        statements.add(node);
    }

    List<JvmNode> getStatements() {
        return ImmutableList.copyOf(statements);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmReturn extends JvmNode {
    final JvmNode expr;

    JvmReturn(JvmNode expr) {
        this.expr = expr;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmComment extends JvmNode {
    final String text;

    JvmComment(String text) {
        this.text = text;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmImport extends JvmNode {
    final String name;

    JvmImport(String name) {
        this.name = name;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmGroup extends JvmNode {
    private List<JvmNode> children = new ArrayList<>();

    JvmGroup() {}

    void add(JvmNode node) {
        children.add(node);
    }

    List<JvmNode> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
