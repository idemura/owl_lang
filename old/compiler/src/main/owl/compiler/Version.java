package owl.compiler;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

final class Version {
    private Version() {}

    private static String LANGUAGE_VERSION;
    private static String COMPILER_NAME;

    static String getCompilerName() {
        if (COMPILER_NAME == null) {
            COMPILER_NAME =
                    getManifestAttribute("Owl-Compiler-Name") + " " +
                            getManifestAttribute("Owl-Compiler-Version");
        }
        return COMPILER_NAME;
    }

    static String getLanguageVersion() {
        if (LANGUAGE_VERSION == null) {
            LANGUAGE_VERSION = getManifestAttribute("Owl-Language-Version");
        }
        return LANGUAGE_VERSION;
    }

    private static String getManifestAttribute(String name) {
        try {
            Attributes.Name attrName = new Attributes.Name(name);
            Enumeration<URL> resources = Util.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                Manifest m = new Manifest(resources.nextElement().openStream());
                Object value = m.getMainAttributes().get(attrName);
                if (value != null) {
                    return (String) value;
                }
            }
        } catch (IOException e) {
            // Fall through
        }
        throw new IllegalStateException("manifest missing attribute");
    }
}
