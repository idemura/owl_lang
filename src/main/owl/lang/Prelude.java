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

final class Prelude {
    private Prelude() {}

    static final EntityMap entities = new EntityMap();
    {
        FunctionEntity f;
        try {
            f = new FunctionEntity();
            f.name = "+";
            f.argumentTypes.add(AstType.I32);
            f.argumentTypes.add(AstType.I32);
            f.returnType = AstType.I32;
            entities.put(f);

            f = new FunctionEntity();
            f.name = "+";
            f.argumentTypes.add(AstType.I64);
            f.argumentTypes.add(AstType.I64);
            f.returnType = AstType.I64;
            entities.put(f);

            f = new FunctionEntity();
            f.name = "*";
            f.argumentTypes.add(AstType.I32);
            f.argumentTypes.add(AstType.I32);
            f.returnType = AstType.I32;
            entities.put(f);

            f = new FunctionEntity();
            f.name = "print";
            f.argumentTypes.add(AstType.I32);
            entities.put(f);

            f = new FunctionEntity();
            f.name = "print";
            f.argumentTypes.add(AstType.I64);
            entities.put(f);

            f = new FunctionEntity();
            f.name = "print";
            f.argumentTypes.add(AstType.String);
            entities.put(f);
        } catch (OwlException e) {
            throw new IllegalArgumentException(e);
        }
        entities.freeze();
    }
}