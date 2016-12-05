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

import java.util.Objects;

import static owl.lang.TypeUtil.fnEqualSignatures;

abstract class Entity {
    protected String name;
    protected String moduleName;
    protected AstType type;

    String getName() {
        return name;
    }

    String getModuleName() {
        return moduleName;
    }

    AstType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, moduleName);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Entity) {
            Entity otherEnt = (Entity) other;
            return name.equals(otherEnt.name) && moduleName.equals(otherEnt.moduleName);
        } else {
            throw new IllegalArgumentException("entity expected");
        }
    }
}

// Basically function signature
class FunctionEntity extends Entity {
    FunctionEntity(String moduleName, String name, AstType type) {
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Function " + moduleName + " " + name + ": " + type;
    }

    @Override
    public boolean equals(Object other) {
        if (super.equals(other)) {
            if (other instanceof FunctionEntity) {
                return fnEqualSignatures(type, ((Entity) other).type);
            }
            return true;
        }
        return false;
    }
}

enum VariableScope {
    MODULE,
    FUNCTION,
}

class VariableEntity extends Entity {
    VariableScope scope;

    VariableEntity(String moduleName, String name, AstType type, VariableScope scope) {
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Variable " + moduleName + " " + name + ": " + type;
    }
}
