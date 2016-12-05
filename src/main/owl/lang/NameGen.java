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
    private int counter = 0;
    private char[] buf = new char[80];

    String newName() {
        int j = 0;
        int n = counter;
        do {
            int mod = ALPHABET.length();
            buf[j++] = ALPHABET.charAt(n % mod);
            n /= mod;
        } while (n != 0);
        counter++;
        return "_tmp_" + new String(buf, 0, j);
    }

    void reset() {
        counter = 0;
    }
}
