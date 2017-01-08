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

final class AstArrayType extends AstAbstractType {
    static final AstArrayType INSTANCE = new AstArrayType();
    static final String NAME = "Array";

    private final List<TypeMatcher.ParamMatcher> params = Util.listOf(new TypeMatcher.ParamMatcher());

    private AstArrayType() {}

    @Override
    public String getModuleName() {
        return "";
    }

    @Override
    public String getName() {
        return "Array";
    }

    @Override
    Object accept(AstVisitor v) {
        // Should not visit, because never defined in Owl code
        throw new UnsupportedOperationException("accept");
    }

    @Override
    List<TypeMatcher.ParamMatcher> getParamMatchers() {
        return params;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public int hashCode() {
        return NAME.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AstArrayType;
    }
}
