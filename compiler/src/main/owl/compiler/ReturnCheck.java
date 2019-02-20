package owl.compiler;

// Check types of function applications. Resolves entity names and function overloads.
final class ReturnCheck {
    private ReturnCheck() {}

    static boolean run(AstFunction function, ErrorListener errorListener) {
        CountErrorListener countErrorListener = new CountErrorListener(errorListener);
        new Visitor(errorListener).accept(function);
        return countErrorListener.getErrorCount() == 0;
    }

    private static final class Visitor implements AstVisitor<AstNode> {
        private final ErrorListener errorListener;
        private AstFunction function;

        private Visitor(ErrorListener errorListener) {
            this.errorListener = errorListener;
        }

        @Override
        public AstNode visit(AstFunction node) {
            // We should not recurse into a new function
            Util.check(function == null);
            function = node;
            AstNode ret = accept(node.block);
            if (ret != null) {
                if (ret != Util.last(node.block.children)) {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "unreachable statements after return in " + function.getName());
                }
            } else {
                if (node.getReturnType().equals(AstType.NONE)) {
                    node.block.add(new AstReturn(null));
                } else {
                    errorListener.error(node.getLine(), node.getCharPosition(),
                            "missing return statement in " + function.getName());
                }
            }
            return ret;
        }

        @Override
        public AstNode visit(AstBlock node) {
            for (AstNode s : node.children) {
                AstNode ret = accept(s);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }

        @Override
        public AstNode visit(AstVariable node) {
            return null;
        }

        @Override
        public AstNode visit(AstIf node) {
            for (AstIf.Branch b : node.branches) {
                AstNode ret = accept(b.block);
                if (ret == null) {
                    return null;
                }
            }
            if (Util.last(node.branches).condition != null) {
                return null;
            }
            return node;
        }

        @Override
        public AstNode visit(AstFor node) {
            return null;
        }

        @Override
        public AstNode visit(AstReturn node) {
            return node;
        }

        @Override
        public AstNode visit(AstExpr node) {
            return null;
        }

        @Override
        public AstNode visit(AstGroup node) {
            for (AstNode c : node.children) {
                AstNode ret = accept(c);
                if (ret != null) {
                    return ret;
                }
            }
            return null;
        }
    }
}
