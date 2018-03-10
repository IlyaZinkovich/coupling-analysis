package io.coupling.domain.core;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CouplingStatistic {

  private final List<Coupling> coupling;

  public CouplingStatistic(final List<Coupling> coupling) {
    this.coupling = unmodifiableList(coupling);
  }

  public CouplingStatistic merge(CouplingStatistic toMerge) {
    final int capacity = coupling.size() + toMerge.coupling.size();
    final List<Coupling> mergedCoupling = new ArrayList<>(capacity);
    mergedCoupling.addAll(coupling);
    mergedCoupling.addAll(toMerge.coupling);
    return new CouplingStatistic(mergedCoupling);
  }

  public List<AnalysedClass> analyse() {
    final Map<String, List<Coupling>> outboundCouplings = new OutboundCoupling(coupling).toMap();
    final Map<String, List<Coupling>> inboundCouplings = new InboundCoupling(coupling).toMap();
    return new AnalysedClasses(coupling).names().stream()
        .map(className -> new AnalysedClass(
                className,
                outboundCouplings.getOrDefault(className, emptyList()),
                inboundCouplings.getOrDefault(className, emptyList())
            )
        ).collect(toList());
  }

}
