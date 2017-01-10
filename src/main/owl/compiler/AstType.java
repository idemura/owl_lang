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
package owl.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Generic type with parameters
final class AstType extends AstNode {
    private static final String ARRAY = "Array";
    private static final String FUNCTION = "Fn";

    static final int kNONE = 0;
    static final int kBOOL = 1;
    static final int kCHAR = 2;
    static final int kF32 = 3;
    static final int kF64 = 4;
    static final int kI32 = 5;
    static final int kI64 = 6;
    static final int kREF = 7;

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
    AstAbstractType abstractType;

    static AstType arrayOf(AstType type) {
        return new AstType(ARRAY, Util.listOf(type));
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
        Util.check(isFunction());
        return args.get(args.size() - 1);
    }

    @Override
    public Object accept(AstVisitor v) {
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
            if (!name.equals(otherType.name)) {
                return false;
            }
            if (!args.equals(otherType.args)) {
                return false;
            }
            return true;
        }
        return false;
    }

    // Not sure if needed
    static boolean equalSignatures(AstType a, AstType b) {
        Util.check(a.isFunction() && b.isFunction(), "function type expected");
        if (a.args.size() != b.args.size()) {
            return false;
        }
        // Compare all except last (return type)
        for (int i = 0; i < a.args.size() - 1; i++) {
            if (!a.args.get(i).equals(b.args.get(i))) {
                return false;
            }
        }
        return true;
    }

    boolean acceptsArgs(List<AstType> argsIn) {
        Util.check(isFunction(), "function type expected");
        // Do not count return type
        if (args.size() - 1 != argsIn.size()) {
            return false;
        }
        for (int i = 0; i < argsIn.size(); i++) {
            if (!argsIn.get(i).equals(args.get(i))) {
                return false;
            }
        }
        return true;
    }

    // This is exactly the same relationship as implicitly convertible.
    boolean canAssignTo(AstType dst) {
        if (this.equals(dst)) {
            return true;
        }
        if (this.equals(AstType.I32) && dst.equals(AstType.I64)) {
            return true;
        }
        // TODO: Bool to integers
        return false;
    }

    boolean canCoerceTo(AstType dst) {
        if (this.equals(dst)) {
            return true;
        }
        if (this.equals(AstType.I32) && dst.equals(AstType.I64)) {
            return true;
        }
        if (this.equals(AstType.I64) && dst.equals(AstType.I32)) {
            return true;
        }
        // TODO: Bool to integers
        return false;
    }

    static AstType of(AstNode node) {
        if (node == null) {
            return AstType.NONE;
        }
        return ((Typed) node).getType();
    }

    int getJvmLocalType() {
        if (equals(NONE)) return kNONE;
        if (equals(BOOL)) return kBOOL;
        if (equals(CHAR)) return kCHAR;
        if (equals(I32)) return kI32;
        if (equals(I64)) return kI64;
        if (equals(F32)) return kF32;
        if (equals(F64)) return kF64;
        return kREF;
    }

    String getJvmType() {
        if (isArray()) {
            return "[" + args.get(0).getJvmType();
        }
        if (args.size() > 0) {
            throw new UnsupportedOperationException("java type name on generic");
        }
        if (equals(AstType.BOOL)) {
            return "Z";
        } else if (equals(AstType.CHAR)) {
            return "C";
        } else if (equals(AstType.I32)) {
            return "I";
        } else if (equals(AstType.I64)) {
            return "J";
        } else if (equals(AstType.F32)) {
            return "F";
        } else if (equals(AstType.F64)) {
            return "D";
        } else if (equals(AstType.STRING)) {
            return getJvmClassType("java.lang.String");
        } else if (equals(AstType.NONE)) {
            return "V";
        } else {
            return getJvmClassType(name);
        }
    }

    private static String getJvmClassType(String type) {
        return "L" + type.replace('.', '/') + ";";
    }
}
