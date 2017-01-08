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
import javassist.bytecode.AccessFlag;

import java.util.ArrayList;
import java.util.List;

final class JvmClass extends JvmNode {
    final int flags;
    final String moduleName;
    final String name;
    private List<JvmNode> variables = new ArrayList<>();
    private List<JvmNode> functions = new ArrayList<>();

    JvmClass(boolean fpublic, String moduleName, String name) {
        this.flags = (fpublic ? AccessFlag.PUBLIC: 0);
        this.moduleName = moduleName;
        this.name = name;
    }

    String getQualifiedName() {
        return moduleName + "." + name;
    }

    void addFunction(JvmNode f) {
        functions.add(f);
    }

    void addVariable(JvmNode v) {
        variables.add(v);
    }

    List<JvmNode> getFunctions() {
        return ImmutableList.copyOf(functions);
    }

    List<JvmNode> getVariables() {
        return ImmutableList.copyOf(variables);
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
