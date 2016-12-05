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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static owl.lang.Utils.joinLines;

class EntityMap implements Cloneable {
    private HashMap<String, List<Entity>> ents = new HashMap<>();

    EntityMap() {}

    @Override
    public EntityMap clone() {
        EntityMap other = new EntityMap();
        for (Map.Entry<String, List<Entity>> entry : ents.entrySet()) {
            other.ents.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return other;
    }

    void put(Entity e) throws OwlException {
        int j = findInsertIfMissing(e);
        if (j >= 0) {
            throw new OwlException("duplicated entity");
        }
    }

    void replace(Entity e) {
        int j = findInsertIfMissing(e);
        if (j >= 0) {
            ents.get(e.getName()).set(j, e);
        }
    }

    private int findInsertIfMissing(Entity e) {
        if (ents.containsKey(e.getName())) {
            int i = 0;
            for (Entity existing : ents.get(e.getName())) {
                if (e.equals(existing)) {
                    return i;
                }
                i++;
            }
        } else {
            ents.put(e.getName(), new ArrayList<>());
        }
        ents.get(e.getName()).add(e);
        return -1;
    }

    EntityMap freeze() {
        return this;
    }

    boolean isFunction(String name) {
        return ents.containsKey(name) && ents.get(name).get(0) instanceof FunctionEntity;
    }

    Entity resolveVariable(String name) throws OwlException {
        if (ents.containsKey(name)) {
            List<Entity> entList = ents.get(name);
            if (entList.size() == 1 && entList.get(0) instanceof VariableEntity) {
                return entList.get(0);
            }
        }
        throw new OwlException("variable entity not found " + name);
    }

    Entity resolveFunction(String name, List<AstType> argTypes) throws OwlException {
        if (ents.containsKey(name)) {
            List<Entity> matched = ents.get(name).stream()
                    .filter(e -> fnMatchesArgs(e, argTypes))
                    .collect(toList());
            if (matched.size() == 1) {
                return matched.get(0);
            }
            if (matched.size() == 0) {
                throw new OwlException("no candidates for call " + name);
            }
            throw new OwlException("ambiguous call to " + name + "; candidates:\n" + joinLines(matched));
        }
        throw new OwlException("function entity not found: " + name);
    }

    private static boolean fnMatchesArgs(Entity e, List<AstType> argTypes) {
        if (!(e instanceof FunctionEntity)) {
            return false;
        }
        AstType fnType = e.getType();
        // Do not count return type
        if (fnType.args.size() - 1 == argTypes.size()) {
            for (int i = 0; i < argTypes.size(); i++) {
                if (!fnType.args.get(i).equals(argTypes.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    void print(PrintStream out) {
        for (String name : ents.keySet()) {
            List<Entity> entList = ents.get(name);
            if (entList.size() == 1) {
                out.println(entList.get(0));
            } else {
                out.println(name);
                for (Entity e : entList) {
                    out.println("  " + e);
                }
            }
        }
    }
}
