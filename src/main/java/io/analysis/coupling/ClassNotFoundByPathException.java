package io.analysis.coupling;

public class ClassNotFoundByPathException extends RuntimeException {

    public ClassNotFoundByPathException(String path, Throwable cause) {
        super(String.format("Provided path: %s", path), cause);
    }
}
