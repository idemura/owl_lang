package owl.compiler;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilTest {
    @Test
    public void testIsName() {
        assertTrue(Util.isName("hello"));
        assertTrue(Util.isName("Hello"));
        assertFalse(Util.isName("_Hello"));
        assertFalse(Util.isName("0Hello"));
    }
}
