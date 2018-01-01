package io.analysis.coupling.extract;

import io.analysis.coupling.ClassPartDescriptor;
import io.analysis.coupling.Coupling;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CouplingExtractingMethodVisitor extends MethodVisitor {

    private String callingClassName;
    private String callingMethodName;
    private String callingMethodDescriptor;
    private List<Coupling> couplings = new ArrayList<>();

    public CouplingExtractingMethodVisitor(String callingClassName, String callingMethodName, String callingMethodDescriptor) {
        super(Opcodes.ASM6);
        this.callingClassName = callingClassName;
        this.callingMethodName = callingMethodName;
        this.callingMethodDescriptor = callingMethodDescriptor;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
        final ClassPartDescriptor from = new ClassPartDescriptor(callingClassName, callingMethodName, callingMethodDescriptor);
        final ClassPartDescriptor to = new ClassPartDescriptor(owner, name, desc);
        couplings.add(new Coupling(from, to));
    }

    public List<Coupling> couplings() {
        return unmodifiableList(couplings);
    }
}
