package io.analysis.coupling;

import java.util.Map;

public class EfferentCoupling {

    private String className;
    private Map<Dependency, Integer> outboundDependenciesCount;

    public EfferentCoupling(String className, Map<Dependency, Integer> outboundDependenciesCount) {
        this.className = className;
        this.outboundDependenciesCount = outboundDependenciesCount;
    }

    public String className() {
        return className;
    }

    public int value() {
        return outboundDependenciesCount.entrySet().stream().filter(entry -> !className.equals(entry.getKey().owner())).mapToInt(Map.Entry::getValue).sum();
    }
}
