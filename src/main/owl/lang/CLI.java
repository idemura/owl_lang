package owl.lang;

import java.io.*;

public class CLI {
    public static void main(String[] args) {
        boolean anyFailed = false;
        for (String fileName : args) {
            try {
                if (!compileFile(fileName)) {
                    anyFailed = true;
                    System.err.println("Error compiling " + fileName);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                anyFailed = true;
            }
        }
        System.exit(anyFailed ? 1 : 0);
    }

    private static boolean compileFile(String fileName) throws Exception {
        try (InputStream in = new FileInputStream(new File(fileName))) {
            return compile(in);
        }
    }

    private static boolean compile(InputStream in) throws Exception {
        Parser parser = new Parser(new ParserTokenManager(new SimpleCharStream(in)));
        AstModule module = parser.module();
        module.accept(new DebugPrintVisitor());
        return true;
    }
}
