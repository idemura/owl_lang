package owl.compiler;

final class NameGen {
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String prefix;
    private Stack<Integer> counters = new Stack<>();

    NameGen(String prefix) {
        this.prefix = prefix;
        counters.push(0);
    }

    String newName() {
        counters.push(counters.pop() + 1);
        return fromInt(prefix, counters.top() - 1);
    }

    void push() {
        counters.push(0);
    }

    void pop() {
        counters.pop();
    }

    static String fromInt(String prefix, int n) {
        char[] buf = new char[16];
        int j = 0;
        do {
            int mod = ALPHABET.length();
            buf[j++] = ALPHABET.charAt(n % mod);
            n /= mod;
        } while (n != 0);
        return prefix + new String(buf, 0, j);
    }
}
