package io.coupling.infrastructure.extract.filesystem;

class BytecodeNotFoundByPathException extends RuntimeException {

  BytecodeNotFoundByPathException(String path, Throwable cause) {
    super(String.format("Provided path: %s", path), cause);
  }
}
