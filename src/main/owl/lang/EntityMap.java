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
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static owl.lang.TypeUtil.equalSignatures;

// Entity map without overload support for local scopes
final class EntityMap implements Cloneable {
    private HashMap<String, Entity> map = new HashMap<>();

    @Override
    public EntityMap clone() {
        EntityMap other = new EntityMap();
        for (Map.Entry<String, Entity> entry : map.entrySet()) {
            other.map.put(entry.getKey(), entry.getValue());
        }
        return other;
    }

    void put(Entity e) throws OwlException {
        Entity inMap = map.get(e.name);
        if (inMap == null) {
            map.put(e.name, e);
        } else {
            throw new OwlException("duplicated entity " + e.name);
        }
    }

    boolean contains(String name) {
        return map.containsKey(name);
    }

    boolean isFunction(String name) {
        return map.get(name).isFunction();
    }

    Entity get(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return Util.joinLines(map.values().stream().map(Object::toString).collect(Collectors.toList()));
    }
}

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
            if (equalSignatures(f.type, ent.type)) {
                throw new OwlException("overload with same signature");
            }
        }
        overload.add(ent);
    }

    List<Entity> resolve(List<AstType> args) {
        List<Entity> res = new ArrayList<>();
        for (Entity f : overload) {
            if (TypeUtil.accepts(f.type, args)) {
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
final class OverloadEntityMap implements Cloneable {
    private HashMap<String, Overload> map = new HashMap<>();

    @Override
    public OverloadEntityMap clone() {
        OverloadEntityMap other = new OverloadEntityMap();
        for (Map.Entry<String, Overload> entry : map.entrySet()) {
            other.map.put(entry.getKey(), entry.getValue().clone());
        }
        return other;
    }

    void put(Entity e) throws OwlException {
        checkArgument(e.isFunction());
        Overload ovl = map.get(e.name);
        if (ovl == null) {
            ovl = new Overload(e.name);
            map.put(e.name, ovl);
        }
        ovl.add(e);
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
