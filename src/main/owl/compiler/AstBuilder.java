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
package owl.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static owl.compiler.OwlParser.*;

// Builds AST from parse tree (PT), or maps PT to AST. This is failure-less operation given a valid PT.
// Performs desugaring.
// Checks and possible actions that may result an error are performed on separate stages.
final class AstBuilder extends AbstractParseTreeVisitor<AstNode>
        implements OwlVisitor<AstNode> {
    static Ast run(ModuleContext ctx) {
        return new Ast(ctx.accept(new AstBuilder()));
    }

    private String moduleName;
    private int functionNestLevel = 0;

    private AstBuilder() {}

    private static String qualifiedNameText(QualifiedNameContext ctx) {
        return String.join("::", ctx.NAME().stream().map(t -> t.getSymbol().getText()).collect(Collectors.toList()));
    }

    private AstNode accept(ParserRuleContext ctx) {
        if (ctx != null) {
            return ctx.accept(this);
        } else {
            return null;
        }
    }

    private String getTextOpt(TerminalNode term) {
        return term == null? null: term.getText();
    }

    private String getEntityModuleName() {
        return functionNestLevel > 0? null: moduleName;
    }

    @Override
    public AstNode visitModule(ModuleContext ctx) {
        AstModule m = new AstModule();
        m.name = ctx.NAME().getText();
        moduleName = m.name;
        for (VariableContext vc : ctx.variable()) {
            m.variables.add((AstVariable) accept(vc));
        }
        for (FunctionContext fc : ctx.function()) {
            m.functions.add((AstFunction) accept(fc));
        }
        return m;
    }

    @Override
    public AstNode visitQualifiedName(QualifiedNameContext ctx) {
        throw new UnsupportedOperationException("ast builder");
    }

    @Override
    public AstNode visitVariable(VariableContext ctx) {
        if (functionNestLevel > 0) {
            return new AstVariable(
                    new AstVariable.Local(),
                    null,
                    ctx.NAME().getText(),
                    null,
                    accept(ctx.expression()));
        } else {
            return new AstVariable(
                    new AstVariable.Module(),
                    moduleName,
                    ctx.NAME().getText(),
                    null,
                    accept(ctx.expression()));
        }
    }

    @Override
    public AstNode visitFunction(FunctionContext ctx) {
        functionNestLevel++;
        List<AstVariable> args;
        if (ctx.argument() == null) {
            args = new ArrayList<>();
        } else {
            args = ctx.argument().stream()
                    .map(a -> (AstVariable) accept(a))
                    .collect(Collectors.toList());
        }
        AstNode r = new AstFunction(
                getEntityModuleName(),
                getTextOpt(ctx.NAME()),
                null,
                args,
                (AstType) accept(ctx.type()),
                (AstBlock) accept(ctx.block()));
        functionNestLevel--;
        return r;
    }

    @Override
    public AstNode visitArgument(ArgumentContext ctx) {
        return new AstVariable(
                new AstVariable.Local(),
                null,
                ctx.NAME().getText(),
                (AstType) accept(ctx.type()),
                null);
    }

    @Override
    public AstNode visitBlock(BlockContext ctx) {
        AstBlock b = new AstBlock();
        ctx.statement().forEach(s -> b.add(accept(s)));
        return b;
    }

    @Override
    public AstNode visitExprPrime(ExprPrimeContext ctx) {
        if (ctx.exprNew() != null) {
            return accept(ctx.exprNew());
        }
        if (ctx.NAME() != null) {
            return new AstName(ctx.NAME().getText());
        }
        if (ctx.BOOL() != null) {
            return new AstLiteral(ctx.BOOL().getText().equals("true")? Boolean.TRUE: Boolean.FALSE, AstType.BOOL);
        }
        if (ctx.INT() != null) {
            // TODO: Check range
            // TODO: Deduce type
            return new AstLiteral(Integer.valueOf(ctx.INT().getText()), AstType.I32);
        }
        if (ctx.STRING() != null) {
            String text = ctx.STRING().getText();
            return new AstLiteral(text.substring(1, text.length() - 1), AstType.STRING);
        }
        return accept(ctx.expression());
    }

    @Override
    public AstNode visitExprNew(ExprNewContext ctx) {
        List<AstNode> init = ctx.expression().stream().map(this::accept).collect(Collectors.toList());
        return new AstNew((AstType) accept(ctx.type()), init);
    }

    @Override
    public AstNode visitExprApply(ExprApplyContext ctx) {
        if (ctx.exprPrime() != null) {
            return accept(ctx.exprPrime());
        }
        String op = ctx.op.getText();
        if (op.equals("(")) {
            return new AstApply(
                    accept(ctx.exprApply()),
                    ctx.expression().stream().map(this::accept).collect(Collectors.toList()));
        }
        if (op.equals("[")) {
            throw new UnsupportedOperationException("index is not implemented");
        }
        if (op.equals(".")) {
            throw new UnsupportedOperationException("field is not implemented");
        }
        throw new IllegalStateException("unknown op " + op);
    }

    @Override
    public AstNode visitExprCoerce(ExprCoerceContext ctx) {
        AstNode l = accept(ctx.exprApply());
        if (ctx.type() == null) {
            return l;
        }
        return new AstCoerce(l, (AstType) accept(ctx.type()));
    }

    @Override
    public AstNode visitExprUnary(ExprUnaryContext ctx) {
        AstNode l = accept(ctx.t);
        if (ctx.op == null) {
            return l;
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(l));
    }

    @Override
    public AstNode visitExprMulDiv(ExprMulDivContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprAddSub(ExprAddSubContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprShift(ExprShiftContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprBitAnd(ExprBitAndContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprBitXor(ExprBitXorContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprBitOr(ExprBitOrContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprCmp(ExprCmpContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprEq(ExprEqContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprNot(OwlParser.ExprNotContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l)));
    }

    @Override
    public AstNode visitExprAnd(ExprAndContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprXor(ExprXorContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExprOr(ExprOrContext ctx) {
        if (ctx.op == null) {
            return accept(ctx.t);
        }
        return new AstApply(new AstName(ctx.op.getText()), Util.listOf(
                accept(ctx.l),
                accept(ctx.r)));
    }

    @Override
    public AstNode visitExpression(ExpressionContext ctx) {
        return accept(ctx.t);
    }

    @Override
    public AstNode visitAssignment(AssignmentContext ctx) {
        AstNode l = accept(ctx.l);
        AstNode r = accept(ctx.r);
        if (ctx.op == null) {
            return new AstExpr(l);
        }
        String operator = Util.removeSuffix(ctx.op.getText(), 1);
        if (!operator.isEmpty()) {
            r = new AstApply(new AstName(operator), Util.listOf(l, r));
        }
        return new AstExpr(new AstAssign(l, r));
    }

    @Override
    public AstNode visitStmtIf(StmtIfContext ctx) {
        AstIf stmtIf = new AstIf();
        for (int i = 0; i < ctx.expression().size(); i++) {
            stmtIf.add(accept(ctx.expression(i)), (AstBlock) accept(ctx.block(i)));
        }
        if (ctx.block().size() > ctx.expression().size()) {
            stmtIf.add(null, (AstBlock) accept(ctx.block(ctx.block().size() - 1)));
        }
        return stmtIf;
    }

    @Override
    public AstNode visitStmtFor(StmtForContext ctx) {
        return new AstFor(accept(ctx.expression()), (AstBlock) accept(ctx.block()));
    }

    @Override
    public AstNode visitStmtReturn(StmtReturnContext ctx) {
        return new AstReturn(accept(ctx.expression()));
    }

    @Override
    public AstNode visitStatement(StatementContext ctx) {
        if (ctx.assignment() != null) {
            return accept(ctx.assignment());
        }
        if (ctx.stmtIf() != null) {
            return accept(ctx.stmtIf());
        }
        if (ctx.stmtReturn() != null) {
            return accept(ctx.stmtReturn());
        }
        if (ctx.variable() != null) {
            return accept(ctx.variable());
        }
        if (ctx.stmtFor() != null) {
            return accept(ctx.stmtFor());
        }
        throw new IllegalStateException("unknown statement");
    }

    @Override
    public AstNode visitArrayTypeSuffix(ArrayTypeSuffixContext ctx) {
        throw new UnsupportedOperationException("array suffix");
    }

    @Override
    public AstNode visitTypeConstructor(TypeConstructorContext ctx) {
        if (ctx.qualifiedName() == null) {
            return accept(ctx.type(0));
        }

        AstType t;
        String name = qualifiedNameText(ctx.qualifiedName());
        if (ctx.type().isEmpty()) {
            t = new AstType(name);
        } else {
            t = new AstType(name,
                    ctx.type().stream()
                            .map(x -> (AstType) accept(x))
                            .collect(Collectors.toList()));
        }
        if (ctx.arrayTypeSuffix() != null) {
            for (int i = 0; i < ctx.arrayTypeSuffix().size(); i++) {
                t = AstType.arrayOf(t);
            }
        }
        return t;
    }

    @Override
    public AstNode visitType(TypeContext ctx) {
        if (ctx.typeConstructor().size() > 1) {
            return AstType.functionOf(ctx.typeConstructor().stream()
                    .map(x -> (AstType) accept(x))
                    .collect(Collectors.toList()));
        } else {
            return accept(ctx.typeConstructor().get(0));
        }
    }
}
