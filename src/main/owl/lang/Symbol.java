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

abstract class Symbol {
    abstract String getName();
}

// Basically function signature.
class FunctionSymbol extends Symbol {
    String name;
    List<AstType> argumentTypes = new ArrayList<>();
    AstType returnType = AstType.None;

    FunctionSymbol(String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionSymbol " + name + "(" +
                String.join(", ", argumentTypes.stream().map(AstType::toString).collect(toList())) +
                "): " + returnType;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Symbol)) {
            throw new IllegalArgumentException("Symbol expected");
        }
        if (other instanceof FunctionSymbol) {
            FunctionSymbol otherFun = (FunctionSymbol) other;
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
        return name.equals(((Symbol) other).getName());
    }
}

class VariableSymbol extends Symbol {
    private String name;

    VariableSymbol(String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "VariableSymbol " + name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Symbol)) {
            throw new IllegalArgumentException("Symbol expected");
        }
        if (other instanceof VariableSymbol) {
            VariableSymbol otherVar = (VariableSymbol) other;
            return name.equals(otherVar.name);
        }
        return name.equals(((Symbol) other).getName());
    }
}
