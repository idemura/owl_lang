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

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class BytecodeGenerator {
    private BytecodeGenerator() {}

    static void run(Ast ast, File dir, int optLevel) throws OwlException {
        Visitor v = new Visitor(ast, optLevel);
        v.accept(ast.root);
        File classFile = new File(dir, v.getClassName() + ".class");
        classFile.getParentFile().mkdirs();
        try (OutputStream os = new FileOutputStream(classFile)) {
            os.write(v.clazz.toByteArray());
        } catch (IOException e) {
            throw new OwlException(e);
        }
    }

    private static final class Visitor implements AstVisitor<Void> {
        private final Ast ast;
        private final int optLevel;
        private final List<AstVariable> staticInit = new ArrayList<>();
        private ClassWriter clazz;

        Visitor(Ast ast, int optLevel) {
            this.ast = ast;
            this.optLevel = optLevel;
        }

        String getClassName() {
            return ((AstModule) ast.root).name;
        }

        @Override
        public Void visit(AstModule node) {
            clazz = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            clazz.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, node.name, null, "java/lang/Object", null);
            clazz.visitSource(ast.fileName, null);
            for (AstNode v : node.variables) {
                accept(v);
            }
            for (AstNode f : node.functions) {
                accept(f);
            }
            genStaticInit();
            return null;
        }

        @Override
        public Void visit(AstFunction node) {
            int flags = Opcodes.ACC_STATIC;
            if (node.getName().equals("main")) {
                flags |= Opcodes.ACC_PUBLIC;
            }
            MethodVisitor mv = clazz.visitMethod(flags, node.getName(), node.getJvmDescriptor(), null, null);
            mv.visitCode();
            node.indexLocals();
            node.accept(new FunctionVisitor(getClassName(), mv, optLevel));
            mv.visitMaxs(0, 0);
            mv.visitEnd();
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            clazz.visitField(Opcodes.ACC_STATIC, node.getName(), node.getJvmDescriptor(), null, null).visitEnd();
            if (node.getExpr() != null) {
                staticInit.add(node);
            }
            return null;
        }

        private void genStaticInit() {
            if (staticInit.isEmpty()) {
                return;
            }
            MethodVisitor mv = clazz.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            for (AstVariable v : staticInit) {
                v.getExpr().accept(new FunctionVisitor(getClassName(), mv, optLevel));
                mv.visitFieldInsn(Opcodes.PUTSTATIC, v.getModuleName(), v.getName(), v.getJvmDescriptor());
            }
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }

    private static final class FunctionVisitor implements AstVisitor<Void> {
        private final String className;
        private final MethodVisitor mv;
        private final int optLevel;

        FunctionVisitor(String className, MethodVisitor mv, int optLevel) {
            this.className = className;
            this.mv = mv;
            this.optLevel = optLevel;
        }

        private static final int BIPUSH_MIN = Byte.MIN_VALUE;
        private static final int BIPUSH_MAX = Byte.MAX_VALUE;
        private static final int SIPUSH_MIN = Short.MIN_VALUE;
        private static final int SIPUSH_MAX = Short.MAX_VALUE;

        private void getVar(AstVariable v) {
            if (v.getStorage() instanceof AstVariable.Local) {
                int index = ((AstVariable.Local) v.getStorage()).index;
                switch (v.getType().getJvmLocalType()) {
                    case AstType.kBOOL:
                    case AstType.kI32:
                        mv.visitVarInsn(Opcodes.ILOAD, index);
                        break;
                    case AstType.kI64:
                        mv.visitVarInsn(Opcodes.LLOAD, index);
                        break;
                    case AstType.kREF:
                        mv.visitVarInsn(Opcodes.ALOAD, index);
                        break;
                    default:
                        throw new UnsupportedOperationException("load local type");
                }
                return;
            }
            if (v.getStorage() instanceof AstVariable.Field) {
                throw new UnsupportedOperationException("field storage");
            }
            if (v.getStorage() instanceof AstVariable.Module) {
                mv.visitFieldInsn(Opcodes.GETSTATIC, v.getModuleName(), v.getName(), v.getJvmDescriptor());
                return;
            }
            throw new IllegalStateException("unknown storage");
        }

        private void putVar(AstVariable v) {
            if (v.getStorage() instanceof AstVariable.Local) {
                int index = ((AstVariable.Local) v.getStorage()).index;
                switch (v.getType().getJvmLocalType()) {
                    case AstType.kBOOL:
                    case AstType.kI32:
                        mv.visitVarInsn(Opcodes.ISTORE, index);
                        break;
                    case AstType.kI64:
                        mv.visitVarInsn(Opcodes.LSTORE, index);
                        break;
                    case AstType.kREF:
                        mv.visitVarInsn(Opcodes.ASTORE, index);
                        break;
                    default:
                        throw new UnsupportedOperationException("load local type");
                }
                return;
            }
            if (v.getStorage() instanceof AstVariable.Field) {
                throw new UnsupportedOperationException("field storage");
            }
            if (v.getStorage() instanceof  AstVariable.Module) {
                mv.visitFieldInsn(Opcodes.PUTSTATIC, v.getModuleName(), v.getName(), v.getJvmDescriptor());
                return;
            }
            throw new IllegalStateException("unknown storage");
        }

        @Override
        public Void visit(AstFunction node) {
            accept(node.getBlock());
            return null;
        }

        @Override
        public Void visit(AstVariable node) {
            if (node.getExpr() != null) {
                accept(node.getExpr());
                putVar(node);
            }
            return null;
        }

        @Override
        public Void visit(AstName node) {
            getVar((AstVariable) node.entity);
            return null;
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
            AstFunction fn = (AstFunction) ((AstName) node.fn).entity;
            if (optLevel > 0 && fn.getName().equals("assert")) {
                return null;
            }

            switch (fn.getName()) {
                case "&&": {
                    accept(node.args.get(0));
                    Label conditionFalse = new Label();
                    Label end = new Label();
                    mv.visitJumpInsn(Opcodes.IFEQ, conditionFalse);
                    accept(node.args.get(1));
                    mv.visitJumpInsn(Opcodes.GOTO, end);
                    mv.visitLabel(conditionFalse);
                    mv.visitInsn(Opcodes.ICONST_0);
                    mv.visitLabel(end);
                    return null;
                }
                case "||": {
                    accept(node.args.get(0));
                    Label conditionFalse = new Label();
                    Label end = new Label();
                    mv.visitJumpInsn(Opcodes.IFEQ, conditionFalse);
                    mv.visitInsn(Opcodes.ICONST_1);
                    mv.visitJumpInsn(Opcodes.GOTO, end);
                    mv.visitLabel(conditionFalse);
                    accept(node.args.get(1));
                    mv.visitLabel(end);
                    return null;
                }
            }

            for (AstNode a : node.args) {
                accept(a);
            }
            String methodName = fn.getMethodName();
            if (Util.isIdFirstChar(fn.getName().charAt(0)) || methodName != null) {
                if (methodName == null) {
                    methodName = fn.getName();
                }
                mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                        Util.isEmpty(fn.getModuleName()) ? Runtime.NAME : className,
                        methodName,
                        fn.getJvmDescriptor(),
                        false);
                return null;
            }

            // Operators
            int lt = fn.getArgs().get(0).getType().getJvmLocalType();
            if (node.args.size() == 1) {
                genUnaryOp(fn.getName(), lt);
            } else {
                Util.check(node.args.size() == 2);
                Util.check(Objects.equals(
                        fn.getArgs().get(0).getType(),
                        fn.getArgs().get(1).getType()));
                switch (fn.getName()) {
                    case "<":
                    case "<=":
                    case ">":
                    case ">=":
                    case "==":
                    case "!=":
                        boolean lstr = AstType.of(node.args.get(0)).equals(AstType.STRING);
                        boolean rstr = AstType.of(node.args.get(1)).equals(AstType.STRING);
                        if (lstr && rstr) {
                            mv.visitMethodInsn(Opcodes.INVOKESTATIC, Runtime.NAME, "compare", "(II)I", false);
                            mv.visitInsn(Opcodes.ICONST_0);
                            genBinaryOp(fn.getName(), AstType.kI32);
                        } else {
                            genBinaryOp(fn.getName(), lt);
                        }
                        break;
                    default:
                        genBinaryOp(fn.getName(), lt);
                }
            }
            return null;
        }

        private void compare(int falseJumpOp) {
            Label conditionFalse = new Label();
            Label end = new Label();
            mv.visitJumpInsn(falseJumpOp, conditionFalse);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.GOTO, end);
            mv.visitLabel(conditionFalse);
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitLabel(end);
        }

        @Override
        public Void visit(AstCoerce node) {
            accept(node.expr);
            genCoerce(AstType.of(node.expr), node.type);
            return null;
        }

        @Override
        public Void visit(AstAssign node) {
            accept(node.r);
            if (node.l instanceof AstName) {
                putVar((AstVariable) ((AstName) node.l).entity);
                return null;
            } else {
                throw new UnsupportedOperationException("assign to not a name");
            }
        }

        @Override
        public Void visit(AstLiteral node) {
            switch (node.getType().getJvmLocalType()) {
                case AstType.kBOOL:
                    if ((Boolean) node.object) {
                        mv.visitInsn(Opcodes.ICONST_1);
                    } else {
                        mv.visitInsn(Opcodes.ICONST_0);
                    }
                    break;
                case AstType.kCHAR:
                    throw new UnsupportedOperationException("literal");
                case AstType.kI32:
                    int v = (Integer) node.object;
                    if (-1 <= v && v <= 5) {
                        mv.visitInsn(Opcodes.ICONST_0 + v);
                    } else if (BIPUSH_MIN <= v && v <= BIPUSH_MAX) {
                        mv.visitIntInsn(Opcodes.BIPUSH, v);
                    } else if (SIPUSH_MIN <= v && v <= SIPUSH_MAX) {
                        mv.visitIntInsn(Opcodes.SIPUSH, v);
                    } else {
                        mv.visitLdcInsn(v);
                    }
                    break;
                case AstType.kI64:
                    long l = (Long) node.object;
                    if (l == 0) {
                        mv.visitInsn(Opcodes.LCONST_0);
                    } else if (l == 1) {
                        mv.visitInsn(Opcodes.LCONST_1);
                    } else {
                        mv.visitLdcInsn(l);
                    }
                    break;
                case AstType.kF32:
                case AstType.kF64:
                    mv.visitLdcInsn(node.object);
                    break;
                case AstType.kREF:
                    Util.check(node.getType().equals(AstType.STRING));
                    mv.visitLdcInsn(node.object);
                    break;
                default:
                    throw new UnsupportedOperationException("literal type");
            }
            return null;
        }

        @Override
        public Void visit(AstIf node) {
            Label end = new Label();
            for (AstIf.Branch b : node.branches) {
                if (b.condition != null) {
                    accept(b.condition);
                    Label conditionFalse = new Label();
                    mv.visitJumpInsn(Opcodes.IFEQ, conditionFalse);
                    accept(b.block);
                    mv.visitJumpInsn(Opcodes.GOTO, end);
                    mv.visitLabel(conditionFalse);
                } else {
                    accept(b.block);
                }
            }
            mv.visitLabel(end);
            return null;
        }

        @Override
        public Void visit(AstFor node) {
            Label begin = new Label();
            Label end = new Label();
            mv.visitLabel(begin);
            accept(node.condition);
            mv.visitJumpInsn(Opcodes.IFEQ, end);
            accept(node.block);
            mv.visitJumpInsn(Opcodes.GOTO, begin);
            mv.visitLabel(end);
            return null;
        }

        @Override
        public Void visit(AstReturn node) {
            if (node.expr != null) {
                accept(node.expr);
            }
            switch (AstType.of(node.expr).getJvmLocalType()) {
                case AstType.kNONE:
                    mv.visitInsn(Opcodes.RETURN);
                    break;
                case AstType.kBOOL:
                    mv.visitInsn(Opcodes.IRETURN);
                    break;
                case AstType.kCHAR:
                    mv.visitInsn(Opcodes.IRETURN);
                    break;
                case AstType.kI32:
                    mv.visitInsn(Opcodes.IRETURN);
                    break;
                case AstType.kI64:
                    mv.visitInsn(Opcodes.LRETURN);
                    break;
                case AstType.kF32:
                    mv.visitInsn(Opcodes.FRETURN);
                    break;
                case AstType.kF64:
                    mv.visitInsn(Opcodes.DRETURN);
                    break;
                case AstType.kREF:
                    mv.visitInsn(Opcodes.ARETURN);
                    break;
                default:
                    throw new UnsupportedOperationException("return type");
            }
            return null;
        }

        @Override
        public Void visit(AstExpr node) {
            accept(node.expr);
            if (node.discards()) {
                mv.visitInsn(Opcodes.POP);
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

        private void genUnaryOp(String op, int lt) {
            switch (op) {
                case "+":
                    switch (lt) {
                        case AstType.kI32:
                        case AstType.kI64:
                            return;
                    }
                    break;
                case "-":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.INEG);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LNEG);
                            return;
                    }
                    break;
                case "~":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.ICONST_M1);
                            mv.visitInsn(Opcodes.IXOR);
                            return;
                        case AstType.kI64:
                            mv.visitLdcInsn(-1L);
                            mv.visitInsn(Opcodes.LXOR);
                            return;
                    }
                    break;
                case "!":
                    switch (lt) {
                        case AstType.kBOOL:
                            // Bool are 0 or 1 in Owl
                            mv.visitInsn(Opcodes.ICONST_1);
                            mv.visitInsn(Opcodes.IXOR);
                            return;
                    }
                    break;
            }
            throw new UnsupportedOperationException("invalid unary " + op);
        }

        private void genBinaryOp(String op, int lt) {
            switch (op) {
                case "+":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IADD);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LADD);
                            return;
                    }
                    break;
                case "-":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.ISUB);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LSUB);
                            return;
                    }
                    break;
                case "*":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IMUL);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LMUL);
                            return;
                    }
                    break;
                case "/":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IDIV);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LDIV);
                            return;
                    }
                    break;
                case "%":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IREM);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LREM);
                            return;
                    }
                    break;
                case "<<":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.ISHL);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LSHL);
                            return;
                    }
                    break;
                case ">>":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.ISHR);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LSHR);
                            return;
                    }
                    break;
                case ">>>":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IUSHR);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LUSHR);
                            return;
                    }
                    break;
                case "&":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IAND);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LAND);
                            return;
                    }
                    break;
                case "^":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IXOR);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LXOR);
                            return;
                    }
                    break;
                case "|":
                    switch (lt) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.IOR);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.LOR);
                            return;
                    }
                    break;
                case "<":
                    switch (lt) {
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPGE);
                            return;
                    }
                    break;
                case "<=":
                    switch (lt) {
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPGT);
                            return;
                    }
                    break;
                case ">":
                    switch (lt) {
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPLE);
                            return;
                    }
                    break;
                case ">=":
                    switch (lt) {
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPLT);
                            return;
                    }
                    break;
                case "==":
                    switch (lt) {
                        case AstType.kBOOL:
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPNE);
                            return;
                    }
                    break;
                case "!=":
                    switch (lt) {
                        case AstType.kBOOL:
                        case AstType.kI32:
                            compare(Opcodes.IF_ICMPEQ);
                            return;
                    }
                    break;
                case "^^":
                    switch (lt) {
                        case AstType.kBOOL:
                            mv.visitInsn(Opcodes.IXOR);
                            compare(Opcodes.IFEQ);
                            return;
                    }
                    break;
            }
            throw new IllegalStateException("invalid binary " + op);
        }

        private void genCoerce(AstType stype, AstType dtype) {
            if (stype.equals(dtype)) {
                return;
            }
            switch (stype.getJvmLocalType()) {
                case AstType.kBOOL:
                    switch (dtype.getJvmLocalType()) {
                        case AstType.kI32:
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.I2L);
                            return;
                    }
                    break;
                case AstType.kI32:
                    switch (dtype.getJvmLocalType()) {
                        case AstType.kBOOL:
                            compare(Opcodes.IFEQ);
                            return;
                        case AstType.kI64:
                            mv.visitInsn(Opcodes.I2L);
                            return;
                    }
                    break;
                case AstType.kI64:
                    switch (dtype.getJvmLocalType()) {
                        case AstType.kI32:
                            mv.visitInsn(Opcodes.L2I);
                            return;
                    }
                    break;
            }
            throw new IllegalStateException("invalid coerce " + stype + " to " + dtype);
        }
    }
}
