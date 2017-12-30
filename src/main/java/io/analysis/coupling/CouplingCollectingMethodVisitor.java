package io.analysis.coupling;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CouplingCollectingMethodVisitor extends MethodVisitor {

    private String callingClassName;
    private String callingMethodName;
    private String callingMethodDescriptor;
    private List<Coupling> couplings = new ArrayList<>();

    public CouplingCollectingMethodVisitor(String callingClassName, String callingMethodName, String callingMethodDescriptor) {
        super(Opcodes.ASM6);
        this.callingClassName = callingClassName;
        this.callingMethodName = callingMethodName;
        this.callingMethodDescriptor = callingMethodDescriptor;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        couplings.add(new Coupling(new Dependency(owner, name, desc),
                new Dependency(callingClassName, callingMethodName, callingMethodDescriptor)));
    }

    public List<Coupling> couplings() {
        return unmodifiableList(couplings);
    }
}