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
package owl.lang;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

final class AstFunction extends AstNode
        implements Entity {
    private String moduleName;
    private String name;
    private List<AstVariable> args = new ArrayList<>();
    private List<AstVariable> vars = new ArrayList<>();
    private AstType returnType = AstType.NONE;
    private AstBlock block;

    AstFunction(
            String moduleName,
            String name,
            List<AstVariable> args,
            AstType returnType,
            AstBlock block) {
        this.moduleName = moduleName;
        this.name = name;
        this.args = args;
        this.returnType = returnType == null? AstType.NONE: returnType;
        this.block = block;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    void addVar(AstVariable node) {
        vars.add(node);
    }

    List<AstVariable> getArgs() {
        return ImmutableList.copyOf(args);
    }

    List<AstVariable> getVars() {
        return ImmutableList.copyOf(vars);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        list.add("function");
        if (moduleName == null) {
            if (name != null) {
                list.add(name);
            }
        } else {
            list.add(moduleName + "." + name);
        }
        list.add("type=" + getType());
        return String.join(" ", list);
    }

    @Override
    public int hashCode() {
        return Entity.getHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AstFunction) {
            return Entity.equals(this, (AstFunction) other);
        }
        return false;
    }

    // Full function type, not just return
    @Override
    public AstType getType() {
        List<AstType> typeArgs = new ArrayList<>();
        for (AstVariable a : args) {
            if (a.getType().equals(AstType.NONE)) {
                throw new IllegalStateException("no argument type");
            }
            typeArgs.add(a.getType());
        }
        typeArgs.add(returnType);
        return AstType.functionOf(typeArgs);
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUniqueName() {
        return name;
    }

    AstType getReturnType() {
        return returnType;
    }

    AstBlock getBlock() {
        return block;
    }

    void indexLocals() {
        int i = 0;
        for (AstVariable a : args) {
            a.index = i++;
        }
        NameGen gen = new NameGen("_l_");
        for (AstVariable v : vars) {
            v.setUniqueName(gen.newName());
            v.index = i++;
        }
    }

    Entity getLocal(int index) {
        if (index < args.size()) {
            return args.get(index);
        } else {
            return vars.get(index - args.size());
        }
    }
}
