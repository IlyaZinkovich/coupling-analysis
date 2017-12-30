package io.analysis.coupling;

public class Coupling {

    private Dependency outboundDependency;
    private Dependency inboundDependency;

    public Coupling(Dependency outboundDependency, Dependency inboundDependency) {
        this.outboundDependency = outboundDependency;
        this.inboundDependency = inboundDependency;
    }
}
