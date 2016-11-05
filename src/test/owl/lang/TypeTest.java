package owl.lang;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static owl.lang.TypeNameVisitor.typeStr;

public class TypeTest {
    @Test
    public void testGenericTypeStr() {
        AstType t = AstType.fromName("Foo");
        t.params.add(AstType.I32);
        t.params.add(AstType.F32);
        assertEquals("Foo(I32, F32)", typeStr(t));
    }

    @Test
    public void testTypeStr() {
        AstType t = AstType.fromName("Foo");
        assertEquals("Foo", typeStr(t));
    }
}
