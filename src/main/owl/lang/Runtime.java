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

import java.util.ArrayList;
import java.util.List;

final class Runtime {
    private Runtime() {}

    static final NameMap<AstAbstractType> ABSTRACT_TYPES = new NameMap<>();
    static {
        ABSTRACT_TYPES.put(AstScalarType.BOOL.getName(), AstScalarType.BOOL);
        ABSTRACT_TYPES.put(AstScalarType.CHAR.getName(), AstScalarType.CHAR);
        ABSTRACT_TYPES.put(AstScalarType.F32.getName(), AstScalarType.F32);
        ABSTRACT_TYPES.put(AstScalarType.F64.getName(), AstScalarType.F64);
        ABSTRACT_TYPES.put(AstScalarType.I32.getName(), AstScalarType.I32);
        ABSTRACT_TYPES.put(AstScalarType.I64.getName(), AstScalarType.I64);
        ABSTRACT_TYPES.put(AstScalarType.NONE.getName(), AstScalarType.NONE);
        ABSTRACT_TYPES.put(AstScalarType.STRING.getName(), AstScalarType.STRING);
        ABSTRACT_TYPES.put(AstArrayType.INSTANCE.getName(), AstArrayType.INSTANCE);
    }

    static final OverloadNameMap FUNCTIONS = new OverloadNameMap();
    static {
        FUNCTIONS.put(makeFn("+", AstType.STRING, AstType.STRING, AstType.STRING));
        FUNCTIONS.put(makeFn("+", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(makeFn("+", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(makeFn("-", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(makeFn("-", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(makeFn("*", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(makeFn("*", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(makeFn("/", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(makeFn("/", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(makeFn("%", AstType.I32, AstType.I32, AstType.I32));
        FUNCTIONS.put(makeFn("%", AstType.I64, AstType.I64, AstType.I64));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.BOOL));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.CHAR));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.I32));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.I64));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.F32));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.F64));
        FUNCTIONS.put(makeFn("println", AstType.NONE, AstType.STRING));
    }

    private static AstFunction makeFn(String op, AstType returnType, AstType... argTypes) {
        List<AstVariable> args = new ArrayList<>();
        int i = 0;
        for (AstType t : argTypes) {
            TypeMatcher.run(t, ABSTRACT_TYPES, null);
            args.add(new AstVariable(null, "_" + i++, t, null));
        }
        return new AstFunction("", op, args, returnType, null);
    }
}
