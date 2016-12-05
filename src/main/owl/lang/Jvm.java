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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

final class Jvm {
    JvmNode root;

    Jvm(JvmNode root) {
        this.root = root;
    }
}

interface JvmTranslator {
    void translate(Jvm jvm, OutputStream out) throws OwlException;
}

interface JvmVisitor<T> {
    default T visitError() {
        throw new UnsupportedOperationException("JvmVisitor implementation " + getClass().getName());
    }

    default T accept(JvmNode node) {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return visitError();
        }
    }

    default T visit(JvmPackage node) { return visitError(); }
    default T visit(JvmClass node) { return visitError(); }
    default T visit(JvmFunction node) { return visitError(); }
    default T visit(JvmVariable node) { return visitError(); }
    default T visit(JvmValue node) { return visitError(); }
    default T visit(JvmApply node) { return visitError(); }
    default T visit(JvmBinary node) { return visitError(); }
    default T visit(JvmBlock node) { return visitError(); }
    default T visit(JvmReturn node) { return visitError(); }
}

abstract class JvmNode {
    abstract Object accept(JvmVisitor v);
}

enum JvmAccessModifier {
    PUBLIC,
    PRIVATE,
    PACKAGE,
}

enum JvmMemoryModifier {
    STATIC,
    LOCAL,
    MEMBER,
}

class JvmPackage extends JvmNode {
    final String name;
    private List<JvmNode> children = new ArrayList<>();

    JvmPackage(String name) {
        this.name = name;
    }

    void add(JvmNode n) {
        children.add(n);
    }

    List<JvmNode> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmClass extends JvmNode {
    final JvmAccessModifier access;
    final String name;
    private List<JvmNode> children = new ArrayList<>();

    JvmClass(JvmAccessModifier access, String name) {
        this.access = access;
        this.name = name;
    }

    void add(JvmNode n) {
        children.add(n);
    }

    List<JvmNode> getChildren() {
        return ImmutableList.copyOf(children);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmVariable extends JvmNode {
    final JvmAccessModifier access;
    final JvmMemoryModifier memory;
    final String name;
    final String type;

    static JvmVariable makeLocal(String name, String type) {
        return new JvmVariable(JvmAccessModifier.PRIVATE, JvmMemoryModifier.LOCAL, name, type);
    }

    JvmVariable(
            JvmAccessModifier access,
            JvmMemoryModifier memory,
            String name,
            String type) {
        this.access = access;
        this.memory = memory;
        this.name = name;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmFunction extends JvmNode {
    final JvmAccessModifier access;
    final JvmMemoryModifier memory;
    final String name;
    final List<JvmVariable> args;
    final String returnType;
    final JvmNode block;

    JvmFunction(
            JvmAccessModifier access,
            JvmMemoryModifier memory,
            String returnType,
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

// Includes literals and variable names.
class JvmValue extends JvmNode {
    String name;

    JvmValue(String name) {
        this.name = name;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmApply extends JvmNode {
    final String returnType;
    final String object;
    final String method;
    private List<JvmNode> args = new ArrayList<>();

    JvmApply(
            String returnType,
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

class JvmBinary extends JvmNode {
    final String op;
    JvmNode a;
    JvmNode b;

    JvmBinary(String op, JvmNode a, JvmNode b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmBlock extends JvmNode {
    private List<JvmNode> statements = new ArrayList<>();

    void add(JvmNode n) {
        statements.add(n);
    }

    List<JvmNode> getStatements() {
        return ImmutableList.copyOf(statements);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

class JvmReturn extends JvmNode {
    final JvmNode expr;

    JvmReturn(JvmNode expr) {
        this.expr = expr;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
