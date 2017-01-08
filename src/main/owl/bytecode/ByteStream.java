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
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

final class ByteStream {
    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private final DataOutput writer = new DataOutputStream(stream);

    ByteArrayOutputStream getByteStream() {
        return stream;
    }

    void writeStream(ByteArrayOutputStream s) {
        try {
            s.writeTo(stream);
        } catch (IOException ignored) {
        }
    }

    void writeByte(int v) {
        try {
            writer.writeByte(v);
        } catch (IOException ignored) {
        }
    }

    void writeI16(int v) {
        try {
            writer.writeShort(v);
        } catch (IOException ignored) {
        }
    }

    void writeI32(int v) {
        try {
            writer.writeInt(v);
        } catch (IOException ignored) {
        }
    }

    void writeI64(long v) {
        try {
            writer.writeLong(v);
        } catch (IOException ignored) {
        }
    }

    void writeF32(float v) {
        try {
            writer.writeFloat(v);
        } catch (IOException ignored) {
        }
    }

    void writeF64(double v) {
        try {
            writer.writeDouble(v);
        } catch (IOException ignored) {
        }
    }

    void writeString(String v) {
        try {
            writer.writeUTF(v);
        } catch (IOException ignored) {
        }
    }
}
