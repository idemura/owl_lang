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
import java.util.ArrayList;
import java.util.List;

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

    private IndentPrinter doPrint(Object[] objects, boolean ln) {
        printLineIndent();
        boolean first = true;
        for (Object o : objects) {
            if (o == null) {
                continue;
            }
            String s = o.toString();
            if (s.isEmpty()) {
                continue;
            }
            if (!first) {
                printToStream(" ");
            }
            printToStream(s);
            first = false;
        }
        newLine = ln;
        printToStream(ln? "\n": " ");
        return this;
    }

    private void printToStream(String s) {
        for (PrintStream ps : out) {
            ps.print(s);
        }
    }
}
