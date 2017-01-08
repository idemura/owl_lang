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

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static java.util.stream.Collectors.toList;

final class Util {
    private Util() {}

    private static String LANGUAGE_VERSION;
    private static String COMPILER_NAME;

    static boolean isIdFirstChar(char c) {
        c = Character.toLowerCase(c);
        return 'a' <= c && c <= 'z';
    }

    static boolean isIdChar(char c) {
        c = Character.toLowerCase(c);
        return 'a' <= c && c <= 'z' || '0' <= c && c <= '9' || c == '_';
    }

    static boolean isName(String s) {
        if (!isIdFirstChar(s.charAt(0)) || s.charAt(0) == '_') {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if (!isIdChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static <T> String join(String delimiter, Collection<T> c) {
        return String.join(delimiter, c.stream().map(T::toString).collect(toList()));
    }

    static String quote(String s) {
        // TODO: Handle escapes
        return "\"" + s + "\"";
    }

    static String unquote(String s) {
        // TODO: Handle escapes
        return s.substring(1, s.length() - 1);
    }

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

    static String removeSuffix(String s, int len) {
        return s.substring(0, s.length() - len);
    }

    static boolean isEmpty(String s) {
        return s != null && s.isEmpty();
    }

    static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @SafeVarargs
    static <T> List<T> listOf(T... a) {
        return Arrays.asList(a);
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
