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

import com.google.common.collect.ImmutableList;

import java.util.List;

// Base class for all abstract types. Abstract type is a (parametric) type programmer defines in the source code or
// primitive types + array type.
abstract class AstAbstractType extends AstNode {
    abstract String getModuleName();
    abstract String getName();
    abstract List<TypeMatcher.ParamMatcher> getParamMatchers();
}

final class AstScalarType extends AstAbstractType
        implements Named {
    static final AstScalarType BOOL = new AstScalarType("Bool");
    static final AstScalarType CHAR = new AstScalarType("Char");
    static final AstScalarType F32 = new AstScalarType("F32");
    static final AstScalarType F64 = new AstScalarType("F64");
    static final AstScalarType I32 = new AstScalarType("I32");
    static final AstScalarType I64 = new AstScalarType("I64");
    static final AstScalarType NONE = new AstScalarType("None");
    static final AstScalarType STRING = new AstScalarType("String");

    final String name;

    private AstScalarType(String name) {
        this.name = name;
    }

    @Override
    public String getModuleName() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    Object accept(AstVisitor v) {
        // Should not visit, because never defined in Owl code
        throw new UnsupportedOperationException("accept");
    }

    @Override
    List<TypeMatcher.ParamMatcher> getParamMatchers() {
        return ImmutableList.of();
    }

    @Override
    public String toString() {
        return "Scalar " + name;
    }

    @Override
    public int hashCode() {
        // Makes hash maps output stable
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        // Only singletons of this class accessible from outside
        return this == other;
    }
}

final class AstArrayType extends AstAbstractType
        implements Named {
    static final AstArrayType INSTANCE = new AstArrayType();
    static final String NAME = "Array";

    private final List<TypeMatcher.ParamMatcher> params = ImmutableList.of(new TypeMatcher.ParamMatcher());

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
