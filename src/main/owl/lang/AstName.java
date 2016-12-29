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

final class AstName extends AstNode
        implements Typed {
    String name;
    Entity entity;

    AstName() {}

    AstName(String name) {
        this();
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstName) {
            AstName otherName = (AstName) other;
            return name.equals(otherName.name);
        }
        return false;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return entity.getType();
    }
}
