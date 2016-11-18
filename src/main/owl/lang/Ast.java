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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


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
    default void visit(AstBlock node) {}
    default void visit(AstApply node) {}
    default void visit(AstConstant node) {}
    default void visit(AstMember node) {}
    default void visit(AstIf node) {}
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


class AstName extends AstNode {
    String name = "";

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
}


class AstFunction extends AstNode {
    String name = "";
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


class AstIf extends AstNode {
    List<AstNode> condition = new ArrayList<>();
    List<AstNode> branch = new ArrayList<>();

    @Override
    public void accept(AstVisitor v) {
        v.visit(this);
    }
}


final class AstUtil {
    private AstUtil() {}
}
