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

    static final String CLASS_NAME = "RT";
    static final OverloadEntityMap ENTITY_MAP;
    static {
        ENTITY_MAP = new OverloadEntityMap();
        try {
            ENTITY_MAP.put(makeFn("+", AstType.STRING, AstType.STRING, AstType.STRING));
            ENTITY_MAP.put(makeFn("+", AstType.I32, AstType.I32, AstType.I32));
            ENTITY_MAP.put(makeFn("+", AstType.I64, AstType.I64, AstType.I64));
            ENTITY_MAP.put(makeFn("*", AstType.I32, AstType.I32, AstType.I32));
            ENTITY_MAP.put(makeFn("/", AstType.I32, AstType.I32, AstType.I32));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.BOOL));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.CHAR));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.I32));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.I64));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.F32));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.F64));
            ENTITY_MAP.put(makeFn("println", AstType.NONE, AstType.STRING));
        } catch (OwlException e) {
            throw new IllegalArgumentException(e);
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
