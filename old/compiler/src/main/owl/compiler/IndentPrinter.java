package owl.compiler;

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
