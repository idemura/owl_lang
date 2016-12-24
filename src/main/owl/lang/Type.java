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
final class Type {
    static final String ARRAY = "Array";
    static final String FUNCTION = "Fn";

    static final Type BOOL = new Type("Bool");
    static final Type CHAR = new Type("Char");
    static final Type F32 = new Type("F32");
    static final Type F64 = new Type("F64");
    static final Type I32 = new Type("I32");
    static final Type I64 = new Type("I64");
    static final Type NONE = new Type("None");
    static final Type STRING = new Type("String");

    // TODO: Module name
    // final String moduleName;
    final String name;
    final List<Type> args = new ArrayList<>();

    static Type arrayOf(Type type) {
        return new Type(ARRAY, ImmutableList.of(type));
    }

    static Type functionOf(List<Type> args) {
        return new Type(FUNCTION, args);
    }

    Type(String name) {
        this.name = name;
    }

    Type(String name, List<Type> args) {
        this(name);
        for (Type t : args) {
            this.args.add(t);
        }
    }

    boolean isArray() {
        return name.equals(ARRAY);
    }

    boolean isFunction() {
        return name.equals(FUNCTION);
    }

    Type returnType() {
        checkState(isFunction());
        return args.get(args.size() - 1);
    }

    void add(Type node) {
        args.add(node);
    }

    @Override
    public String toString() {
        if (args.isEmpty()) {
            return name;
        } else {
            List<String> pieces = args.stream().map(Type::toString).collect(Collectors.toList());
            return name + "(" + String.join(", ", pieces) + ")";
        }
    }

    @Override
    public int hashCode() {
        int h = name.hashCode();
        for (Type t : args) {
            h = Objects.hash(h, t.hashCode());
        }
        return h;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Type) {
            Type otherType = (Type) other;
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
        if (equals(Type.BOOL)) {
            return "boolean";
        } else if (equals(Type.CHAR)) {
            return "char";
        } else if (equals(Type.I32)) {
            return "int";
        } else if (equals(Type.I64)) {
            return "long";
        } else if (equals(Type.F32)) {
            return "float";
        } else if (equals(Type.F64)) {
            return "double";
        } else if (equals(Type.STRING)) {
            return "String";
        } else if (equals(Type.NONE)) {
            return "void";
        } else {
            return name;
        }
    }
}
