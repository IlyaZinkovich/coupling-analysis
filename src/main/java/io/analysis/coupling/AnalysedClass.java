package io.analysis.coupling;

import java.util.List;

public class AnalysedClass {

    private final String className;
    private final List<Coupling> outboundCouplings;
    private final List<Coupling> inboundCouplings;

    public AnalysedClass(String className, List<Coupling> outboundCouplings, List<Coupling> inboundCouplings) {
        this.className = className;
        this.outboundCouplings = outboundCouplings;
        this.inboundCouplings = inboundCouplings;
    }

    public int efferentCoupling() {
        return outboundCouplings.size();
    }

    public int afferentCoupling() {
        return (int) inboundCouplings.stream().map(Coupling::from).map(Dependency::className).distinct().count();
    }

    public String className() {
        return className;
    }
}
