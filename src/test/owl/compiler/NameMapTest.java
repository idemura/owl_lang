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

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameMapTest {
    @Test
    public void testPutReplace() {
        NameMap<Entity> m = new NameMap<>();
        assertTrue(m.put("x", new AstVariable(new AstVariable.Local(), null, "x", AstType.I32, null)));
        assertTrue(m.put("y", new AstVariable(new AstVariable.Local(), null, "y", AstType.I32, null)));
        assertFalse(m.put("x", new AstVariable(new AstVariable.Local(), null, "x", AstType.STRING, null)));
        assertFalse(m.put("y", new AstVariable(new AstVariable.Local(), null, "y", AstType.STRING, null)));
    }
}
