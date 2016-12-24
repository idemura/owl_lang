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

import static org.junit.Assert.fail;

public class EntityMapTest {
    @Test
    public void testPutReplace() {
        EntityMap m = new EntityMap();
        try {
            m.put(new Entity("test", "x", Type.I32));
            m.put(new Entity("test", "y", Type.I32));
        } catch (OwlException e) {
            fail();
        }
        try {
            m.put(new Entity("test", "x", Type.STRING));
            fail();
        } catch (OwlException e) {
        }
    }
}
