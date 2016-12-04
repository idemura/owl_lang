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

// Check types of function applications. Resolves entity names and function overloads.
class CodeGenerator {
    static Jvm generate(Ast ast, ErrorListener errorListener) throws OwlException {
        return new Jvm(new CodeGenerator(ast, errorListener).run());
    }

    private final Ast ast;
    private CountErrorListener errorListener;

    private CodeGenerator(Ast ast, ErrorListener errorListener) {
        this.ast = ast;
        this.errorListener = new CountErrorListener(errorListener);
    }

    private void error(AstNode n, String msg) {
        errorListener.error(n.line, n.charPositionInLine, msg);
    }

    private JvmNode run() throws OwlException {
        GeneratorVisitor v = new GeneratorVisitor();
        ast.accept(v);
        return v.root;

    }

    private final class GeneratorVisitor implements AstVisitor {
        JvmNode root = null;

        @Override
        public void visit(AstName n) {
        }

        @Override
        public void visit(AstType n) {
        }

        @Override
        public void visit(AstMember n) {
            root = null;
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
        public void visit(AstLiteral n) {
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
            accept(n.expr);
        }
    }
}
