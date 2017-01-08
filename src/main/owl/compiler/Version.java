/*
 * Copyright 2017 Igor Demura
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
