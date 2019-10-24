package owl.compiler;

import java.io.PrintStream;

final class PrintErrorListener implements ErrorListener {
    private final String fileName;
    private final PrintStream out;

    PrintErrorListener(PrintStream out, String fileName) {
        this.out = out;
        this.fileName = fileName;
    }

    public void error(
            int line,
            int charPosition,
            String msg) {
        String position = null;
        if (line > 0) {
            position = String.valueOf(line);
            if (charPosition > 0) {
                position += ":" + String.valueOf(charPosition);
            }
        }
        if (msg == null) {
            msg = "unknown";
        }
        print(position, "error: " + msg);
    }

    private void print(String position, String text) {
        String fileWithPosition = fileName;
        if (position != null) {
            fileWithPosition += ":" + position;
        }
        out.println(fileWithPosition + ": " + text);
    }
}
