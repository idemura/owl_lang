/*
 * Copyright 2017 Igor Demura
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

final class Jvm {
    static final String BOOL = "Z";
    static final String CHAR = "C";
    static final String F32 = "F";
    static final String F64 = "D";
    static final String I8 = "B";
    static final String I16 = "S";
    static final String I32 = "I";
    static final String I64 = "J";
    static final String NONE = "V";
    static final String STRING = classType("java.lang.String");

    JvmNode root;

    Jvm(JvmNode root) {
        this.root = root;
    }

    <T extends JvmNode> T getRoot() {
        return (T) root;
    }

    static String arrayType(String type) {
        return "[" + type;
    }

    static String classType(String name) {
        return "L" + name.replace('.', '/') + ";";
    }

    static int countArgs(String type) {
        if (type.charAt(0) == '(' && type.charAt(1) == ')') {
            return 0;
        }
        int c = 0;
        int i = 0;
        for (;;) {
            int comma = type.indexOf(',', i);
            if (comma < 0) {
                break;
            }
            c++;
            i = comma + 1;
        }
        return c + 1;
    }

    static boolean isVoid(String type) {
        return type.endsWith(")V");
    }

    static boolean isClass(String type) {
        return type.charAt(0) == 'L' && type.charAt(type.length() - 1) == ';';
    }
}
