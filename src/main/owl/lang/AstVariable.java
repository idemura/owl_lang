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

import static com.google.common.base.Preconditions.checkState;

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
    public Object accept(AstVisitor v) {
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

    void setExpr(AstNode expr) {
        this.expr = expr;
    }
}
