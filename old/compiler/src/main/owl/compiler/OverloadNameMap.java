package owl.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Merge with plain NameMap
// This is an entity map just for function overloads
final class OverloadNameMap implements Cloneable {
    final static class Overload implements Cloneable {
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

        boolean add(Entity ent) {
            // TODO: Check other way, because this is effectively O(N^2)
            for (Entity f : overload) {
                if (AstType.equalSignatures(f.getType(), ent.getType())) {
                    return false;
                }
            }
            overload.add(ent);
            return true;
        }

        List<Entity> resolve(List<AstType> args) {
            List<Entity> res = new ArrayList<>();
            for (Entity f : overload) {
                if (f.getType().functionTakes(args)) {
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

    private HashMap<String, Overload> map = new HashMap<>();

    @Override
    public OverloadNameMap clone() {
        OverloadNameMap other = new OverloadNameMap();
        map.forEach((k, v) -> other.map.put(k, v.clone()));
        return other;
    }

    boolean put(Entity e) {
        Util.check(e.getType().isFunction());
        return map.computeIfAbsent(e.getName(), k -> new Overload(e.getName())).add(e);
    }

    boolean contains(String name) {
        return map.containsKey(name);
    }

    Overload get(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return Util.join("\n", map.values().stream().map(Object::toString).collect(Collectors.toList()));
    }
}
