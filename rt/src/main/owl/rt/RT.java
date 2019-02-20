package owl.rt;

public class RT {
    private RT() {}

    public static void owl_assert(boolean x) {
        if (!x) {
            throw new IllegalStateException("assertion failed");
        }
    }
    public static void println(boolean x) {
        System.out.println(x);
    }
    public static void println(char x) {
        System.out.println(x);
    }
    public static void println(double x) {
        System.out.println(x);
    }
    public static void println(float x) {
        System.out.println(x);
    }
    public static void println(int x) {
        System.out.println(x);
    }
    public static void println(long x) {
        System.out.println(x);
    }
    public static void println(String x) {
        System.out.println(x);
    }
    public static int compare(String a, String b) {
        return a.compareTo(b);
    }
    public static double fdiv(int a, int b) {
        return ((double) a) / b;
    }
    public static double fdiv(long a, long b) {
        return ((double) a) / b;
    }
    public static int size(String s) {
        return s.length();
    }
    public static String concat(String a, String b) {
        return a + b;
    }
}
