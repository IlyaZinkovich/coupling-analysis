package io.coupling.intellij.plugin;

import com.intellij.openapi.module.Module;

public class ModuleOutputPath {

  private final Module module;
  private final String absolutePath;

  ModuleOutputPath(final Module module, final String absolutePath) {
    this.module = module;
    this.absolutePath = absolutePath;
  }

  @Override
  public String toString() {
    return "ModuleOutputPath{" + module +
        ", absolutePath='" + absolutePath + '\'' +
        '}';
  }

  public String absolute() {
    return absolutePath;
  }
}
