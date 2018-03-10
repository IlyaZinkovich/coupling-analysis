package io.coupling.core;

import java.util.Objects;

public class ClassPartDescriptor {

  private String className;
  private String methodName;
  private String methodSignature;

  public ClassPartDescriptor(String className, String methodName, String methodSignature) {
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
