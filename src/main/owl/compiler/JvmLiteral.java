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

final class JvmLiteral extends JvmNode {
    static final JvmLiteral TRUE = new JvmLiteral(1, Jvm.BOOL);
    static final JvmLiteral FALSE = new JvmLiteral(0, Jvm.BOOL);

    final Object object;
    final String type;

    JvmLiteral(Object object, String type) {
        this.object = object;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
