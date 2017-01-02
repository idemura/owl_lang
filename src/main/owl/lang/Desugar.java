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

import java.util.List;

import static com.google.common.base.Preconditions.checkState;

final class Desugar {
    private Desugar() {}

    static void run(Ast ast) {
        ast.root = new Visitor().accept(ast.root);
    }

    private static final class Visitor implements AstVisitor<AstNode> {
        private final NameGen gen = new NameGen("_d_");

        private Visitor() {
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
            mapChildren(node.variables);
            mapChildren(node.functions);
            return node;
        }

        @Override
        public AstNode visit(AstFunction node) {
            gen.push();
            AstNode b = accept(node.getBlock());
            // Block doesn't change, only statements inside it
            checkState(b == node.getBlock());
            gen.pop();
            return node;
        }

        @Override
        public AstNode visit(AstVariable node) {
            node.setExpr(accept(node.getExpr()));
            return node;
        }

        @Override
        public AstNode visit(AstBlock node) {
            mapChildren(node.children);
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
        public AstNode visit(AstCoerce node) {
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
                AstApply app = new AstApply(new AstName(node.op), Util.listOf(node.l, node.r));
                return new AstAssign(null, node.l, app);
            }
            // TODO: Support field and index
            throw new UnsupportedOperationException("different code if left side is field or index");
        }

        @Override
        public AstNode visit(AstLiteral node) {
            return node;
        }

        @Override
        public AstNode visit(AstIf node) {
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    b.condition = accept(b.condition);
                }
                b.block = (AstBlock) accept(b.block);
            }
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
            mapChildren(node.children);
            return node;
        }

        private <T extends AstNode> void mapChildren(List<T> children) {
            for (int i = 0; i < children.size(); i++) {
                children.set(i, (T) accept(children.get(i)));
            }
        }
    }
}
