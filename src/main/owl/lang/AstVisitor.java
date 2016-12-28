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

interface AstVisitor<T> {
    default T visitError() {
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T accept(AstNode node) throws OwlException {
        if (node != null) {
            return (T) node.accept(this);
        } else {
            return null;
        }
    }

    default T visit(AstApply node) throws OwlException { return visitError(); }
    default T visit(AstAssign node) throws OwlException { return visitError(); }
    default T visit(AstBlock node) throws OwlException { return visitError(); }
    default T visit(AstCast node) throws OwlException { return visitError(); }
    default T visit(AstExpr node) throws OwlException { return visitError(); }
    default T visit(AstField node) throws OwlException { return visitError(); }
    default T visit(AstFunction node) throws OwlException { return visitError(); }
    default T visit(AstGroup node) throws OwlException { return visitError(); }
    default T visit(AstIf node) throws OwlException { return visitError(); }
    default T visit(AstModule node) throws OwlException { return visitError(); }
    default T visit(AstName node) throws OwlException { return visitError(); }
    default T visit(AstNew node) throws OwlException { return visitError(); }
    default T visit(AstReturn node) throws OwlException { return visitError(); }
    default T visit(AstType node) throws OwlException { return visitError(); }
    default T visit(AstValue node) throws OwlException { return visitError(); }
    default T visit(AstVariable node) throws OwlException { return visitError(); }
}