package owl.compiler;

import java.util.Objects;

interface Entity extends Typed, Named {
    String getJvmDescriptor();

    static int getHashCode(Entity e) {
        Util.check(e.getType() != null);
        return Objects.hash(e.getModuleName(), e.getName(), e.getType());
    }

    static boolean equals(Entity l, Entity r) {
        return l.getModuleName().equals(r.getModuleName()) &&
                l.getName().equals(r.getName()) &&
                l.getType().equals(r.getType());
    }
}
