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

class ResolveError extends Exception {
}

final class ResolveErrorNoMatch extends ResolveError {
    final String name;
    final List<Entity> matches;

    ResolveErrorNoMatch(String name, List<Entity> matches) {
        this.name = name;
        this.matches = matches == null? ImmutableList.of(): ImmutableList.copyOf(matches);
    }

    @Override
    public String getMessage() {
        if (matches.size() == 0) {
            return "function " + name + ": no matches";
        } else {
            return "function " + name + ": match not found:\n" +
                    Util.joinLines(matches.stream()
                            .map(s -> "  " + s)
                            .collect(Collectors.toList()));
        }
    }
}

final class ResolveErrorType extends ResolveError {
    final String name;

    ResolveErrorType(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return name + " is not a function";
    }
}

final class NestedEntityMap {
    private EntityMap variables;
    private OverloadEntityMap overloads = new OverloadEntityMap();
    private Stack<EntityMap> scope = new Stack<>();

    NestedEntityMap(EntityMap variables, OverloadEntityMap overloads) {
        this.variables = variables.clone();
        this.overloads = overloads.clone();
    }

    void push() {
        scope.push(new EntityMap());
    }

    void pop() {
        scope.pop();
    }

    void put(Entity e) throws OwlException {
        scope.top().put(e);
    }

    boolean isBlockVar(String name) {
        for (EntityMap map : scope) {
            if (map.contains(name)) {
                return true;
            }
        }
        return false;
    }

    boolean contains(String name) {
        for (EntityMap map : scope) {
            if (map.contains(name)) {
                return true;
            }
        }
        return variables.contains(name) || overloads.contains(name);
    }

    boolean isFunction(String name) {
        for (EntityMap map : scope) {
            if (map.contains(name) && map.isFunction(name)) {
                return true;
            }
        }
        return overloads.contains(name);
    }

    EntityMap top() {
        return scope.top();
    }

    Entity get(String name) {
        for (EntityMap map : scope) {
            if (map.contains(name)) {
                return map.get(name);
            }
        }
        return variables.get(name);
    }

    Entity resolve(String name, List<AstType> args) throws ResolveError {
        for (EntityMap map : scope) {
            Entity e = map.get(name);
            if (e != null) {
                if (e.isFunction()) {
                    if (TypeUtil.accepts(e.type, args)) {
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
