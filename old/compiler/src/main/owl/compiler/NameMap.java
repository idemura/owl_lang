package owl.compiler;

import java.util.HashMap;
import java.util.stream.Collectors;

// Entity map without overload support for local scopes and types
final class NameMap<T> implements Cloneable {
    private HashMap<String, T> map = new HashMap<>();
    private final int scopeId;

    NameMap(int scopeId) {
        this.scopeId = scopeId;
    }

    @Override
    public NameMap<T> clone() {
        NameMap<T> other = new NameMap<>(scopeId);
        map.forEach((k, v) -> other.map.put(k, v));
        return other;
    }

    boolean put(String name, T value) {
        return map.putIfAbsent(name, value) == null;
    }

    boolean put(T value) {
        Util.check(value instanceof Named);
        return put(((Named) value).getName(), value);
    }

    boolean contains(String name) {
        return map.containsKey(name);
    }

    T get(String name) {
        return map.get(name);
    }

    int getScopeId() {
        return scopeId;
    }

    @Override
    public String toString() {
        return Util.join("\n", map.values().stream().map(Object::toString).collect(Collectors.toList()));
    }
}
