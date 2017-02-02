/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
