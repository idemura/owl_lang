package id.owl_lang;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App app = new App();
        assertEquals(app.getGreeting(), "Hello");
    }
}
