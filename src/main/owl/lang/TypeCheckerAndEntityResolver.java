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

class TypeCheckerAndEntityResolver {
    static void analyze(Ast ast, EntityMap entityMap, ErrorListener errorListener) throws OwlException {
        new TypeCheckerAndEntityResolver(ast, entityMap, errorListener).run();
    }

    private Ast ast;
    private EntityMap entityMap;
    private CountErrorListener errorListener;

    private TypeCheckerAndEntityResolver(Ast ast, EntityMap entityMap, ErrorListener errorListener) {
        this.ast = ast;
        this.entityMap = entityMap;
        this.errorListener = new CountErrorListener(errorListener);
    }

    private void error(AstNode n, String msg) {
        errorListener.error(n.line, n.charPositionInLine, msg);
    }

    private void run() throws OwlException {
        AstVisitor v = new AnalyzerVisitor();
        ast.module.accept(v);
    }

    private final class AnalyzerVisitor implements AstVisitor {
        @Override
        public void visit(AstModule n) {
            for (AstNode m : n.members) {
                m.accept(this);
            }
        }

        @Override
        public void visit(AstFunction n) {
            n.block.accept(this);
        }

        @Override
        public void visit(AstVariable n) {
            n.expr.accept(this);
        }

        @Override
        public void visit(AstBlock n) {
            for (AstNode s : n.statements) {
                s.accept(this);
            }
        }

        @Override
        public void visit(AstApply n) {

        }
    }
}
