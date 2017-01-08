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

public class NameGenTest {
    @Test
    public void testNameGen() {
        NameGen gen = new NameGen("_t_");
        assertEquals("_t_a", gen.newName());
        assertEquals("_t_b", gen.newName());
        assertEquals("_t_c", gen.newName());
        for (int i = 0; i < 26 - 3; i++) {
            gen.newName();
        }
        assertEquals("_t_A", gen.newName());
        assertEquals("_t_B", gen.newName());
        for (int i = 0; i < 26 - 2; i++) {
            gen.newName();
        }
        assertEquals("_t_0", gen.newName());
        assertEquals("_t_1", gen.newName());
        for (int i = 0; i < 10 - 2; i++) {
            gen.newName();
        }
        assertEquals("_t_ab", gen.newName());
        assertEquals("_t_bb", gen.newName());
        assertEquals("_t_cb", gen.newName());
        gen.push();
        assertEquals("_t_a", gen.newName());
        assertEquals("_t_b", gen.newName());
        gen.pop();
        assertEquals("_t_db", gen.newName());
    }
}
