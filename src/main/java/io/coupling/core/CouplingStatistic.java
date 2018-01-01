package io.coupling.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class CouplingStatistic {

    private List<Coupling> coupling;

    public CouplingStatistic(List<Coupling> coupling) {
        this.coupling = coupling;
    }

    public CouplingStatistic merge(CouplingStatistic toMerge) {
        List<Coupling> mergedCoupling = new ArrayList<>(coupling.size() + toMerge.coupling.size());
        mergedCoupling.addAll(coupling);
        mergedCoupling.addAll(toMerge.coupling);
        return new CouplingStatistic(mergedCoupling);
    }

    public List<AnalysedClass> analyse() {
        final Set<String> analysedClassesNames = coupling.stream()
                .flatMap(couplingItem -> Stream.of(couplingItem.source(), couplingItem.target()))
                .map(ClassPartDescriptor::className)
                .collect(toSet());
        final Map<String, List<Coupling>> outboundCouplings = coupling.stream()
                .collect(groupingBy(couplingItem -> couplingItem.source().className()));
        final Map<String, List<Coupling>> inboundCouplings = coupling.stream()
                .collect(groupingBy(couplingItem -> couplingItem.target().className()));
        return analysedClassesNames.stream().map(className -> new AnalysedClass(
                        className,
                        outboundCouplings.getOrDefault(className, emptyList()),
                        inboundCouplings.getOrDefault(className, emptyList())
                )
        ).collect(toList());
    }
}
