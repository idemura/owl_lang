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

class Entity {
    final String moduleName;
    final String name;
    final AstType type;

    Entity(String moduleName, String name, AstType type) {
        this.moduleName = moduleName;
        this.name = name;
        this.type = type;
    }

    boolean isRT() {
        return moduleName.isEmpty();
    }

    boolean isFunction() {
        return type.isFunction();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Entity) {
            Entity otherEnt = (Entity) other;
            return name.equals(otherEnt.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return (moduleName == null || moduleName.isEmpty()? "": moduleName + ".") + name + ": " + type;
    }
}
