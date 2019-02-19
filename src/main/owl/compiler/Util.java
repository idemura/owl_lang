package owl.compiler;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

final class Util {
    private Util() {}

    static void checkFail(String msg) {
        throw new IllegalStateException(msg);
    }

    static void check(boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }

    static void check(boolean b, String msg) {
        if (!b) {
            throw new IllegalStateException(msg);
        }
    }

    static int combineHashes(int a, int b) {
        // Follows Objects.hash
        return 31 * (31 + a) + b;
    }

    static boolean isIdFirstChar(char c) {
        c = Character.toLowerCase(c);
        return 'a' <= c && c <= 'z';
    }

    static boolean isIdChar(char c) {
        c = Character.toLowerCase(c);
        return 'a' <= c && c <= 'z' || '0' <= c && c <= '9' || c == '_';
    }

    static boolean isName(String s) {
        if (!isIdFirstChar(s.charAt(0)) || s.charAt(0) == '_') {
            return false;
        }
        for (int i = 1; i < s.length(); i++) {
            if (!isIdChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static <T> String join(String delimiter, Collection<T> c) {
        return String.join(delimiter, c.stream().map(T::toString).collect(toList()));
    }

    static String removeSuffix(String s, int len) {
        return s.substring(0, s.length() - len);
    }

    static boolean isEmpty(String s) {
        return s != null && s.isEmpty();
    }

    static <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    @SafeVarargs
    static <T> List<T> listOf(T... a) {
        return Arrays.asList(a);
    }
}
