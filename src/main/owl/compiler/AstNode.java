package owl.compiler;

abstract class AstNode {
    private int line;
    private int charPosition;

    void setPosition(int line, int charPosition) {
        this.line = line;
        this.charPosition = charPosition;
    }

    int getLine() { return line; }
    int getCharPosition() { return charPosition; }

    abstract <T> T accept(AstVisitor<T> visitor);
}
