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

import java.io.PrintStream;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

final class IndentPrinter {
    private static final String TAB = "  ";
    private PrintStream out;
    private int tab = 0;
    private boolean newLine = true;

    IndentPrinter(PrintStream out) {
        this.out = out;
    }

    void indent() {
        tab++;
    }

    void unindent() {
        tab--;
    }

    IndentPrinter print(String s) {
        return doPrint(s, false);
    }

    IndentPrinter println(String s) {
        return doPrint(s, true);
    }

    IndentPrinter printlnAll(Object... objs) {
        printLineIndent();
        boolean first = true;
        for (Object o : objs) {
            if (first) {
                first = false;
            } else {
                out.print(" ");
            }
            out.print(o.toString());
        }
        out.println();
        newLine = true;
        return this;
    }

    void curlyOpen() {
        println("{");
        indent();
    }

    void curlyClose() {
        unindent();
        println("}");
    }

    private void printLineIndent() {
        if (newLine) {
            for (int i = 0; i < tab; i++) {
                out.print(TAB);
            }
        }
    }

    private IndentPrinter doPrint(String s, boolean ln) {
        printLineIndent();
        out.print(s);
        if (ln) out.println();
        newLine = ln;
        return this;
    }
}

final class Utils {
    private Utils() {}

    static <T> String joinLines(Collection<T> c) {
        return String.join("\n", c.stream().map(T::toString).collect(toList()));
    }
}