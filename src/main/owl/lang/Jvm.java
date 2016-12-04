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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

final class Jvm {
    JvmNode root;

    Jvm(JvmNode root) {
        this.root = root;
    }

    void accept(JvmVisitor v) {
        if (root != null) {
            root.accept(v);
        }
    }
}

interface JvmTranslator {
    void translate(Jvm jvm, OutputStream out) throws OwlException;
}

interface JvmVisitor {
    default void visit(JvmPackage n) {}
    default void visit(JvmClass n) {}
    default void visit(JvmFunction n) {}
    default void visit(JvmVariable n) {}
    default void visit(JvmValue n) {}
    default void visit(JvmApply n) {}
    default void visit(JvmBinary n) {}
}

abstract class JvmNode {
    abstract void accept(JvmVisitor v);
}

enum JvmAccess {
    PUBLIC,
    PRIVATE,
    PACKAGE,
}

class JvmPackage extends JvmNode {
    String name;
    List<JvmNode> members = new ArrayList<>();

    JvmPackage(String name) {
        this.name = name;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

class JvmClass extends JvmNode {
    JvmAccess access;
    String name;
    List<JvmNode> members = new ArrayList<>();

    JvmClass(JvmAccess access, String name) {
        this.access = access;
        this.name = name;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

class JvmVariable extends JvmNode {
    JvmAccess access;
    String name;
    String type;

    JvmVariable(JvmAccess access, String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

class JvmFunction extends JvmNode {
    JvmAccess access;
    boolean isStatic;
    String name;
    List<JvmVariable> args;
    String returnType;
    JvmNode block;

    JvmFunction(
            JvmAccess access,
            boolean isStatic,
            String name,
            List<JvmVariable> args,
            String returnType,
            JvmNode block) {
        this.access = access;
        this.isStatic = isStatic;
        this.name = name;
        this.args = args;
        this.returnType = returnType;
        this.block = block;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

// Includes literals and variable names.
class JvmValue extends JvmNode {
    String name;

    JvmValue(String name) {
        this.name = name;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

class JvmApply extends JvmNode {
    String object;
    String method;
    String result;
    List<String> params;
    List<JvmNode> child = new ArrayList<>();


    JvmApply(String result, String object, String method, List<String> params) {
        this.object = object;
        this.method = method;
        this.result = result;
        this.params = params;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}

class JvmBinary extends JvmNode {
    String result;
    String op;
    String a;
    String b;
    JvmNode aNode;
    JvmNode bNode;

    JvmBinary(String result, String op, String a, String b) {
        this.result = result;
        this.op = op;
        this.a = a;
        this.b = b;
    }

    @Override
    void accept(JvmVisitor v) {
        v.visit(this);
    }
}
