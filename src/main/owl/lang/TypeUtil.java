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

import java.util.List;

import static java.util.Arrays.asList;

final class TypeUtil {
    private TypeUtil() {}

    // Not sure if needed
    static boolean equalSignatures(Type a, Type b) {
        if (!a.isFunction() || !b.isFunction()) {
            throw new IllegalArgumentException("function type expected");
        }
        if (a.args.size() != b.args.size()) {
            return false;
        }
        // Compare all except last (return type)
        for (int i = 0; i < a.args.size() - 1; i++) {
            if (!a.args.get(i).equals(b.args.get(i))) {
                return false;
            }
        }
        return true;
    }

    static Type makeFnType(Type... typeArgs) {
        if (typeArgs.length < 1) {
            throw new IllegalArgumentException("makeFnType args 0 length");
        }
        return Type.functionOf(asList(typeArgs));
    }

    static boolean accepts(Type fn, List<Type> args) {
        // Do not count return type
        if (fn.args.size() - 1 == args.size()) {
            for (int i = 0; i < args.size(); i++) {
                if (!fn.args.get(i).equals(args.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean assignable(Type dst, Type src) {
        return dst.equals(src);
    }
}
