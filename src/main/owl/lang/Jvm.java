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

import static com.google.common.base.Preconditions.checkArgument;

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
    // Throws some IO(system) related exceptions
    void translate(Jvm jvm, File outDir, PrintStream echo) throws OwlException;
}

interface JvmVisitor<T> {
    default T visitError() {
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T accept(JvmNode node) {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return visitError();
        }
    }

    default T visit(JvmApply node) { return visitError(); }
    default T visit(JvmBlock node) { return visitError(); }
    default T visit(JvmCoerce node) { return visitError(); }
    default T visit(JvmClass node) { return visitError(); }
    default T visit(JvmComment node) { return visitError(); }
    default T visit(JvmGetLocal node) { return visitError(); }
    default T visit(JvmFunction node) { return visitError(); }
    default T visit(JvmLiteral node) { return visitError(); }
    default T visit(JvmOperator node) { return visitError(); }
    default T visit(JvmPackage node) { return visitError(); }
    default T visit(JvmPop node) { return visitError(); }
    default T visit(JvmPutLocal node) { return visitError(); }
    default T visit(JvmReturn node) { return visitError(); }
    default T visit(JvmVariable node) { return visitError(); }
}

abstract class JvmNode {
    abstract Object accept(JvmVisitor v);
}

enum AccessModifier {
    PACKAGE,
    PRIVATE,
    PUBLIC,
}

enum MemoryModifier {
    MEMBER,
    STATIC,
}

final class JvmPackage extends JvmNode {
    final String name;
    private List<String> imports = new ArrayList<>();
    private List<JvmClass> classes = new ArrayList<>();

    JvmPackage(String name) {
        this.name = name;
    }

    void addClass(JvmClass clazz) {
        classes.add(clazz);
    }

    List<JvmClass> getClasses() {
        return ImmutableList.copyOf(classes);
    }

    void addImport(String name) {
        imports.add(name);
    }

    List<String> getImports() {
        return ImmutableList.copyOf(imports);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmClass extends JvmNode {
    final AccessModifier access;
    final String name;
    private List<JvmVariable> variables = new ArrayList<>();
    private List<JvmFunction> functions = new ArrayList<>();

    JvmClass(AccessModifier access, String name) {
        this.access = access;
        this.name = name;
    }

    void addFunction(JvmFunction f) {
        functions.add(f);
    }

    void addVariable(JvmVariable v) {
        variables.add(v);
    }

    List<JvmFunction> getFunctions() {
        return ImmutableList.copyOf(functions);
    }

    List<JvmVariable> getVariables() {
        return ImmutableList.copyOf(variables);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmVariable extends JvmNode {
    final AstVariable variable;
    final AccessModifier access;
    final MemoryModifier memory;
    final JvmBlock block;

    static JvmVariable local(AstVariable v) {
        return new JvmVariable(v, null, null, null);
    }

    JvmVariable(
            AstVariable variable,
            AccessModifier access,
            MemoryModifier memory,
            JvmBlock block) {
        this.variable = variable;
        this.access = access;
        this.memory = memory;
        this.block = block;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmFunction extends JvmNode {
    final AstFunction function;
    final AccessModifier access;
    final MemoryModifier memory;
    final JvmBlock block;

    JvmFunction(
            AstFunction function,
            AccessModifier access,
            MemoryModifier memory,
            JvmBlock block) {
        this.function = function;
        this.access = access;
        this.memory = memory;
        this.block = block;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmLiteral extends JvmNode {
    final String text;
    final AstType type;

    JvmLiteral(String text, AstType type) {
        this.text = text;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmApply extends JvmNode {
    final String object;
    final String method;
    final int numArgs;
    final AstType returnType;

    JvmApply(
            String object,
            String method,
            int numArgs,
            AstType returnType) {
        this.object = object;
        this.method = method;
        this.numArgs = numArgs;
        this.returnType = returnType;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmOperator extends JvmNode {
    final int arity;
    final String op;
    final AstType returnType;

    JvmOperator(int arity, String op, AstType returnType) {
        checkArgument(1 <= arity && arity <= 2, "arity");
        this.arity = arity;
        this.op = op;
        this.returnType = returnType;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmGetLocal extends JvmNode {
    final int index;

    JvmGetLocal(int index) {
        checkArgument(index >= 0);
        this.index = index;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmPutLocal extends JvmNode {
    final int index;

    JvmPutLocal(int index) {
        checkArgument(index >= 0);
        this.index = index;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmBlock extends JvmNode {
    private List<JvmNode> instr = new ArrayList<>();

    void add(JvmNode node) {
        instr.add(node);
    }

    List<JvmNode> getInstructions() {
        return ImmutableList.copyOf(instr);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmReturn extends JvmNode {
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

final class JvmPop extends JvmNode {
    JvmPop() {}

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}

final class JvmCoerce extends JvmNode {
    final AstType srcType;
    final AstType dstType;

    JvmCoerce(AstType srcType, AstType dstType) {
        this.srcType = srcType;
        this.dstType = dstType;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
