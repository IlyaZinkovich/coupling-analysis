package io.analysis.coupling.extract;

import io.analysis.coupling.Coupling;
import org.objectweb.asm.ClassReader;

import java.util.List;

public class CouplingExtractor {

    private BytecodeSource bytecodeSource;

    public CouplingExtractor(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    public List<Coupling> couplings() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        return classVisitor.couplings();
    }
}
