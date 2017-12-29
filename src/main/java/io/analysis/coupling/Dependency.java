package io.analysis.coupling;

import java.util.Objects;

public class Dependency {

    private String className;
    private String methodName;
    private String methodSignature;

    public Dependency(String className, String methodName, String methodSignature) {
        this.className = className;
        this.methodName = methodName;
        this.methodSignature = methodSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dependency that = (Dependency) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(methodSignature, that.methodSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, methodName, methodSignature);
    }
}
