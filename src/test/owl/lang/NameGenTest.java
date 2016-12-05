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
        NameGen gen = new NameGen();
        assertEquals("_tmp_a", gen.newName());
        assertEquals("_tmp_b", gen.newName());
        assertEquals("_tmp_c", gen.newName());
        for (int i = 0; i < 26 - 3; i++) {
            gen.newName();
        }
        assertEquals("_tmp_A", gen.newName());
        assertEquals("_tmp_B", gen.newName());
        for (int i = 0; i < 26 - 2; i++) {
            gen.newName();
        }
        assertEquals("_tmp_0", gen.newName());
        assertEquals("_tmp_1", gen.newName());
        for (int i = 0; i < 10 - 2; i++) {
            gen.newName();
        }
        assertEquals("_tmp_ab", gen.newName());
        assertEquals("_tmp_bb", gen.newName());
        assertEquals("_tmp_cb", gen.newName());
    }
}
