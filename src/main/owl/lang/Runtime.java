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
    static final OverloadEntityMap ENTITY_MAP;
    static {
        ENTITY_MAP = new OverloadEntityMap();
        AstType binaryI32 = makeFnType(AstType.I32, AstType.I32, AstType.I32);
        AstType binaryI64 = makeFnType(AstType.I64, AstType.I64, AstType.I64);
        try {
            ENTITY_MAP.put(new Entity("", "+", binaryI32));
            ENTITY_MAP.put(new Entity("", "+", binaryI64));
            ENTITY_MAP.put(new Entity("", "*", binaryI32));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.BOOL, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.CHAR, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.I32, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.I64, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.F32, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.F64, AstType.NONE)));
            ENTITY_MAP.put(new Entity("", "println", makeFnType(AstType.STRING, AstType.NONE)));
        } catch (OwlException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
