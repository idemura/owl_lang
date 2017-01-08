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

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

final class JvmBlock extends JvmNode {
    private List<JvmNode> instr = new ArrayList<>();

    static JvmBlock of(JvmNode node) {
        JvmBlock b = new JvmBlock();
        b.add(node);
        return b;
    }

    void add(JvmNode node) {
        instr.add(node);
    }

    List<JvmNode> getInstructions() {
        return ImmutableList.copyOf(instr);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
