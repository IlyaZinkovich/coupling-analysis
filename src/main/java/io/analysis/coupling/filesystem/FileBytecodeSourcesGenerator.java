package io.analysis.coupling.filesystem;

import io.analysis.coupling.extract.BytecodeSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileBytecodeSourcesGenerator {

    private Path rootDirectoryPath;

    public FileBytecodeSourcesGenerator(Path rootDirectoryPath) {
        this.rootDirectoryPath = rootDirectoryPath;
    }

    public BytecodeSource[] bytecodeSources() {
        try (Stream<Path> targetFilesPaths = Files.walk(rootDirectoryPath)) {
            return targetFilesPaths.filter(path -> path.toString().endsWith(".class"))
                    .map(FileBytecodeSource::new).toArray(BytecodeSource[]::new);
        } catch (IOException e) {
            throw new BytecodeNotFoundByPathException(rootDirectoryPath.toString(), e);
        }
    }
}
