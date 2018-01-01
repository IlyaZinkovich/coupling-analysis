package io.analysis.coupling;

import org.objectweb.asm.ClassReader;

import java.util.List;

public class CouplingCalculator {

    private BytecodeSource bytecodeSource;

    public CouplingCalculator(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    public List<Coupling> couplings() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        return classVisitor.couplings();
    }
}
