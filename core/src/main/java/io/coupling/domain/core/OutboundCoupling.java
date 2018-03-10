package io.coupling.domain.core;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

class OutboundCoupling {

  private final List<Coupling> coupling;

  OutboundCoupling(final List<Coupling> coupling) {
    this.coupling = unmodifiableList(coupling);
  }

  Map<String, List<Coupling>> toMap() {
    return coupling.stream().collect(groupingBy(couplingItem -> couplingItem.source().className()));
  }
}
