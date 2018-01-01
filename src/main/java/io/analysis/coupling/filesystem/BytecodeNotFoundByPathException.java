package io.analysis.coupling.filesystem;

public class BytecodeNotFoundByPathException extends RuntimeException {

    public BytecodeNotFoundByPathException(String path, Throwable cause) {
        super(String.format("Provided path: %s", path), cause);
    }
}
