package io.analysis.coupling.extract;

import io.analysis.coupling.Coupling;
import org.objectweb.asm.ClassReader;

import java.util.List;

public class SingleSourceCouplingExtractor {

    private BytecodeSource bytecodeSource;

    public SingleSourceCouplingExtractor(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    public List<Coupling> couplings() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingExtractingClassVisitor classVisitor = new CouplingExtractingClassVisitor();
        reader.accept(classVisitor, 0);
        return classVisitor.couplings();
    }
}
