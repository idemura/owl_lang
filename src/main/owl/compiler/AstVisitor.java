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

interface AstVisitor<T> {
    default T accept(AstNode node) {
        return (T) node.accept(this);
    }

    default T visitError() {
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T visit(AstApply node) { return visitError(); }
    default T visit(AstAssign node) { return visitError(); }
    default T visit(AstBlock node) { return visitError(); }
    default T visit(AstCoerce node) { return visitError(); }
    default T visit(AstExpr node) { return visitError(); }
    default T visit(AstSelect node) { return visitError(); }
    default T visit(AstFunction node) { return visitError(); }
    default T visit(AstGroup node) { return visitError(); }
    default T visit(AstIf node) { return visitError(); }
    default T visit(AstLiteral node) { return visitError(); }
    default T visit(AstModule node) { return visitError(); }
    default T visit(AstName node) { return visitError(); }
    default T visit(AstNew node) { return visitError(); }
    default T visit(AstReturn node) { return visitError(); }
    default T visit(AstType node) { return visitError(); }
    default T visit(AstVariable node) { return visitError(); }
    default T visit(AstFor node) { return visitError(); }
}
