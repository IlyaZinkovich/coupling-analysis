package io.coupling.domain.core;

import java.util.Objects;

public class ClassPartDescriptor {

  private final String className;
  private final String methodName;
  private final String methodSignature;

  public ClassPartDescriptor(final String className, final String methodName,
      final String methodSignature) {
    this.className = className;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
  }

  public String className() {
    return className;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClassPartDescriptor that = (ClassPartDescriptor) o;
    return Objects.equals(className, that.className) &&
        Objects.equals(methodName, that.methodName) &&
        Objects.equals(methodSignature, that.methodSignature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(className, methodName, methodSignature);
  }
}
