package io.analysis.coupling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class BytecodeSource {

    private Path path;

    private byte[] cachedBytecode;

    public BytecodeSource(Path filePath) {
        this.path = filePath;
    }

    public byte[] bytecode() {
        try {
            cachedBytecode = Optional.ofNullable(cachedBytecode).orElse(Files.readAllBytes(path));
            return cachedBytecode;
        } catch (IOException exception) {
            throw new ClassNotFoundByPathException(path.toString(), exception);
        }
    }
}
