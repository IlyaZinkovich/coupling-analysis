package io.analysis.coupling;

import org.objectweb.asm.ClassReader;

import java.util.HashMap;

public class CouplingCalculator {

    private BytecodeSource bytecodeSource;

    public CouplingCalculator(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    public EfferentCoupling efferentCoupling() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        return new EfferentCoupling("", new HashMap<>());
    }
}
