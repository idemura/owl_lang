package owl.compiler;

import owl.compiler.AstFor.ForBoolean;
import owl.compiler.AstFor.ForRange;

import java.io.PrintStream;

final class DebugPrint {
    private DebugPrint() {}

    static void printAst(Ast ast, PrintStream out) {
        ast.accept(new Visitor(new IndentPrinter(out)));
    }

    private static final class Visitor implements AstVisitor<Void> {
        private final IndentPrinter printer;

        private Visitor(IndentPrinter printer) {
            this.printer = printer;
        }

        @Override
        public Void visit(AstName node) {
            leaf(node, node.name);
            return null;
        }

        @Override
        public Void visit(AstSelect node) {
            beginNode(node, node.field);
            accept(node.object);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstModule node) {
            beginNode(node, node.name);
            for (AstNode f : node.variables) {
                accept(f);
                printer.println("");
            }
            for (AstNode f : node.functions) {
                accept(f);
                printer.println("");
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            beginNode(node, node.getName());
            if (node.getReturnType() != null) {
                prop("ReturnType", node.getReturnType().toString());
            }
            if (node.getMethodName() != null) {
                prop("MethodName", node.getMethodName());
            }
            for (AstVariable a : node.getArgs()) {
                leaf(a, a.getName() + ": " + a.getType());
            }
            accept(node.block);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            beginNode(node, node.getName());
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            beginNode(node);
            for (AstNode s : node.children) {
                accept(s);
                printer.println(";");
            }
            endNode();
            printer.println(";;");
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            beginNode(node);
            accept(node.fn);
            node.args.forEach(this::accept);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstIndex node) {
            beginNode(node);
            accept(node.array);
            accept(node.index);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstLiteral node) {
            String s = node.object.toString();
            if (node.object instanceof String) {
                s = "\"" + s + "\"";
            }
            leaf(node, s + " " + node.getType());
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            beginNode(node);
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    accept(b.condition);
                }
                accept(b.block);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstFor node) {
            beginNode(node);
            if (node.condition instanceof ForBoolean) {
                ForBoolean cond = (ForBoolean) node.condition;
                accept(cond.expr);
            } else {
                ForRange range = (ForRange) node.condition;
                printer.print("iter:");
                accept(range.iter);
                printer.print("last:");
                accept(range.last);
            }
            accept(node.block);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstReturn node) {
            beginNode(node);
            if (node.expr != null) {
                accept(node.expr);
            }
            endNode();
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            beginNode(node);
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstCoerce node) {
            beginNode(node, node.getType().toString());
            accept(node.expr);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstGroup node) {
            beginNode(node);
            node.children.forEach(this::accept);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstAssign node) {
            beginNode(node);
            accept(node.l);
            accept(node.r);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstAssignIndex node) {
            beginNode(node);
            accept(node.array);
            accept(node.index);
            accept(node.r);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstNew node) {
            beginNode(node, node.type.toString());
            node.init.forEach(this::accept);
            endNode();
            return null;
        }

        @Override
        public Void visit(AstObject node) {
            beginNode(node);
            prop("name:", node.getModuleName() + "::" + node.getName());
            node.fields.forEach(this::accept);
            endNode();
            return null;
        }

        private void prop(String name, String s) {
            printer.println(name + ": " + s);
        }

        private void leaf(Object node) {
            printer.println(getClassName(node));
        }

        private void leaf(Object node, String s) {
            printer.println(getClassName(node), s);
        }

        private void beginNode(Object node) {
            printer.println(getClassName(node));
            printer.indent();
        }

        private void beginNode(Object node, String s) {
            printer.println(getClassName(node), s);
            printer.indent();
        }

        private void endNode() {
            printer.unindent();
        }

        private static String getClassName(Object node) {
            String fullName = node.getClass().getName();
            String name = fullName.substring(fullName.lastIndexOf('.') + 1);
            if (name.startsWith("Ast")) {
                name = name.substring(3);
            }
            return name;
        }
    }
}
