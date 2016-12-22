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
        public AstNode visit(AstType node) {
            return node;
        }

        @Override
        public AstNode visit(AstMember node) {
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
            // Do not desugar arguments, nothing will happen anyways.
            node.block = (AstBlock) accept(node.block);
            gen.pop();
            return node;
        }

        @Override
        public AstNode visit(AstArgument node) {
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
        public AstNode visit(AstAssign node) {
            node.l = accept(node.l);
            node.r = accept(node.r);
            if (node.op.isEmpty()) {
                node.op = null;
                return node;
            }
            AstGroup g = new AstGroup();
            String temp = gen.newName();
            g.add(new AstAssign(null, new AstName(temp), node.l));
            AstApply app = new AstApply();
            app.fn = new AstName(node.op);
            app.add(new AstName(temp));
            app.add(node.r);
            g.add(new AstAssign(null, node.l, app));
            return g;
        }

        @Override
        public AstNode visit(AstConstant node) {
            return accept(node.expr);
        }

        @Override
        public AstNode visit(AstValue node) {
            return node;
        }

        @Override
        public AstNode visit(AstIf node) {
            node.children = node.children.stream()
                    .map(n -> (AstCond) accept(n))
                    .collect(Collectors.toList());
            return node;
        }

        @Override
        public AstNode visit(AstCond node) {
            node.condition = (AstExpr) accept(node.condition);
            node.block = (AstBlock) accept(node.block);
            return node;
        }

        @Override
        public AstNode visit(AstMatch node) {
            node.children = node.children.stream()
                    .map(n -> (AstCase) accept(n))
                    .collect(Collectors.toList());
            return node;
        }

        @Override
        public AstNode visit(AstCase node) {
            node.block = (AstBlock) accept(node.block);
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
