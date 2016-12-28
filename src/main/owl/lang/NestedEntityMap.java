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
import java.util.List;
import java.util.stream.Collectors;

final class NestedEntityMap {
    private NameMap<Entity> variables;
    private OverloadNameMap overloads = new OverloadNameMap();
    private Stack<NameMap<Entity>> scope = new Stack<>();

    NestedEntityMap(NameMap<Entity> variables, OverloadNameMap overloads) {
        this.variables = variables.clone();
        this.overloads = overloads.clone();
    }

    void push() {
        scope.push(new NameMap<>());
    }

    void pop() {
        scope.pop();
    }

    void put(Entity e) throws OwlException {
        scope.top().put(e.getName(), e);
    }

    boolean isBlockVar(String name) {
        for (NameMap<Entity> map : scope) {
            if (map.contains(name)) {
                return true;
            }
        }
        return false;
    }

    boolean inTopBlock(String name) {
        for (NameMap<Entity> map : scope) {
            if (map.contains(name)) {
                return true;
            }
        }
        return false;
    }

    boolean contains(String name) {
        for (NameMap<Entity> map : scope) {
            if (map.contains(name)) {
                return true;
            }
        }
        return variables.contains(name) || overloads.contains(name);
    }

    boolean isFunction(String name) {
        for (NameMap<Entity> map : scope) {
            if (map.contains(name) && map.get(name).getType().isFunction()) {
                return true;
            }
        }
        return overloads.contains(name);
    }

    NameMap<Entity> top() {
        return scope.top();
    }

    Entity get(String name) {
        for (NameMap<Entity> map : scope) {
            if (map.contains(name)) {
                return map.get(name);
            }
        }
        return variables.get(name);
    }

    Entity resolve(String name, List<AstType> args) throws ResolveError {
        for (NameMap<Entity> map : scope) {
            Entity e = map.get(name);
            if (e != null) {
                if (e.getType().isFunction()) {
                    if (TypeUtil.accepts(e.getType(), args)) {
                        return e;
                    }
                    // TODO: Better message
                    throw new ResolveErrorNoMatch(name, ImmutableList.of(e));
                }
                throw new ResolveErrorType(name);
            }
        }
        Overload ovl = overloads.get(name);
        if (ovl == null) {
            throw new ResolveErrorNoMatch(name, ImmutableList.of());
        }
        List<Entity> res = ovl.resolve(args);
        if (res.size() != 1) {
            throw new ResolveErrorNoMatch(name, res);
        }
        return res.get(0);
    }

    void print(PrintStream out) {
        // TODO: Print nested entity map
    }
}
