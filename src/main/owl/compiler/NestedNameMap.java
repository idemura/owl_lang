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
import java.util.List;

final class NestedNameMap {
    private NameMap<Entity> variables;
    private OverloadNameMap overloads = new OverloadNameMap();
    private Stack<NameMap<Entity>> scope = new Stack<>();

    NestedNameMap(NameMap<Entity> variables, OverloadNameMap overloads) {
        this.variables = variables.clone();
        this.overloads = overloads.clone();
    }

    void push() {
        scope.push(new NameMap<>());
    }

    void pop() {
        scope.pop();
    }

    boolean put(Entity e) {
        return scope.top().put(e.getName(), e);
    }

    boolean inTopBlock(String name) {
        return !scope.isEmpty() && scope.top().contains(name);
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

    ResolveResult resolve(String name, List<AstType> args) {
        for (NameMap<Entity> map : scope) {
            Entity e = map.get(name);
            if (e != null) {
                if (e.getType().isFunction()) {
                    if (e.getType().acceptsArgs(args)) {
                        return ResolveResult.found(e);
                    }
                    return ResolveResult.error(Util.listOf(e));
                }
                return ResolveResult.error(null);
            }
        }
        OverloadNameMap.Overload ovl = overloads.get(name);
        if (ovl == null) {
            return ResolveResult.error(Util.listOf());
        }
        List<Entity> res = ovl.resolve(args);
        if (res.size() != 1) {
            return ResolveResult.error(res);
        }
        return ResolveResult.found(res.get(0));
    }

    void print(PrintStream out) {
        // TODO: Print nested entity map
    }
}
