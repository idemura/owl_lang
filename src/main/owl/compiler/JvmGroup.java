/*
 * Copyright 2017 Igor Demura
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


import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

final class JvmGroup extends JvmNode {
    final List<JvmNode> children;

    JvmGroup() {
        this.children = new ArrayList<>();
    }

    JvmGroup(List<JvmNode> children) {
        this.children = new ArrayList<>();
        for (JvmNode n : children) {
            add(n);
        }
    }

    void add(JvmNode node) {
        checkNotNull(node);
        children.add(node);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
