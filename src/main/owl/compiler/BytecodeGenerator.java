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

import javassist.bytecode.AccessFlag;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class BytecodeGenerator {
    private BytecodeGenerator() {}

    static void run(Ast ast, File dir) throws OwlException {
        Visitor v = new Visitor();
        v.accept(ast.root);
        File classFile = new File(dir, v.clazz.getName() + ".class");
        classFile.getParentFile().mkdirs();
        try (OutputStream os = new FileOutputStream(classFile)) {
            v.clazz.write(new DataOutputStream(os));
        } catch (IOException e) {
            throw new OwlException(e);
        }
    }

    private static final class Visitor implements AstVisitor<Void> {
        private ClassFile clazz;

        @Override
        public Void visit(AstModule node) {
            clazz = new ClassFile(true, node.name, null);
            for (AstNode v : node.variables) {
                accept(v);
            }
            for (AstNode f : node.functions) {
                accept(f);
            }
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            node.accept(new FunctionVisitor(clazz, node));
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
//            if (functionNestLevel == 0) {
//                JvmGroup g = new JvmGroup();
//                g.add(new JvmVariable(false, true, node.getName(), node.getJvmDescriptor(), null));
//                if (node.getExpr() != null) {
//                    JvmBlock b = new JvmBlock();
//                    b.add(accept(node.getExpr()));
//                    b.add(new JvmPutField(clazz.name, node.getName(), node.getType().jvmType()));
//                    g.add(b);
//                }
//                return g;
//            } else {
//                return new JvmGroup(Util.listOf(
//                        accept(node.getExpr()),
//                        new JvmPutLocal(node.index)));
//            }
            throw new UnsupportedOperationException("code generator");
        }
    }

    private static final class FunctionVisitor implements AstVisitor<Void> {
        private final ClassFile clazz;
        private final AstFunction function;
        private Bytecode code;
        private int maxStack = 0;
        private int stack = 0;

        private void incStack(int n) {
            stack += n;
            if (stack > maxStack) {
                maxStack = stack;
            }
        }

        private void decStack(int n) {
            stack -= n;
        }

        FunctionVisitor(ClassFile clazz, AstFunction function) {
            this.clazz = clazz;
            this.function = function;
        }

        @Override
        public Void visit(AstFunction node) {
            if (node != function) {
                throw new UnsupportedOperationException("nested function (lambda)");
            }

            code = new Bytecode(clazz.getConstPool());
            accept(node.getBlock());

            MethodInfo method = new MethodInfo(clazz.getConstPool(), function.getName(), function.getJvmDescriptor());
            int flags = AccessFlag.STATIC;
            if (node.getName().equals("main")) {
                flags |= AccessFlag.PUBLIC;
            }
            method.setAccessFlags(flags);
            method.addAttribute(new CodeAttribute(
                    clazz.getConstPool(),
                    maxStack,
                    function.getVars().size() + function.getArgs().size(),
                    code.get(),
                    new ExceptionTable(clazz.getConstPool())));
            clazz.addMethod2(method);
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public Void visit(AstName node) {
            AstVariable v = (AstVariable) node.entity;
            if (v.storage instanceof AstVariable.Local) {
                int index = ((AstVariable.Local) v.storage).index;
                switch (v.getType().getJvmLocalType()) {
                    case AstType.kI32:
                        code.addIload(index);
                        break;
                    case AstType.kI64:
                        code.addLload(index);
                        break;
                    default:
                        throw new UnsupportedOperationException("load local type");
                }
                incStack(1);
                return null;
            }
            if (v.storage instanceof AstVariable.Field) {
                throw new UnsupportedOperationException("field storage");
            }
            if (v.storage instanceof  AstVariable.Module) {
                throw new UnsupportedOperationException("field storage");
            }
            throw new IllegalStateException("unknown storage");
        }

        @Override
        public Void visit(AstSelect node) {
            throw new UnsupportedOperationException("code generator");
        }

        @Override
        public Void visit(AstBlock node) {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }

        @Override
        public Void visit(AstApply node) {
            for (AstNode a : node.args) {
                accept(a);
            }
            AstFunction fn = (AstFunction) ((AstName) node.fn).entity;
            if (Util.isIdFirstChar(fn.getName().charAt(0))) {
                incStack(node.args.size());
                code.addInvokestatic(
                        Util.isEmpty(fn.getModuleName()) ? Runtime.NAME : clazz.getName(),
                        fn.getName(),
                        fn.getJvmDescriptor());
                decStack(node.args.size());
                if (!fn.getReturnType().equals(AstType.NONE)) {
                    incStack(1);
                }
                return null;
            }

            // Operators
            incStack(node.args.size());
            int lt = fn.getReturnType().getJvmLocalType();
            if (node.args.size() == 1) {
                switch (fn.getName()) {
                    case "+":
                        break;
                    case "-":
                        switch (lt) {
                            case AstType.kI32:
                                code.add(Opcode.INEG);
                                break;
                            case AstType.kI64:
                                code.add(Opcode.LNEG);
                                break;
                        }
                        break;
                    case "~":
                        switch (lt) {
                            case AstType.kI32:
                                code.addIconst(-1);
                                code.add(Opcode.IXOR);
                                break;
                            case AstType.kI64:
                                code.addLconst(-1);
                                code.add(Opcode.LXOR);
                                break;
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException("unary " + fn.getName());
                }
            } else {
                Util.check(node.args.size() == 2);
                binaryOperation(fn.getName(), lt);
//                case "&&": {
//                    return new JvmIf(
//                            accept(node.args.get(0)),
//                            JvmBlock.of(new JvmIf(accept(node.args.get(1)),
//                                    JvmBlock.of(JvmLiteral.TRUE),
//                                    JvmBlock.of(JvmLiteral.FALSE))),
//                            JvmBlock.of(JvmLiteral.FALSE));
//                }
            }
            incStack(1);
            return null;
        }

        private void binaryOperation(String op, int lt) {
            switch (op) {
                case "+":
                    switch (lt) {
                        case AstType.kI32:
                            code.add(Opcode.IADD);
                            break;
                        case AstType.kI64:
                            code.add(Opcode.LADD);
                            break;
                    }
                    break;
                case "-":
                    switch (lt) {
                        case AstType.kI32:
                            code.add(Opcode.ISUB);
                            break;
                        case AstType.kI64:
                            code.add(Opcode.LSUB);
                            break;
                    }
                    break;
                case "*":
                    switch (lt) {
                        case AstType.kI32:
                            code.add(Opcode.IMUL);
                            break;
                        case AstType.kI64:
                            code.add(Opcode.LMUL);
                            break;
                    }
                    break;
                case "/":
                    switch (lt) {
                        case AstType.kI32:
                            code.add(Opcode.IDIV);
                            break;
                        case AstType.kI64:
                            code.add(Opcode.LDIV);
                            break;
                    }
                    break;
                case "%":
                    switch (lt) {
                        case AstType.kI32:
                            code.add(Opcode.IREM);
                            break;
                        case AstType.kI64:
                            code.add(Opcode.LREM);
                            break;
                    }
                    break;
                case "<<":
                case ">>":
                case ">>>":
                case "&":
                case "^":
                case "|":
                    break;
                case "//":
                    //JvmApply(Runtime.NAME, "fdiv", node.getType().jvmType());
                    break;
                case "<":
                case "<=":
                case ">":
                case ">=":
                case "==":
                case "!=":
//                    AstType tl = AstType.of(node.args.get(0));
//                    AstType tr = AstType.of(node.args.get(1));
//                    if (tl.equals(AstType.STRING) && tr.equals(AstType.STRING)) {
//                        g.add(new JvmApply(Runtime.NAME, "compare", "(II)I"));
//                        g.add(new JvmLiteral(0, Jvm.I32));
//                    }
//                    g.add(new JvmOp(2, fnName.name, node.getType().jvmType()));
//                    return g;
                    break;
                default:
                    throw new IllegalStateException("invalid operator");
            }
        }

        @Override
        public Void visit(AstCoerce node) {
            accept(node.expr);
            AstType stype = AstType.of(node.expr);
            AstType dtype = node.type;
            if (stype.equals(dtype)) {
                return null;
            }
            switch (stype.getJvmLocalType()) {
                case AstType.kI32:
                    switch (dtype.getJvmLocalType()) {
                        case AstType.kI64:
                             code.add(Opcode.I2L);
                             break;
                    }
                    break;
                case AstType.kI64:
                    switch (dtype.getJvmLocalType()) {
                        case AstType.kI32:
                             code.add(Opcode.L2I);
                             break;
                    }
                    break;
            }
            return null;
        }

        @Override
        public Void visit(AstAssign node) {
            if (node.l instanceof AstName) {
                AstVariable v = (AstVariable) ((AstName) node.l).entity;
                if (v.storage instanceof AstVariable.Local) {
                    int index = ((AstVariable.Local) v.storage).index;
                    switch (v.getType().getJvmLocalType()) {
                        case AstType.kI32:
                            code.addIstore(index);
                            break;
                        case AstType.kI64:
                            code.addLstore(index);
                            break;
                        default:
                            throw new UnsupportedOperationException("load local type");
                    }
                    decStack(1);
                    return null;
                }
                if (v.storage instanceof AstVariable.Field) {
                    throw new UnsupportedOperationException("field storage");
                }
                if (v.storage instanceof  AstVariable.Module) {
                    throw new UnsupportedOperationException("field storage");
                }
                throw new IllegalStateException("unknown storage");
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
        }

        @Override
        public Void visit(AstLiteral node) {
            switch (node.getType().getJvmLocalType()) {
                case AstType.kBOOL:
                    code.addIconst((Boolean) node.object? 1: 0);
                    break;
                case AstType.kCHAR:
                    throw new UnsupportedOperationException("literal");
                case AstType.kI32:
                    code.addIconst((Integer) node.object);
                    break;
                case AstType.kI64:
                    code.addLconst((Long) node.object);
                    break;
                case AstType.kF32:
                    code.addFconst((Float) node.object);
                    break;
                case AstType.kF64:
                    code.addDconst((Double) node.object);
                    break;
                case AstType.kREF:
                    Util.check(node.getType().equals(AstType.STRING));
                    code.addLdc((String) node.object);
                    break;
                default:
                    throw new UnsupportedOperationException("literal type");
            }
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            throw new UnsupportedOperationException("code gen");
        }

        @Override
        public Void visit(AstReturn node) {
            if (node.expr != null) {
                accept(node.expr);
            }
            int d = 1;
            switch (AstType.of(node.expr).getJvmLocalType()) {
                case AstType.kNONE:
                    code.add(Opcode.RETURN);
                    d = 0;
                    break;
                case AstType.kBOOL:
                    code.add(Opcode.IRETURN);
                    break;
                case AstType.kCHAR:
                    code.add(Opcode.IRETURN);
                    break;
                case AstType.kI32:
                    code.add(Opcode.IRETURN);
                    break;
                case AstType.kI64:
                    code.add(Opcode.LRETURN);
                    break;
                case AstType.kF32:
                    code.add(Opcode.FRETURN);
                    break;
                case AstType.kF64:
                    code.add(Opcode.DRETURN);
                    break;
                case AstType.kREF:
                    code.add(Opcode.ARETURN);
                    break;
                default:
                    throw new UnsupportedOperationException("return type");
            }
            decStack(d);
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            accept(node.expr);
            if (node.discards()) {
                code.add(Opcode.POP);
                decStack(1);
            }
            return null;
        }

        @Override
        public Void visit(AstGroup node) {
            for (AstNode c : node.children) {
                accept(c);
            }
            return null;
        }
    }
}
