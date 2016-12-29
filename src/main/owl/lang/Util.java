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
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static java.util.stream.Collectors.toList;

final class IndentPrinter {
    private static final String TAB = "  ";
    private final List<PrintStream> out = new ArrayList<>();
    private int tab = 0;
    private boolean newLine = true;

    IndentPrinter(PrintStream... out) {
        for (PrintStream s : out) {
            if (s != null) {
                this.out.add(s);
            }
        }
    }

    void indent() {
        tab++;
    }

    void unindent() {
        tab--;
    }

    IndentPrinter print(Object... objs) {
        return doPrint(objs, false);
    }

    IndentPrinter println(Object... objs) {
        return doPrint(objs, true);
    }

    private void printLineIndent() {
        if (newLine) {
            for (int i = 0; i < tab; i++) {
                printToStream(TAB);
            }
        }
    }

    private IndentPrinter doPrint(Object[] objs, boolean ln) {
        printLineIndent();
        boolean first = true;
        for (Object o : objs) {
            String ostr = o.toString();
            if (ostr.isEmpty()) {
                continue;
            }
            if (!first) {
                printToStream(" ");
            }
            printToStream(o.toString());
            first = false;
        }
        if (ln) {
            printToStream("\n");
        }
        newLine = ln;
        return this;
    }

    private void printToStream(String s) {
        for (PrintStream ps : out) {
            ps.print(s);
        }
    }
}

final class Util {
    private Util() {}

    private static String LANGUAGE_VERSION;
    private static String COMPILER_NAME;

    static boolean startsWithLetter(String s) {
        char c = Character.toLowerCase(s.charAt(0));
        return 'a' <= c && c <= 'z';
    }

    static boolean isName(String s) {
        char c = Character.toLowerCase(s.charAt(0));
        if (!('a' <= c && c <= 'z')) {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            c = Character.toLowerCase(s.charAt(0));
            if ("abcdefghijklmnopqrstuvwxyz0123456789_".indexOf(c) < 0) {
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

    static <T> List<T> listOf(T x1) {
        List<T> l = new ArrayList<>();
        l.add(x1);
        return l;
    }

    static String removeSuffix(String s, int len) {
        return s.substring(0, s.length() - len);
    }

    static boolean isEmpty(String s) {
        return s != null && s.isEmpty();
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
