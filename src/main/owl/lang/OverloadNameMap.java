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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static owl.lang.TypeUtil.equalSignatures;

final class Overload implements Cloneable {
    final String name;
    private List<Entity> overload = new ArrayList<>();

    Overload(String name) {
        this.name = name;
    }

    @Override
    public Overload clone() {
        Overload other = new Overload(name);
        overload.forEach(e -> other.overload.add(e));
        return other;
    }

    void add(Entity ent) throws OwlException {
        // TODO: Check other way, because this is effectively O(N^2)
        for (Entity f : overload) {
            if (equalSignatures(f.getType(), ent.getType())) {
                throw new OwlException("overload with same signature");
            }
        }
        overload.add(ent);
    }

    List<Entity> resolve(List<AstType> args) {
        List<Entity> res = new ArrayList<>();
        for (Entity f : overload) {
            if (TypeUtil.accepts(f.getType(), args)) {
                res.add(f);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String text = "overload " + name + "\n";
        for (Entity f : overload) {
            text += "  ";
            text += f.toString();
            text += "\n";
        }
        return text;
    }
}

// This is an entity map just for function overloads
final class OverloadNameMap implements Cloneable {
    private HashMap<String, Overload> map = new HashMap<>();

    @Override
    public OverloadNameMap clone() {
        OverloadNameMap other = new OverloadNameMap();
        map.forEach((k, v) -> other.map.put(k, v.clone()));
        return other;
    }

    void put(Entity e) throws OwlException {
        checkArgument(e.getType().isFunction());
        map.computeIfAbsent(e.getName(), k -> new Overload(e.getName())).add(e);
    }

    boolean contains(String name) {
        return map.containsKey(name);
    }

    Overload get(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return Util.joinLines(map.values().stream().map(Object::toString).collect(Collectors.toList()));
    }
}