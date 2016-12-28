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

    static final OverloadNameMap OVERLOAD_MAP;
    static {
        OVERLOAD_MAP = new OverloadNameMap();
        try {
            OVERLOAD_MAP.put(makeFn("+", AstType.STRING, AstType.STRING, AstType.STRING));
            OVERLOAD_MAP.put(makeFn("+", AstType.I32, AstType.I32, AstType.I32));
            OVERLOAD_MAP.put(makeFn("+", AstType.I64, AstType.I64, AstType.I64));
            OVERLOAD_MAP.put(makeFn("*", AstType.I32, AstType.I32, AstType.I32));
            OVERLOAD_MAP.put(makeFn("/", AstType.I32, AstType.I32, AstType.I32));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.BOOL));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.CHAR));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.I32));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.I64));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.F32));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.F64));
            OVERLOAD_MAP.put(makeFn("println", AstType.NONE, AstType.STRING));
        } catch (OwlException e) {
            throw new IllegalStateException(e);
        }
    }

    static final NameMap<AstAbstractType> ABSTRACT_TYPE_MAP;
    static {
        ABSTRACT_TYPE_MAP = new NameMap<>();
        try {
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.BOOL.getName(), AstPrimitiveType.BOOL);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.CHAR.getName(), AstPrimitiveType.CHAR);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.F32.getName(), AstPrimitiveType.F32);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.F64.getName(), AstPrimitiveType.F64);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.I32.getName(), AstPrimitiveType.I32);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.I64.getName(), AstPrimitiveType.I64);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.NONE.getName(), AstPrimitiveType.NONE);
            ABSTRACT_TYPE_MAP.put(AstPrimitiveType.STRING.getName(), AstPrimitiveType.STRING);
        } catch (OwlException e) {
            throw new IllegalStateException(e);
        }
    }

    private static AstFunction makeFn(String op, AstType returnType, AstType... argTypes) {
        List<AstVariable> args = new ArrayList<>();
        int i = 0;
        for (AstType t : argTypes) {
            args.add(new AstVariable(null, "_" + i++, t, null));
        }
        return new AstFunction("", op, args, returnType, null);
    }
}
