package io.coupling.domain.core;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class AnalysedClasses {

  private final List<Coupling> coupling;

  AnalysedClasses(final List<Coupling> coupling) {
    this.coupling = unmodifiableList(coupling);
  }

  public Set<String> names() {
    return coupling.stream()
        .flatMap(couplingItem -> Stream.of(couplingItem.source(), couplingItem.target()))
        .map(ClassPartDescriptor::className)
        .collect(toSet());
  }
}
