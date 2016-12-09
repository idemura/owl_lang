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

import static owl.lang.TypeUtil.makeFnType;

final class Runtime {
    private Runtime() {}

    static final String CLASS_NAME = "RT";
    static final EntityMap ENTITY_MAP;
    static {
        ENTITY_MAP = new EntityMap();
        AstType binaryI32 = makeFnType(AstType.I32, AstType.I32, AstType.I32);
        AstType binaryI64 = makeFnType(AstType.I64, AstType.I64, AstType.I64);
        try {
            ENTITY_MAP.put(new FunctionEntity("", "+", binaryI32, true));
            ENTITY_MAP.put(new FunctionEntity("", "+", binaryI64, true));
            ENTITY_MAP.put(new FunctionEntity("", "*", binaryI32, true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.BOOL, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.CHAR, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.I32, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.I64, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.F32, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.F64, AstType.NONE), true));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.STRING, AstType.NONE), true));
        } catch (OwlException e) {
            throw new IllegalArgumentException(e);
        }
        ENTITY_MAP.freeze();
    }
}
