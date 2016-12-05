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
    static void run(Ast ast, EntityMap entityMap, ErrorListener errorListener) throws OwlException {
        new Visitor(entityMap, errorListener).accept(ast.root);
    }

    private static final class Visitor implements AstVisitor {
        private final ErrorListener errorListener;
        private final EntityMap entityMap;
        private EntityMap locals;
        private String moduleName;

        private Visitor(EntityMap entityMap, ErrorListener errorListener) {
            this.errorListener = new CountErrorListener(errorListener);
            this.entityMap = entityMap;
        }

        private void error(AstNode n, String msg) {
            errorListener.error(n.line, n.charPositionInLine, msg);
        }

        @Override
        public Void visit(AstName n) {
            // Should not resolve named function here, but in apply.
            if (!locals.isFunction(n.name)) {
                try {
                    n.entity = locals.resolveVariable(n.name);
                } catch (OwlException e) {
                    error(n, e.getMessage());
                }
            }
            return null;
        }

        @Override
        public Void visit(AstType n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMember n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstModule n) {
            moduleName = n.name;
            for (AstNode f : n.members) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction n) {
            locals = entityMap.clone();
            for (AstArgument a : n.args) {
                locals.replace(a.getEntity(moduleName));
            }
            accept(n.block);
            return null;
        }

        @Override
        public Void visit(AstArgument n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstVariable n) {
            accept(n.expr);
            return null;
        }

        @Override
        public Void visit(AstBlock n) {
            for (AstNode s : n.statements) {
                accept(s);
            }
            return null;
        }

        @Override
        public Void visit(AstApply n) {
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
                    return null;
                }
                AstType fnType = fn.entity.getType();
                n.type = fnType.args.get(fnType.args.size() - 1);
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
            return null;
        }

        @Override
        public Void visit(AstConstant n) {
            accept(n.expr);
            return null;
        }

        @Override
        public Void visit(AstLiteral n) {
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
                    throw new IllegalStateException("unknown literal format " + n.format);
            }
            return null;
        }

        @Override
        public Void visit(AstIf n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMatch n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstReturn n) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstExpr n) {
            accept(n.expr);
            return null;
        }
    }
}
