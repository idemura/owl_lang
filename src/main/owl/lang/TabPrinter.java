package owl.lang;

final class TabPrinter {
    private int tab = 0;
    private boolean newLine = true;

    void print(String s) {
        int t = tab;
        if (s.endsWith("{\n")) {
            tab++;
            s = s.substring(0, s.length() - 2) + "\n";
        }
        if (s.endsWith("}\n")) {
            tab--;
            if (s.length() == 2) {
                newLine = true;
                return;
            }
            s = s.substring(0, s.length() - 2) + "\n";
        }
        if (newLine) {
            for (int i = 0; i < t; i++) {
                System.out.print("  ");
            }
        }
        newLine = s.endsWith("\n");
        System.out.print(s);
    }
}
