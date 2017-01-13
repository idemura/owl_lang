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

final class Runtime {
    private Runtime() {}

    static final String NAME = "owl/runtime/RT";

    static NameMap<AstAbstractType> getAbstractTypes() {
        return ABSTRACT_TYPES.clone();
    }

    static OverloadNameMap getFunctions() {
        return FUNCTIONS.clone();
    }

    private static final NameMap<AstAbstractType> ABSTRACT_TYPES = new NameMap<>();
    static {
        ABSTRACT_TYPES.put(AstScalarType.BOOL);
        ABSTRACT_TYPES.put(AstScalarType.CHAR);
        ABSTRACT_TYPES.put(AstScalarType.F32);
        ABSTRACT_TYPES.put(AstScalarType.F64);
        ABSTRACT_TYPES.put(AstScalarType.I32);
        ABSTRACT_TYPES.put(AstScalarType.I64);
        ABSTRACT_TYPES.put(AstScalarType.NONE);
        ABSTRACT_TYPES.put(AstScalarType.STRING);
        ABSTRACT_TYPES.put(AstArrayType.INSTANCE);
    }

    private static final OverloadNameMap FUNCTIONS = new OverloadNameMap();
    static {
        // Unary
        FUNCTIONS.put(function("+", AstType.I32, AstType.I32));
        FUNCTIONS.put(function("+", AstType.I64, AstType.I64));
        FUNCTIONS.put(function("-", AstType.I32, AstType.I32));
        FUNCTIONS.put(function("-", AstType.I64, AstType.I64));
        FUNCTIONS.put(function("~", AstType.I32, AstType.I32));
        FUNCTIONS.put(function("~", AstType.I64, AstType.I64));

        // Binary arithmetic
        FUNCTIONS.put(function("+", AstType.STRING, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("+", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("+", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("-", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("-", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("*", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("*", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("/", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("/", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("//", AstType.F64, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("//", AstType.F64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("%", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("%", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("<<", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("<<", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function(">>", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function(">>", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function(">>>", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function(">>>", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("&", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("&", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("^", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("^", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("|", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("|", AstType.I64, AstType.I64, AstType.I64));

        // Comparisons
        FUNCTIONS.put(function("<", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("<", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("<", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("<=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("<=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("<=", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function(">", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function(">", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function(">", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function(">=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function(">=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function(">=", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("==", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(function("==", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("==", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("==", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("!=", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(function("!=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("!=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(function("!=", AstType.BOOL, AstType.STRING, AstType.STRING));

        // Boolean operators
        FUNCTIONS.put(function("!", AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(function("&&", AstType.BOOL, AstType.BOOL, AstType.BOOL));

        FUNCTIONS.put(function("compare", AstType.I32, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("concat", AstType.STRING, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(function("size", AstType.I32, AstType.STRING));
        FUNCTIONS.put(function("fdiv", AstType.F64, AstType.I32, AstType.I32));
        FUNCTIONS.put(function("fdiv", AstType.F64, AstType.I64, AstType.I64));

        FUNCTIONS.put(function("println", AstType.NONE, AstType.BOOL));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.CHAR));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.I32));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.I64));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.F32));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.F64));
        FUNCTIONS.put(function("println", AstType.NONE, AstType.STRING));
    }

    private static AstFunction function(String op, AstType returnType, AstType... argTypes) {
        List<AstVariable> args = new ArrayList<>();
        int i = 0;
        for (AstType t : argTypes) {
            TypeMatcher.run(t, ABSTRACT_TYPES, null);
            args.add(new AstVariable(null, "_" + i++, t, null));
        }
        return new AstFunction("", op, args, returnType, null);
    }
}
