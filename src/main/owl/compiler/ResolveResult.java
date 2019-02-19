package owl.compiler;

import java.util.List;

final class ResolveResult extends Exception {
    static ResolveResult found(Entity ent) {
        return new ResolveResult(ent, null);
    }

    static ResolveResult error(List<Entity> candidates) {
        return new ResolveResult(null, candidates);
    }

    private final Entity ent;
    private final List<Entity> candidates;

    private ResolveResult(Entity ent, List<Entity> candidates) {
        this.ent = ent;
        this.candidates = candidates;
    }

    boolean found() {
        return ent != null;
    }

    Entity get() {
        Util.check(ent != null);
        return ent;
    }

    List<Entity> getCandidates() {
        Util.check(ent == null);
        return candidates;
    }
}
