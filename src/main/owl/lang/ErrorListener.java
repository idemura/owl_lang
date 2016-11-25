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


public class ErrorListener {
    private String fileName;
    private int errorCount = 0;

    public ErrorListener(String fileName) {
        this.fileName = fileName;
    }

    public void error(
            int line,
            int charPositionInLine,
            String msg) {
        String position = null;
        if (line > 0) {
            position = String.valueOf(line);
        }
        if (msg == null) {
            msg = "unknown";
        }
        print(position, "error: " + msg);
        errorCount++;
    }

    public void error(
            int line,
            int charPositionInLine,
            Exception e) {
        error(line, charPositionInLine, e.getMessage());
    }

    public int getErrorCount() {
        return errorCount;
    }

    private void print(String position, String text) {
        String fileWithPosition = fileName;
        if (position != null) {
            fileWithPosition += ":" + position;
        }
        System.err.println(fileWithPosition + ": " + text);
    }
}
