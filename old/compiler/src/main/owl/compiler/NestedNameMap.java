package owl.compiler;

import java.io.PrintStream;
import java.util.List;

final class NestedNameMap {
    private NameMap<Entity> variables;
    private OverloadNameMap overloads = new OverloadNameMap();
    private Stack<NameMap<Entity>> scope = new Stack<>();
    private int scopeId = 0;

    NestedNameMap(NameMap<Entity> variables, OverloadNameMap overloads) {
        this.variables = variables.clone();
        this.overloads = overloads.clone();
    }

    void pushScopeId() {
        scopeId++;
    }

    void popScopeId() {
        scopeId--;
    }

    void push() {
        scope.push(new NameMap<>(scopeId));
    }

    void pop() {
        scope.pop();
    }

    boolean put(Entity e) {
        return scope.top().put(e.getName(), e);
    }

    boolean shadows(String name) {
        Util.check(!scope.isEmpty());
        for (int i = scope.size(); i > 0; ) {
            NameMap<Entity> map = scope.get(--i);
            if (map.getScopeId() != scopeId) {
                break;
            }
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

    ResolveResult resolve(String name, List<AstType> args) {
        for (NameMap<Entity> map : scope) {
            Entity e = map.get(name);
            if (e != null) {
                if (e.getType().isFunction()) {
                    if (e.getType().functionTakes(args)) {
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
