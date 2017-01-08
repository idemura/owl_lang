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

import java.io.ByteArrayOutputStream;

final class CodeWriter {
    private final ConstPool cp;
    private final ByteStream writer = new ByteStream();

    CodeWriter(ConstPool cp) {
        this.cp = cp;
    }

    ByteArrayOutputStream serialize() {
        return writer.getByteStream();
    }

    void ipush(int n) {
        if (isByte(n)) {
            writer.writeByte(0x10);
            writer.writeByte(n);
        } else {
            int index = cp.addLiteral(n);
            if (isByte(index)) {
                writer.writeByte(0x12);
                writer.writeByte(index);
            } else {
                writer.writeByte(0x13);
                writer.writeI16(index);
            }
        }
    }

    private static boolean isByte(int n) {
        return Byte.MIN_VALUE <= n && n <= Byte.MAX_VALUE;
    }
}
