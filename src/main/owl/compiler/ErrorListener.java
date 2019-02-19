package owl.compiler;

interface ErrorListener {
    void error(int line, int charPosition, String msg);

    default void error(String msg) {
        error(0, 0, msg);
    }

    default void error(OwlException e) {
        error(e.line, e.charPosition, e.getMessage());
    }
}
