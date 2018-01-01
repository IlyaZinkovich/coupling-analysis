package io.analysis.coupling;

public class Coupling {

    private ClassPartDescriptor source;
    private ClassPartDescriptor target;

    public Coupling(ClassPartDescriptor source, ClassPartDescriptor target) {
        this.target = target;
        this.source = source;
    }

    public ClassPartDescriptor to() {
        return target;
    }

    public ClassPartDescriptor from() {
        return source;
    }
}
