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

abstract class TypeParam {
    abstract boolean match(AstType type);

    static TypeParam getScalarTypeParam() {
        return new ScalarTypeParam();
    }

    private final static class ScalarTypeParam extends TypeParam {
        boolean match(AstType type) {
            return type.isGeneric() || type.abstractType instanceof AstScalar;
        }

        @Override
        public String toString() {
            return "type parameter (scalar)";
        }
    }
}
