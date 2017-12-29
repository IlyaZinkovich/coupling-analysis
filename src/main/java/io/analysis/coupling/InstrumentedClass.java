package io.analysis.coupling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class InstrumentedClass {

    private String path;

    private byte[] cachedBytecode;

    public InstrumentedClass(String path) {
        this.path = path;
    }

    public byte[] bytecode() {
        try {
            cachedBytecode = Optional.ofNullable(cachedBytecode).orElse(Files.readAllBytes(Paths.get(path)));
            return cachedBytecode;
        } catch (IOException exception) {
            throw new ClassNotFoundByPathException(path, exception);
        }
    }
}
