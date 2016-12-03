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

import com.google.common.collect.ImmutableList;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class EntityMap implements Cloneable {
    private List<Entity> ents = new ArrayList<>();

    EntityMap() {}

    // Returns mutable entity map.
    @Override
    public EntityMap clone() {
        EntityMap other = new EntityMap();
        other.ents = new ArrayList<>(ents);
        return other;
    }

    void put(Entity s) throws OwlException {
        for (Entity existing : ents) {
            if (s.equals(existing)) {
                throw new OwlException("duplicated entity");
            }
        }
        ents.add(s);
    }

    void replace(Entity s) {
        for (int i = 0; i < ents.size(); i++) {
            Entity existing = ents.get(i);
            if (s.equals(existing)) {
                ents.set(i, s);
                return;
            }
        }
        ents.add(s);
    }

    void freeze() {
        ents = ImmutableList.copyOf(ents);
    }

    Entity resolve(String name) {
        throw new UnsupportedOperationException("resolve");
    }

    void print(PrintStream out) {
        ents.forEach(out::println);
    }
}
