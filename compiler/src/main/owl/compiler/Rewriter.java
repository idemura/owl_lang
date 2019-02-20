package owl.compiler;

import owl.compiler.AstFor.ForBoolean;
import owl.compiler.AstFor.ForRange;

import java.util.List;

// Generates assignments for array and field
final class Rewriter {
    static void run(Ast ast) {
        ast.accept(new Visitor());
    }

    final static class Visitor implements AstVisitor<AstNode> {
        @Override
        public AstNode visit(AstModule node) {
            for (AstNode v : node.variables) {
                accept(v);
            }
            for (AstNode f : node.functions) {
                accept(f);
            }
            return node;
        }

        @Override
        public AstNode visit(AstFunction node) {
            // Block never changes, it remains same block
            accept(node.block);
            return node;
        }

        @Override
        public AstNode visit(AstVariable node) {
            node.expr = accept(node.expr);
            return node;
        }

        @Override
        public AstNode visit(AstBlock node) {
            visitEach(node.children);
            return node;
        }

        @Override
        public AstNode visit(AstGroup node) {
            visitEach(node.children);
            return node;
        }

        @Override
        public AstNode visit(AstIf node) {
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    b.condition = accept(b.condition);
                }
                accept(b.block);
            }
            return node;
        }

        @Override
        public AstNode visit(AstFor node) {
            if (node.condition instanceof ForBoolean) {
                ForBoolean cond = (ForBoolean) node.condition;
                cond.expr = accept(cond.expr);
            } else {
                ForRange range = (ForRange) node.condition;
                range.iter = (AstVariable) accept(range.iter);
                range.last = (AstVariable) accept(range.last);
                range.expr = accept(range.expr);
                range.increment = accept(range.increment);
            }
            accept(node.block);
            return node;
        }

        @Override
        public AstNode visit(AstReturn node) {
            if (node.expr != null) {
                node.expr = accept(node.expr);
            }
            return node;
        }

        @Override
        public AstNode visit(AstExpr node) {
            node.expr = accept(node.expr);
            return node;
        }

        @Override
        public AstNode visit(AstApply node) {
            return node;
        }

        @Override
        public AstNode visit(AstAssign node) {
            if (node.l instanceof AstIndex) {
                AstIndex index = (AstIndex) node.l;
                return new AstAssignIndex(index.array, index.index, node.r);
            }
            return node;
        }

        @Override
        public AstNode visit(AstCoerce node) {
            return node;
        }

        @Override
        public AstNode visit(AstSelect node) {
            return node;
        }

        @Override
        public AstNode visit(AstLiteral node) {
            return node;
        }

        @Override
        public AstNode visit(AstName node) {
            return node;
        }

        @Override
        public AstNode visit(AstNew node) {
            return node;
        }

        @Override
        public AstNode visit(AstType node) {
            return node;
        }

        private void visitEach(List<AstNode> children) {
            for (int i = 0; i < children.size(); i++) {
                children.set(i, accept(children.get(i)));
            }
        }
    }
}
