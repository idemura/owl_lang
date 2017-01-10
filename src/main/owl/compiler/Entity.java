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
