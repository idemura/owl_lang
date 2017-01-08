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

final class JvmCoerce extends JvmNode {
    static final int I2L = 0;
    static final int L2I = 1;
    static final int REF = 100;

    final int kind;
    final String type;

    JvmCoerce(int kind, String type) {
        this.kind = kind;
        this.type = type;
    }

    @Override
    Object accept(JvmVisitor v) {
        return v.visit(this);
    }
}
