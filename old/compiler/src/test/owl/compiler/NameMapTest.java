package owl.compiler;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameMapTest {
    @Test
    public void testPutReplace() {
        NameMap<Entity> m = new NameMap<>(0);
        assertTrue(m.put("x", new AstVariable(new AstVariable.Local(), null, "x", AstType.I32, null)));
        assertTrue(m.put("y", new AstVariable(new AstVariable.Local(), null, "y", AstType.I32, null)));
        assertFalse(m.put("x", new AstVariable(new AstVariable.Local(), null, "x", AstType.STRING, null)));
        assertFalse(m.put("y", new AstVariable(new AstVariable.Local(), null, "y", AstType.STRING, null)));
    }
}
