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
package owl.lang;

final class NameGen {
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String prefix;
    private Stack<Integer> counters = new Stack<>();

    NameGen(String prefix) {
        this.prefix = prefix;
        counters.push(0);
    }

    String newName() {
        char[] buf = new char[16];
        int j = 0;
        int counter = counters.pop();
        int n = counter;
        do {
            int mod = ALPHABET.length();
            buf[j++] = ALPHABET.charAt(n % mod);
            n /= mod;
        } while (n != 0);
        counters.push(counter + 1);
        return prefix + new String(buf, 0, j);
    }

    void push() {
        counters.push(0);
    }

    void pop() {
        counters.pop();
    }
}
