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

final class AstApply extends AstNode
        implements Typed {
    AstNode fn;
    List<AstNode> args;
    // We can't take apply type as function return type because function return type is the result of deduction on
    // function type parameters given argument types. Consider: fn f(x, y: T): T { }. So type may vary in different
    // function application contexts.
    AstType type;  // Deduced

    AstApply(AstNode fn, List<AstNode> args) {
        this.fn = fn;
        this.args = args;
    }

    @Override
    public Object accept(AstVisitor v) {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }

    List<AstType> getArgTypes() {
        List<AstType> types = new ArrayList<>();
        for (AstNode a  : args) {
            types.add(((Typed) a).getType());
        }
        return ImmutableList.copyOf(types);
    }
}
