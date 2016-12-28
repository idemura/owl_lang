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

import java.util.HashMap;
import java.util.stream.Collectors;

// Entity map without overload support for local scopes and types
final class NameMap<T> implements Cloneable {
    private HashMap<String, T> map = new HashMap<>();

    @Override
    public NameMap<T> clone() {
        NameMap<T> other = new NameMap<>();
        map.forEach((k, v) -> other.map.put(k, v));
        return other;
    }

    void put(String name, T value) throws OwlException {
        if (map.putIfAbsent(name, value) != null) {
            throw new OwlException("duplicated entity " + name);
        }
    }

    boolean contains(String name) {
        return map.containsKey(name);
    }

    T get(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return Util.joinLines(map.values().stream().map(Object::toString).collect(Collectors.toList()));
    }
}
