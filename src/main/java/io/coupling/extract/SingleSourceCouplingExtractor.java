package io.coupling.extract;

import io.coupling.CouplingStatistic;
import org.objectweb.asm.ClassReader;

public class SingleSourceCouplingExtractor implements CouplingExtractor {

    private BytecodeSource bytecodeSource;

    public SingleSourceCouplingExtractor(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    @Override
    public CouplingStatistic couplingStatistic() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingExtractingClassVisitor classVisitor = new CouplingExtractingClassVisitor();
        reader.accept(classVisitor, 0);
        return new CouplingStatistic(classVisitor.coupling());
    }
}
