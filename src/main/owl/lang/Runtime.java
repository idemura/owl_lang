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

import static owl.lang.TypeUtil.makeFnType;

final class Runtime {
    private Runtime() {}

    static final String CLASS_NAME = "OwlRT";
    static final EntityMap ENTITY_MAP;
    static {
        ENTITY_MAP = new EntityMap();
        AstType binaryI32 = makeFnType(AstType.I32, AstType.I32, AstType.I32);
        AstType binaryI64 = makeFnType(AstType.I64, AstType.I64, AstType.I64);
        try {
            ENTITY_MAP.put(new FunctionEntity("", "+", binaryI32));
            ENTITY_MAP.put(new FunctionEntity("", "+", binaryI64));
            ENTITY_MAP.put(new FunctionEntity("", "*", binaryI32));
            // Should be consistent with generate!
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.BOOL, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.CHAR, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.I32, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.I64, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.F32, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.F64, AstType.NONE)));
            ENTITY_MAP.put(new FunctionEntity("", "println", makeFnType(AstType.STRING, AstType.NONE)));
        } catch (OwlException e) {
            throw new IllegalArgumentException(e);
        }
        ENTITY_MAP.freeze();
    }
    
    // TODO: This is a very silly stub. Should link runtime classes.
    static JvmNode generate() {
        JvmClass rt = new JvmClass(AccessModifier.PACKAGE, CLASS_NAME);
        rt.add(genPrintlnOverload(AstType.BOOL));
        rt.add(genPrintlnOverload(AstType.CHAR));
        rt.add(genPrintlnOverload(AstType.I32));
        rt.add(genPrintlnOverload(AstType.I64));
        rt.add(genPrintlnOverload(AstType.F32));
        rt.add(genPrintlnOverload(AstType.F64));
        rt.add(genPrintlnOverload(AstType.STRING));
        return rt;
    }

    private static JvmNode genPrintlnOverload(AstType type) {
        JvmBlock block = new JvmBlock();
        block.add(new JvmApply(
                AstType.NONE,
                "System.out",
                "println",
                ImmutableList.of(new JvmValue("x", type))));
        return new JvmFunction(
                AccessModifier.PACKAGE,
                MemoryModifier.STATIC,
                AstType.NONE,
                "println",
                ImmutableList.of(JvmVariable.local(type, "x")),
                block);
    }
}
