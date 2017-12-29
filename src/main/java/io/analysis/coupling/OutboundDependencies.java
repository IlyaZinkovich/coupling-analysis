package io.analysis.coupling;

import org.objectweb.asm.ClassReader;

public class OutboundDependencies {

    private InstrumentedClass instrumentedClass;

    public OutboundDependencies(InstrumentedClass instrumentedClass) {
        this.instrumentedClass = instrumentedClass;
    }

    public EfferentCoupling efferentCoupling() {
        ClassReader reader = new ClassReader(instrumentedClass.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        return classVisitor.efferentCoupling();
    }
}
