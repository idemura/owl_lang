package owl.compiler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AstTest {
    @Test
    public void testTypeToString() {
        AstType t;

        t = new AstType("Foo",
                Util.listOf(AstType.I32, AstType.F32));
        assertEquals("Foo(I32, F32)", t.toString());

        t = new AstType("Foo",
                Util.listOf(AstType.BOOL));
        assertEquals("Foo(Bool)", t.toString());

        t = new AstType("Foo");
        assertEquals("Foo", t.toString());
    }
}
