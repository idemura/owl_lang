package owl.compiler;

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
