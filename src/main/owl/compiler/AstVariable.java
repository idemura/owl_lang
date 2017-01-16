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
package owl.compiler;

import java.util.ArrayList;
import java.util.List;

final class AstVariable extends AstNode
        implements Entity {
    static final int MODULE = 0;
    static final int FIELD = 1;
    static final int LOCAL = 2;

    static abstract class Storage {
        abstract int getKind();
    }

    static final class Module extends Storage {
        @Override
        int getKind() { return MODULE; }
    }

    static final class Field extends Storage {
        @Override
        int getKind() { return FIELD; }
    }

    static final class Local extends Storage {
        int index = -1;

        @Override
        int getKind() { return LOCAL; }
    }

    private final Storage storage;
    private final String moduleName;
    private final String name;
    AstType type;
    AstNode expr;

    AstVariable(Storage storage, String moduleName, String name, AstType type, AstNode expr) {
        this.storage = storage;
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("variable");
        if (moduleName == null) {
            list.add(name);
        } else {
            list.add(moduleName + "::" + name);
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
    public String getJvmDescriptor() {
        return getType().getJvmType();
    }

    @Override
    public int hashCode() {
        return Entity.getHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
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

    Storage getStorage() {
        return storage;
    }
}
