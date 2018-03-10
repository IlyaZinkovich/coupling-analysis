package io.coupling.infrastructure.extract.filesystem;

import io.coupling.domain.extract.BytecodeSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileBytecodeSourcesGenerator {

  private final Path rootDirectoryPath;

  public FileBytecodeSourcesGenerator(final Path rootDirectoryPath) {
    this.rootDirectoryPath = rootDirectoryPath;
  }

  public BytecodeSource[] bytecodeSources() {
    try (final Stream<Path> targetFilesPaths = Files.walk(rootDirectoryPath)) {
      return targetFilesPaths.filter(path -> path.toString().endsWith(".class"))
          .map(FileBytecodeSource::new).toArray(BytecodeSource[]::new);
    } catch (final IOException e) {
      throw new BytecodeNotFoundByPathException(rootDirectoryPath.toString(), e);
    }
  }
}
