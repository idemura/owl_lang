package owl.compiler;

final class OwlException extends Exception {
    final int line;
    final int charPosition;

    public OwlException(String message) {
        super(message);
        this.line = 0;
        this.charPosition = 0;
    }

    public OwlException(Throwable cause) {
        super(cause);
        this.line = 0;
        this.charPosition = 0;
    }
}
