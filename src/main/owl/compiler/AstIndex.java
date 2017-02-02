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

final class AstIndex extends AstNode
        implements Typed {
    AstNode array, index;

    AstIndex(AstNode array, AstNode index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        AstType type = AstType.of(array);
        if (type.isArray()) {
            return type.args.get(0);
        }
        if (type.equals(AstType.STRING)) {
            return AstType.CHAR;
        }
        Util.checkFail("invalid type of array");
        return type;
    }
}
