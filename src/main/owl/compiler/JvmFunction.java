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

import javassist.bytecode.AccessFlag;

import java.util.List;

final class JvmFunction extends JvmNode {
    final static class Var {
        final int index;
        final int itype;

        Var(int index, int itype) {
            this.index = index;
            this.itype = itype;
        }
    }

    final int flags;
    final String name;
    final String desc;
    final List<Var> vars;
    final JvmBlock block;

    JvmFunction(
            boolean fpublic,
            boolean fstatic,
            String name,
            String desc,
            List<Var> vars,
            JvmBlock block) {
        this.flags = (fpublic? AccessFlag.PUBLIC: 0) | (fstatic? AccessFlag.STATIC: 0);
        this.name = name;
        this.desc = desc;
        this.vars = vars;
        this.block = block;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
