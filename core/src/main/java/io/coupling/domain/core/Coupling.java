package io.coupling.domain.core;

public class Coupling {

  private final ClassPartDescriptor source;
  private final ClassPartDescriptor target;

  public Coupling(final ClassPartDescriptor source, final ClassPartDescriptor target) {
    this.target = target;
    this.source = source;
  }

  public ClassPartDescriptor target() {
    return target;
  }

  public ClassPartDescriptor source() {
    return source;
  }
}
