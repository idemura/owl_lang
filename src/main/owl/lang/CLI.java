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

import java.io.*;

public class CLI {
    public static void main(String[] args) {
        boolean anyFailed = false;
        for (String fileName : args) {
            try (InputStream in = new FileInputStream(new File(fileName))) {
                compile(in);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                anyFailed = true;
            }
        }
        System.exit(anyFailed ? 1 : 0);
    }

    private static void compile(InputStream in) throws OwlException {
        Ast ast = AstUtil.parse(in);
        ast.module.accept(new DebugPrintVisitor());
    }
}
