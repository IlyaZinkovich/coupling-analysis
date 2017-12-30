package io.analysis.coupling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class BytecodeSource {

    private String path;

    private byte[] cachedBytecode;

    public BytecodeSource(String filePath) {
        this.path = filePath;
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
