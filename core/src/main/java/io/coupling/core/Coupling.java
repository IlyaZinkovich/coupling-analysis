package io.coupling.core;

public class Coupling {

  private ClassPartDescriptor source;
  private ClassPartDescriptor target;

  public Coupling(ClassPartDescriptor source, ClassPartDescriptor target) {
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
