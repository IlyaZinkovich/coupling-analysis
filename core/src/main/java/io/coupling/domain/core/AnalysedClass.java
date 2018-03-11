package io.coupling.domain.core;

import java.util.List;

public class AnalysedClass {

  private final String className;
  private final List<Coupling> outboundCoupling;
  private final List<Coupling> inboundCoupling;

  AnalysedClass(String className, List<Coupling> outboundCoupling,
      List<Coupling> inboundCoupling) {
    this.className = className;
    this.outboundCoupling = outboundCoupling;
    this.inboundCoupling = inboundCoupling;
  }

  public int efferentCoupling() {
    return outboundCoupling.size();
  }

  public int afferentCoupling() {
    return (int) inboundCoupling.stream().map(Coupling::source).map(ClassPartDescriptor::className)
        .distinct().count();
  }

  public double instability() {
    final int efferent = efferentCoupling();
    final int afferent = afferentCoupling();
    return (double) efferent / (efferent + afferent);
  }

  public String className() {
    return className;
  }
}
