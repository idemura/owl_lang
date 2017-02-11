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

final class AstFor extends AstNode {
    static abstract class Condition {}

    final static class ForBoolean extends Condition {
        AstNode expr;

        ForBoolean(AstNode expr) {
            this.expr = expr;
        }
    }

    final static class ForRange extends Condition {
        AstVariable iter;
        AstVariable last;
        AstNode expr;
        AstNode increment;

        ForRange(AstVariable iter, AstVariable last) {
            this.iter = iter;
            this.last = last;
        }
    }

    Condition condition;
    AstBlock block;

    AstFor(AstNode expr, AstBlock block) {
        this.condition = new ForBoolean(expr);
        this.block = block;
    }

    AstFor(AstVariable init, AstVariable last, AstBlock block) {
        this.condition = new ForRange(init, last);
        this.block = block;
    }

    @Override
    public <T> T accept(AstVisitor<T> v) {
        return v.visit(this);
    }
}
