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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

// Generic type with parameters
final class AstType extends AstNode {
    static final String ARRAY = "Array";
    static final String FUNCTION = "Fn";

    static final AstType BOOL = new AstType("Bool");
    static final AstType CHAR = new AstType("Char");
    static final AstType F32 = new AstType("F32");
    static final AstType F64 = new AstType("F64");
    static final AstType I32 = new AstType("I32");
    static final AstType I64 = new AstType("I64");
    static final AstType NONE = new AstType("None");
    static final AstType STRING = new AstType("String");

    final String name;
    final List<AstType> args = new ArrayList<>();

    static AstType arrayOf(AstType type) {
        return new AstType(ARRAY, ImmutableList.of(type));
    }

    static AstType functionOf(List<AstType> args) {
        return new AstType(FUNCTION, args);
    }

    AstType(String name) {
        this.name = name;
    }

    AstType(String name, List<AstType> args) {
        this(name);
        for (AstType t : args) {
            this.args.add(t);
        }
    }

    boolean isArray() {
        return name.equals(ARRAY);
    }

    boolean isFunction() {
        return name.equals(FUNCTION);
    }

    AstType getReturnType() {
        checkState(isFunction());
        return args.get(args.size() - 1);
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public String toString() {
        if (args.isEmpty()) {
            return name;
        } else {
            List<String> pieces = args.stream().map(AstType::toString).collect(Collectors.toList());
            return name + "(" + String.join(", ", pieces) + ")";
        }
    }

    @Override
    public int hashCode() {
        int h = name.hashCode();
        for (AstType t : args) {
            h = Objects.hash(h, t);
        }
        return h;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstType) {
            AstType otherType = (AstType) other;
            if (!Objects.equals(name, otherType.name)) {
                return false;
            }
            if (!Objects.equals(args, otherType.args)) {
                return false;
            }
            return true;
        }
        return false;
    }

    String javaType() {
        if (isArray()) {
            return args.get(0).javaType() + "[]";
        }
        if (args.size() > 0) {
            throw new UnsupportedOperationException("java type name on generic");
        }
        if (equals(AstType.BOOL)) {
            return "boolean";
        } else if (equals(AstType.CHAR)) {
            return "char";
        } else if (equals(AstType.I32)) {
            return "int";
        } else if (equals(AstType.I64)) {
            return "long";
        } else if (equals(AstType.F32)) {
            return "float";
        } else if (equals(AstType.F64)) {
            return "double";
        } else if (equals(AstType.STRING)) {
            return "String";
        } else if (equals(AstType.NONE)) {
            return "void";
        } else {
            return name;
        }
    }
}
