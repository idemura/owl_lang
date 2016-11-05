package owl.lang;

import java.io.*;

public class CLI {
    public static void main(String[] args) {
        boolean failed = false;
        for (String file_name : args) {
            if (!compileFile(file_name)) {
                failed = true;
            }
        }
        System.exit(failed ? 1 : 0);
    }

    private static boolean compileFile(String file_name) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(file_name));
            return compile(in);
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
            return false;
        } finally {
            closeStream(in);
        }
    }

    private static boolean compile(InputStream in) {
        Parser parser = new Parser(new ParserTokenManager(new SimpleCharStream(in)));
        try {
            AstNode root = parser.module();
            root.accept(new DebugPrintVisitor());
            System.out.println("Compiled!");
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void closeStream(InputStream in) {
        if (in == null) {
            return;
        }
        try {
            in.close();
        } catch (IOException e) {
            // Empty
        }
    }
}
