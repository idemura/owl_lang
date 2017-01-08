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

public interface JvmVisitor<T> {
    default T visitError() {
        throw new UnsupportedOperationException("visitor incomplete " + getClass().getName());
    }

    default T accept(JvmNode node) {
        return (T) node.accept(this);
    }

    default T visit(JvmApply node) { return visitError(); }
    default T visit(JvmBlock node) { return visitError(); }
    default T visit(JvmCoerce node) { return visitError(); }
    default T visit(JvmClass node) { return visitError(); }
    default T visit(JvmFunction node) { return visitError(); }
    default T visit(JvmGetField node) { return visitError(); }
    default T visit(JvmGetLocal node) { return visitError(); }
    default T visit(JvmGroup node) { return visitError(); }
    default T visit(JvmIf node) { return visitError(); }
    default T visit(JvmLiteral node) { return visitError(); }
    default T visit(JvmOp node) { return visitError(); }
    default T visit(JvmPop node) { return visitError(); }
    default T visit(JvmPutField node) { return visitError(); }
    default T visit(JvmPutLocal node) { return visitError(); }
    default T visit(JvmReturn node) { return visitError(); }
    default T visit(JvmVariable node) { return visitError(); }
}
