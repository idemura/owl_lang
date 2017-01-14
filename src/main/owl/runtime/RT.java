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
package owl.runtime;

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
