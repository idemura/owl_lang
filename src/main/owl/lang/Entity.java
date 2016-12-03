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

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

abstract class Entity {
    protected String moduleName;
    protected String name;

    String getModuleName() {
        return moduleName;
    }

    String getName() {
        return name;
    }
}

// Basically function signature.
class FunctionEntity extends Entity {
    List<AstType> argumentTypes = new ArrayList<>();
    AstType returnType = AstType.None;

    FunctionEntity(String moduleName, String name) {
        this.moduleName = moduleName;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Function " + moduleName + " " + name + "(" +
                String.join(", ", argumentTypes.stream().map(AstType::toString).collect(toList())) +
                "): " + returnType;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Entity)) {
            throw new IllegalArgumentException("entity expected");
        }
        if (other instanceof FunctionEntity) {
            FunctionEntity otherFun = (FunctionEntity) other;
            if (!name.equals(otherFun.name)) {
                return false;
            }
            if (argumentTypes.size() != otherFun.argumentTypes.size()) {
                return false;
            }
            for (int i = 0; i < argumentTypes.size(); i++) {
                if (!argumentTypes.get(i).equals(otherFun.argumentTypes.get(i))) {
                    return false;
                }
            }
            // Return type does not participate in equality resolution.
            return true;
        }
        return name.equals(((Entity) other).getName());
    }
}

class VariableEntity extends Entity {
    VariableEntity(String moduleName, String name) {
        this.moduleName = moduleName;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Variable " + moduleName + " " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Entity)) {
            throw new IllegalArgumentException("entity expected");
        }
        if (other instanceof VariableEntity) {
            VariableEntity otherVar = (VariableEntity) other;
            return name.equals(otherVar.name);
        }
        return name.equals(((Entity) other).getName());
    }
}
