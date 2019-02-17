/*
 * Copyright 2017 Igor Demura
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

import java.util.List;
import java.util.Objects;

final class AstObject extends AstAbstractType {
    final String moduleName;
    final String name;
    final List<AstVariable> fields;

    AstObject(String moduleName, String name, List<AstVariable> fields) {
        this.moduleName = moduleName;
        this.name = name;
        this.fields = fields;
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
    <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    List<TypeParam> getTypeParams() {
        return Util.listOf();
    }

    @Override
    public String toString() {
        return "object type " + moduleName + "::" + name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, name);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof AstObject) {
            AstObject otherObject = (AstObject) other;
            return moduleName.equals(otherObject.moduleName) && name.equals(otherObject.name);
        }
        return false;
    }
}
