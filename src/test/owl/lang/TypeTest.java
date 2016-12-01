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

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TypeTest {
    @Test
    public void testGenericTypeStr() {
        AstType t = new AstType("Foo");
        t.args.add(AstType.I32);
        t.args.add(AstType.F32);
        assertEquals("Foo(I32, F32)", t.toString());
    }

    @Test
    public void testTypeStr() {
        AstType t = new AstType("Foo");
        assertEquals("Foo", t.toString());
    }
}
