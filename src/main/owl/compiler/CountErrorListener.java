package owl.compiler;

final class CountErrorListener implements ErrorListener {
    private ErrorListener sink;
    private int errorCount = 0;

    CountErrorListener(ErrorListener sink) {
        this.sink = sink;
    }

    public void error(
            int line,
            int charPosition,
            String msg) {
        sink.error(line, charPosition, msg);
        errorCount++;
    }

    int getErrorCount() {
        return errorCount;
    }
}
