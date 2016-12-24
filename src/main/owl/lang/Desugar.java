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

import java.util.stream.Collectors;

final class Desugar {
    private Desugar() {}

    static void run(Ast ast, ErrorListener errorListener) throws OwlException {
        ast.root = new Visitor(errorListener).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor<AstNode> {
        private final ErrorListener errorListener;
        private final NameGen gen = new NameGen("_l_");

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        private void error(AstNode node, String msg) {
            errorListener.error(node.getLine(), node.getCharPosition(), msg);
        }

        @Override
        public AstNode visit(AstName node) {
            return node;
        }

        @Override
        public AstNode visit(AstField node) {
            return node;
        }

        @Override
        public AstNode visit(AstModule node) {
            node.children = node.children.stream()
                    .map(this::accept)
                    .collect(Collectors.toList());
            return node;
        }

        @Override
        public AstNode visit(AstFunction node) {
            gen.push();
            node.block = (AstBlock) accept(node.block);
            gen.pop();
            return node;
        }

        @Override
        public AstNode visit(AstVariable node) {
            node.expr = accept(node.expr);
            return node;
        }

        @Override
        public AstNode visit(AstBlock node) {
            node.children = node.children.stream()
                    .map(this::accept)
                    .collect(Collectors.toList());
            return node;
        }

        @Override
        public AstNode visit(AstApply node) {
            // TODO: Desugar a < b < c to a < b && b < c or even
            //      var _l_b = b;
            //      a < _l_b && _l_b < c;
            return node;
        }

        @Override
        public AstNode visit(AstCast node) {
            return node;
        }

        @Override
        public AstNode visit(AstAssign node) {
            node.l = accept(node.l);
            node.r = accept(node.r);
            if (node.op.isEmpty()) {
                node.op = null;
                return node;
            }
            if (node.l instanceof AstName) {
                AstApply app = new AstApply();
                app.fn = new AstName(node.op);
                app.add(node.l);
                app.add(node.r);

                return new AstAssign(null, node.l, app);
            }
            // TODO: Support field and index
            throw new UnsupportedOperationException("different code if left side is field or index");
        }

        @Override
        public AstNode visit(AstValue node) {
            return node;
        }

        @Override
        public AstNode visit(AstIf node) {
            return node;
        }

        @Override
        public AstNode visit(AstReturn node) {
            node.expr = accept(node.expr);
            return node;
        }

        @Override
        public AstNode visit(AstExpr node) {
            node.expr = accept(node.expr);
            return node;
        }

        @Override
        public AstNode visit(AstGroup node) {
            node.children = node.children.stream()
                    .map(this::accept)
                    .collect(Collectors.toList());
            return node;
        }
    }
}
