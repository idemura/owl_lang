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


public class Analyzer {
    static void analyze(Ast ast, ErrorListener errorListener) throws OwlException {
        Analyzer analyzer = new Analyzer(errorListener);
        analyzer.run(ast);
    }

    private ErrorListener errorListener;

    private Analyzer(ErrorListener listener) {
        this.errorListener = listener;
    }

    private void run(Ast ast) throws OwlException {
        AstVisitor v = new Visitor();
        ast.module.accept(v);
    }

    private final class Visitor implements AstVisitor {
        @Override
        public void visit(AstName n) {
        }

        @Override
        public void visit(AstType n) {
        }

        @Override
        public void visit(AstMember n) {
        }

        @Override
        public void visit(AstModule n) {
        }

        @Override
        public void visit(AstFunction n) {
        }

        @Override
        public void visit(AstArgument n) {
        }

        @Override
        public void visit(AstVariable n) {
        }

        @Override
        public void visit(AstBlock n) {
        }

        @Override
        public void visit(AstApply n) {
        }

        @Override
        public void visit(AstConstant n) {
        }

        @Override
        public void visit(AstIf n) {
        }

        @Override
        public void visit(AstMatch n) {
        }

        @Override
        public void visit(AstReturn n) {
        }

        @Override
        public void visit(AstExpr n) {
        }
    }
}
