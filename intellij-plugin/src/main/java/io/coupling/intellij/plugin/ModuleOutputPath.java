package io.coupling.intellij.plugin;

import com.intellij.openapi.module.Module;

class ModuleOutputPath {

  private static final char UNDERSCORE = '_';

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

  String absolute() {
    return absolutePath;
  }

  String moduleName() {
    final String name = module.getName();
    final int lastIndexOfUnderscore = name.lastIndexOf(UNDERSCORE);
    final int firstIndex = 0;
    return name.substring(firstIndex, lastIndexOfUnderscore);
  }
}
