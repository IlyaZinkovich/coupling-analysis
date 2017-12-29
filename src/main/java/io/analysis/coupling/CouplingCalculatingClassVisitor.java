package io.analysis.coupling;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

public class CouplingCalculatingClassVisitor extends ClassVisitor {

    private String internalClassName;
    private Map<Dependency, Integer> outboundDependenciesCount = new HashMap<>();

    public CouplingCalculatingClassVisitor() {
        super(Opcodes.ASM6);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        internalClassName = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MethodVisitor(Opcodes.ASM6) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
                Dependency dependency = new Dependency(owner, name, desc);
                outboundDependenciesCount.putIfAbsent(dependency, 0);
                outboundDependenciesCount.put(dependency, outboundDependenciesCount.get(dependency) + 1);
            }
        };
    }

    public EfferentCoupling efferentCoupling() {
        return new EfferentCoupling(internalClassName, outboundDependenciesCount);
    }
}
