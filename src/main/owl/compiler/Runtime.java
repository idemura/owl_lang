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

    private static final NameMap<AstAbstractType> ABSTRACT_TYPES = new NameMap<>(0);
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
        FUNCTIONS.put(fn("+", AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("+", AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("-", AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("-", AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("~", AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("~", AstType.I64, AstType.I64));

        // Binary arithmetic
        FUNCTIONS.put(fnWithMethod("+", "concat", AstType.STRING, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("+", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("+", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("-", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("-", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("*", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("*", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("/", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("/", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fnWithMethod("//", "fdiv", AstType.F64, AstType.I32, AstType.I32));
        FUNCTIONS.put(fnWithMethod("//", "fdiv", AstType.F64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("%", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("%", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("<<", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("<<", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn(">>", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn(">>", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn(">>>", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn(">>>", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("&", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("&", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("^", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("^", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("|", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("|", AstType.I64, AstType.I64, AstType.I64));

        // Comparisons
        FUNCTIONS.put(fn("<", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("<", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("<", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("<=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("<=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("<=", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn(">", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn(">", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn(">", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn(">=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn(">=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn(">=", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("==", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(fn("==", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("==", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("==", AstType.BOOL, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("!=", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(fn("!=", AstType.BOOL, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("!=", AstType.BOOL, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("!=", AstType.BOOL, AstType.STRING, AstType.STRING));

        // Boolean operators
        FUNCTIONS.put(fn("!", AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(fn("&&", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(fn("^^", AstType.BOOL, AstType.BOOL, AstType.BOOL));
        FUNCTIONS.put(fn("||", AstType.BOOL, AstType.BOOL, AstType.BOOL));

        FUNCTIONS.put(fnWithMethod("assert", "owl_assert", AstType.NONE, AstType.BOOL));
        FUNCTIONS.put(fn("compare", AstType.I32, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("concat", AstType.STRING, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(fn("fdiv", AstType.F64, AstType.I32, AstType.I32));
        FUNCTIONS.put(fn("fdiv", AstType.F64, AstType.I64, AstType.I64));
        FUNCTIONS.put(fn("size", AstType.I32, AstType.STRING));

        FUNCTIONS.put(fn("println", AstType.NONE, AstType.BOOL));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.CHAR));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.I32));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.I64));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.F32));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.F64));
        FUNCTIONS.put(fn("println", AstType.NONE, AstType.STRING));
    }

    private static AstFunction fn(String name, AstType returnType, AstType... argTypes) {
        return fnWithMethod(name, null, returnType, argTypes);
    }

    private static AstFunction fnWithMethod(String name, String methodName,
            AstType returnType,
            AstType... argTypes) {
        List<AstVariable> args = new ArrayList<>();
        int i = 0;
        for (AstType t : argTypes) {
            TypeMatcher.run(t, ABSTRACT_TYPES, null);
            args.add(new AstVariable(new AstVariable.Local(), null, "_" + i++, t, null));
        }
        return new AstFunction("", name, methodName, args, returnType, null);
    }
}
