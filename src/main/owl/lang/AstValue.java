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

final class AstValue extends AstNode
        implements Typed {
    enum Format {
        DEC,
        HEX,
        OCT,
        STRING,
    }

    String text;
    Format format;
    AstType type;  // Deduced

    AstValue(String text, Format format) {
        this.text = text;
        this.format = format;
    }

    @Override
    public Object accept(AstVisitor v) throws OwlException {
        return v.visit(this);
    }

    @Override
    public AstType getType() {
        return type;
    }
}
