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
package owl.compiler;

import java.util.List;

class ResolveResult extends Exception {
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
