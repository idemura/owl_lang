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
final class TypeCheckerAndEntityResolver {
    private TypeCheckerAndEntityResolver() {}

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

        private void error(AstNode node, String msg) {
            errorListener.error(node.line, node.charPositionInLine, msg);
        }

        @Override
        public Void visit(AstName node) {
            // Should not resolve named function here, but in apply
            if (!locals.isFunction(node.name)) {
                try {
                    node.entity = locals.resolveVariable(node.name);
                } catch (OwlException e) {
                    error(node, e.getMessage());
                }
            }
            return null;
        }

        @Override
        public Void visit(AstType node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMember node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstModule node) {
            moduleName = node.name;
            for (AstNode f : node.members) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            locals = entityMap.clone();
            for (AstArgument a : node.args) {
                locals.replace(a.getEntity(moduleName));
            }
            accept(node.block);
            return null;
        }

        @Override
        public Void visit(AstArgument node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstVariable node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstBlock node) {
            for (AstNode s : node.statements) {
                accept(s);
            }
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            for (AstNode e : node.args) {
                accept(e);
            }
            // Now we know types of arguments and (in case of lambda) function. Resolve function overload.
            if (node.args.get(0) instanceof AstName) {
                AstName fn = (AstName) node.args.get(0);
                try {
                    fn.entity = entityMap.resolveFunction(fn.name, node.getArgTypes());
                } catch (OwlException e) {
                    error(node, e.getMessage());
                    return null;
                }
                AstType fnType = fn.entity.getType();
                node.type = fnType.args.get(fnType.args.size() - 1);
            } else {
                throw new UnsupportedOperationException("apply function expr");
            }
            return null;
        }

        @Override
        public Void visit(AstConstant node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstLiteral node) {
            switch (node.format) {
                case DEC:
                case HEX:
                case OCT:
                    node.type = AstType.I32;
                    break;
                case STRING:
                    node.type = AstType.STRING;
                    break;
                default:
                    throw new IllegalStateException("unknown literal format " + node.format);
            }
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstMatch node) {
            throw new UnsupportedOperationException("type checker");
        }

        @Override
        public Void visit(AstReturn node) {
            accept(node.expr);
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            accept(node.expr);
            return null;
        }
    }
}
