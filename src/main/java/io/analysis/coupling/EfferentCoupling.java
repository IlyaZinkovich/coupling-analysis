package io.analysis.coupling;

import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

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
        return outboundDependenciesCount.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
    }

    public Map<String, Integer> statistics() {
        return outboundDependenciesCount.entrySet().stream()
                .collect(groupingBy(entry -> entry.getKey().className(), summingInt(Map.Entry::getValue)));
    }
}
