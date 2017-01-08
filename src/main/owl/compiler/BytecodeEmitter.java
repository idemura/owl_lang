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

import javassist.bytecode.AccessFlag;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;

final class BytecodeEmitter {
    private BytecodeEmitter() {}

    static void run(Jvm jvm, File dir) throws OwlException {
        Visitor v = new Visitor();
        v.accept(jvm.root);
        for (ClassFile clazz : v.classFiles) {
            File classFile = new File(dir, clazz.getName().replace('.', File.separatorChar) + ".class");
            classFile.getParentFile().mkdirs();
            try (OutputStream os = new FileOutputStream(classFile)) {
                clazz.write(new DataOutputStream(os));
            } catch (IOException e) {
                throw new OwlException(e);
            }
        }
    }

    private static int getInstrType(String type) {
        if (type.charAt(0) == 'L') {
            return InstrType.kR;
        }
        switch (type) {
            case Jvm.BOOL:
                return InstrType.kZ;
            case Jvm.CHAR:
                return InstrType.kC;
            case Jvm.I8:
                return InstrType.kB;
            case Jvm.I16:
                return InstrType.kS;
            case Jvm.I32:
                return InstrType.kI;
            case Jvm.I64:
                return InstrType.kL;
            case Jvm.F32:
                return InstrType.kF;
            case Jvm.F64:
                return InstrType.kD;
            case Jvm.NONE:
                return InstrType.kV;
            default:
                throw new IllegalStateException("map to instruction type");
        }
    }

    private static final class Visitor implements JvmVisitor<Void> {
        final List<ClassFile> classFiles = new ArrayList<>();
        private ClassFile clazz;
        private Bytecode code;
        private JvmFunction function;
        private int maxStack = 0;
        private int stack = 0;

        void incStack(int n) {
            stack += n;
            if (stack > maxStack) {
                maxStack = stack;
            }
        }

        @Override
        public Void visit(JvmClass node) {
            checkState(clazz == null);
            clazz = new ClassFile(false, node.getQualifiedName(), null);
            clazz.setMajorVersion(ClassFile.JAVA_8);
            clazz.setMinorVersion(0);
            clazz.setAccessFlags(AccessFlag.PUBLIC | AccessFlag.FINAL);
            for (JvmNode v : node.getVariables()) {
                accept(v);
            }
            for (JvmNode f : node.getFunctions()) {
                accept(f);
            }
            classFiles.add(clazz);
            return null;
        }

        @Override
        public Void visit(JvmFunction node) {
            function = node;
            code = new Bytecode(clazz.getConstPool());
            stack = maxStack = 0;
            accept(node.block);
            MethodInfo mi = new MethodInfo(
                    clazz.getConstPool(),
                    node.name,
                    node.desc);
            mi.setAccessFlags(node.flags);
            mi.addAttribute(new CodeAttribute(
                    clazz.getConstPool(),
                    maxStack,
                    node.vars.size(),
                    code.get(),
                    new ExceptionTable(clazz.getConstPool())));
            clazz.addMethod2(mi);
            return null;
        }

        @Override
        public Void visit(JvmVariable node) {
            // TODO: Init expression generation
            FieldInfo fi = new FieldInfo(
                    clazz.getConstPool(),
                    node.name,
                    node.desc);
            fi.setAccessFlags(node.flags);
            clazz.addField2(fi);
            return null;
        }

        @Override
        public Void visit(JvmLiteral node) {
            if (node.type.equals(Jvm.STRING)) {
                code.addLdc((String) node.object);
            } else {
                switch (node.type) {
                    case Jvm.BOOL:
                        code.addIconst((Boolean) node.object ? 1 : 0);
                        break;
                    case Jvm.CHAR:
                        code.addIconst((Integer) node.object);
                        break;
                    case Jvm.I32:
                        code.addIconst((Integer) node.object);
                        break;
                    case Jvm.I64:
                        code.addLconst((Long) node.object);
                        break;
                    case Jvm.F32:
                        code.addFconst((Float) node.object);
                        break;
                    case Jvm.F64:
                        code.addDconst((Double) node.object);
                        break;
                    default:
                        throw new IllegalArgumentException("invalid literal type " + node.type);
                }
            }
            incStack(1);
            return null;
        }

        @Override
        public Void visit(JvmGetLocal node) {
            switch (function.vars.get(node.index).itype) {
                case InstrType.kI:
                    code.addIload(node.index);
                    break;
                case InstrType.kL:
                    code.addIload(node.index);
                    break;
                case InstrType.kF:
                    code.addFload(node.index);
                    break;
                case InstrType.kD:
                    code.addDload(node.index);
                    break;
                case InstrType.kR:
                    code.addAload(node.index);
                    break;
            }
            incStack(1);
            return null;
        }

        @Override
        public Void visit(JvmGetField node) {
            code.addGetstatic(node.className, node.name, node.type);
            incStack(1);
            return null;
        }

        @Override
        public Void visit(JvmApply node) {
            // Reverse order to the actual argument order
            int s = stack;
            incStack(node.getArgsNum());
            code.addInvokestatic(node.object, node.method, node.desc);
            stack = s;
            if (!node.isVoid()) {
                stack--;
            }
            return null;
        }

        @Override
        public Void visit(JvmOp node) {
            stack -= node.arity;
            stack++;
            boolean found = true;
            if (node.arity == 1) {

            } else {
                switch (node.op) {
                    case "+":
                        switch (node.type.charAt(0)) {
                            case 'I':
                                code.add(Opcode.IADD);
                                break;
                            case 'J':
                                code.add(Opcode.LADD);
                                break;
                            case 'F':
                                code.add(Opcode.FADD);
                                break;
                            case 'D':
                                code.add(Opcode.DADD);
                                break;
                            default:
                                found = false;
                        }
                        break;
                    case "-":
                        switch (node.type.charAt(0)) {
                            case 'I':
                                code.add(Opcode.ISUB);
                                break;
                            case 'J':
                                code.add(Opcode.LSUB);
                                break;
                            case 'F':
                                code.add(Opcode.FSUB);
                                break;
                            case 'D':
                                code.add(Opcode.DSUB);
                                break;
                            default:
                                found = false;
                        }
                        break;
                    case "*":
                        switch (node.type.charAt(0)) {
                            case 'I':
                                code.add(Opcode.IMUL);
                                break;
                            case 'J':
                                code.add(Opcode.LMUL);
                                break;
                            case 'F':
                                code.add(Opcode.FMUL);
                                break;
                            case 'D':
                                code.add(Opcode.DMUL);
                                break;
                            default:
                                found = false;
                        }
                        break;
                    case "/":
                        switch (node.type.charAt(0)) {
                            case 'I':
                                code.add(Opcode.IDIV);
                                break;
                            case 'J':
                                code.add(Opcode.LDIV);
                                break;
                            case 'F':
                                code.add(Opcode.FDIV);
                                break;
                            case 'D':
                                code.add(Opcode.DDIV);
                                break;
                            default:
                                found = false;
                        }
                        break;
                    default:
                        found = false;
                }
            }
            if (!found) {
                throw new UnsupportedOperationException("unsupported op " + node.op);
            }
            return null;
        }

        @Override
        public Void visit(JvmPutLocal node) {
            switch (function.vars.get(node.index).itype) {
                case InstrType.kI:
                    code.addIstore(node.index);
                    break;
                case InstrType.kL:
                    code.addLstore(node.index);
                    break;
                case InstrType.kF:
                    code.addFstore(node.index);
                    break;
                case InstrType.kD:
                    code.addDstore(node.index);
                    break;
                case InstrType.kR:
                    code.addAstore(node.index);
                    break;
            }
            stack--;
            return null;
        }

        @Override
        public Void visit(JvmPutField node) {
            code.addPutstatic(node.className, node.name, node.type);
            stack--;
            return null;
        }

        @Override
        public Void visit(JvmBlock node) {
            for (JvmNode s : node.getInstructions()) {
                accept(s);
            }
            return null;
        }

        @Override
        public Void visit(JvmReturn node) {
            switch (node.itype) {
                case InstrType.kV:
                    code.add(Opcode.RETURN);
                    break;
                case InstrType.kI:
                    code.add(Opcode.IRETURN);
                    break;
                case InstrType.kL:
                    code.add(Opcode.LRETURN);
                    break;
                case InstrType.kF:
                    code.add(Opcode.FRETURN);
                    break;
                case InstrType.kD:
                    code.add(Opcode.DRETURN);
                    break;
                case InstrType.kR:
                    code.add(Opcode.ARETURN);
                    break;
            }
            return null;
        }

        @Override
        public Void visit(JvmPop node) {
            code.add(Opcode.POP);
            return null;
        }

        @Override
        public Void visit(JvmCoerce node) {
            switch (node.kind) {
                case JvmCoerce.I2L:
                    code.add(Opcode.I2L);
                    break;
                case JvmCoerce.L2I:
                    code.add(Opcode.L2I);
                    break;
                case JvmCoerce.REF:
                    throw new UnsupportedOperationException("ref cast");
            }
            return null;
        }

        @Override
        public Void visit(JvmIf node) {
            throw new UnsupportedOperationException("if");
//            accept(node.condition);
//            accept(node.thenBlock);
//            if (node.elseBlock != null) {
//                accept(node.elseBlock);
//            }
//            return null;
        }

        @Override
        public Void visit(JvmGroup node) {
            for (JvmNode c : node.children) {
                accept(c);
            }
            return null;
        }
    }
}
