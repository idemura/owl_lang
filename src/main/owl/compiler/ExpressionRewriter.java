/*
 * Copyright 2017 Igor Demura
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

import java.util.stream.Collectors;

class ExpressionRewriter implements AstVisitor<AstNode> {
    @Override
    public AstNode visit(AstModule node) {
        for (AstNode v : node.variables) {
            accept(v);
        }
        for (AstNode f : node.functions) {
            accept(f);
        }
        return null;
    }

    @Override
    public AstNode visit(AstFunction node) {
        accept(node.getBlock());
        return null;
    }

    @Override
    public AstNode visit(AstVariable node) {
        node.expr = accept(node.expr);
        return null;
    }

    @Override
    public AstNode visit(AstBlock node) {
        node.children = node.children.stream().map(this::accept).collect(Collectors.toList());
        return null;
    }

    @Override
    public AstNode visit(AstGroup node) {
        node.children = node.children.stream().map(this::accept).collect(Collectors.toList());
        return null;
    }

    @Override
    public AstNode visit(AstIf node) {
        for (AstIf.Branch b : node.branches) {
            if (b.condition != null) {
                b.condition = accept(b.condition);
            }
            accept(b.block);
        }
        return null;
    }

    @Override
    public AstNode visit(AstFor node) {
        node.condition = accept(node.condition);
        accept(node.block);
        return null;
    }

    @Override
    public AstNode visit(AstReturn node) {
        if (node.expr != null) {
            node.expr = accept(node.expr);
        }
        return null;
    }

    @Override
    public AstNode visit(AstExpr node) {
        node.expr = accept(node.expr);
        return null;
    }

    @Override
    public AstNode visit(AstApply node) {
        return node;
    }

    @Override
    public AstNode visit(AstAssign node) {
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
}
