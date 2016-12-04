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
class TypeCheckerAndEntityResolver {
    static void analyze(Ast ast, EntityMap entityMap, ErrorListener errorListener) throws OwlException {
        new TypeCheckerAndEntityResolver(ast, entityMap, errorListener).run();
    }

    private final Ast ast;
    private CountErrorListener errorListener;
    private EntityMap entityMap;

    private TypeCheckerAndEntityResolver(Ast ast, EntityMap entityMap, ErrorListener errorListener) {
        this.ast = ast;
        this.entityMap = entityMap;
        this.errorListener = new CountErrorListener(errorListener);
    }

    private void error(AstNode n, String msg) {
        errorListener.error(n.line, n.charPositionInLine, msg);
    }

    private void run() throws OwlException {
        ast.accept(new AnalyzerVisitor());
    }

    private final class AnalyzerVisitor implements AstVisitor {
        EntityMap locals = null;

        @Override
        public void visit(AstName n) {
            // Should not resolve named function here, but in apply.
            if (!locals.isFunction(n.name)) {
                try {
                    n.entity = locals.resolveVariable(n.name);
                } catch (OwlException e) {
                    error(n, e.getMessage());
                }
            }
        }

        @Override
        public void visit(AstType n) {
            throw new UnsupportedOperationException("type checker");
//            for (AstType t : n.args) {
//                accept(t);
//            }
        }

        @Override
        public void visit(AstMember n) {
            throw new UnsupportedOperationException("type checker");
//            accept(n.left);
//            accept(n.name);
        }

        @Override
        public void visit(AstModule n) {
            for (AstNode f : n.members) {
                accept(f);
            }
        }

        @Override
        public void visit(AstFunction n) {
            locals = entityMap.clone();
            for (AstArgument a : n.args) {
                locals.replace(a.getEntity(ast.<AstModule>getRootAs().name));
            }
            accept(n.block);
        }

        @Override
        public void visit(AstArgument n) {
            throw new UnsupportedOperationException("type checker");
//            accept(n.returnType);
        }

        @Override
        public void visit(AstVariable n) {
            accept(n.expr);
        }

        @Override
        public void visit(AstBlock n) {
            for (AstNode s : n.statements) {
                accept(s);
            }
        }

        @Override
        public void visit(AstApply n) {
            for (AstNode e : n.args) {
                accept(e);
            }
            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (n.args.get(0) instanceof AstName) {
                AstName fn = (AstName) n.args.get(0);
                try {
                    fn.entity = entityMap.resolveFunction(fn.name, n.getArgTypes());
                } catch (OwlException e) {
                    error(n, e.getMessage());
                    return;
                }
                AstType fnType = fn.entity.getType();
                n.type = fnType.args.get(fnType.args.size() - 1);
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
        }

        @Override
        public void visit(AstConstant n) {
            accept(n.expr);
        }

        @Override
        public void visit(AstLiteral n) {
            switch (n.format) {
                case DEC:
                case HEX:
                case OCT:
                    n.type = AstType.I32;
                    break;
                case STRING:
                    n.type = AstType.String;
                    break;
                default:
                    throw new IllegalStateException("literal format " + n.format);
            }
        }

        @Override
        public void visit(AstIf n) {
            throw new UnsupportedOperationException("type checker");
//            for (int i = 0; i < n.condition.size(); i++) {
//                accept(n.condition.get(i));
//                accept(n.block.get(i));
//            }
//            if (n.block.size() > n.condition.size()) {
//                accept(n.block.get(n.block.size() - 1));
//            }
        }

        @Override
        public void visit(AstMatch n) {
            throw new UnsupportedOperationException("type checker");
//            int blockIndex = 0;
//            for (AstMatch.Label l : n.label) {
//                if (blockIndex != l.block) {
//                    accept(n.block.get(blockIndex));
//                    blockIndex = l.block;
//                }
//            }
//            accept(n.block.get(blockIndex));
//            accept(n.elseBlock);
        }

        @Override
        public void visit(AstReturn n) {
            throw new UnsupportedOperationException("type checker");
//                accept(n.expr);
        }

        @Override
        public void visit(AstExpr n) {
            accept(n.expr);
        }
    }
}
