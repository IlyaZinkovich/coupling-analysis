package io.coupling.domain.core;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    final Map<String, List<Coupling>> outboundCouplings = new OutboundCoupling(coupling).toMap();
    final Map<String, List<Coupling>> inboundCouplings = new InboundCoupling(coupling).toMap();
    return new AnalysedClasses(coupling).names().stream()
        .map(className -> new AnalysedClass(className, mapValue(outboundCouplings, className),
            mapValue(inboundCouplings, className))).collect(toList());
  }

  private List<Coupling> mapValue(Map<String, List<Coupling>> couplingPerClass, String className) {
    return couplingPerClass.getOrDefault(className, emptyList());
  }
}
