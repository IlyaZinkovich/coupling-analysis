package io.coupling.intellij.plugin;

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.module.Module;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

class ProjectCompiler {

  private final CompilerManager compilerManager;
  private final Consumer<ModuleOutputPath> callback;

  ProjectCompiler(final CompilerManager compilerManager,
      final Consumer<ModuleOutputPath> callback) {
    this.compilerManager = compilerManager;
    this.callback = callback;
  }

  void compile() {
    compilerManager.rebuild(this::compilationResults);
  }

  private void compilationResults(final boolean aborted, final int errors, final int warnings,
      final CompileContext compileContext) {
    final Module[] affectedModules = compileContext.getCompileScope().getAffectedModules();
    Arrays.stream(affectedModules)
        .peek(module -> reportModuleCompilationResults(aborted, errors, warnings, module))
        .map(module -> extractModuleOutputPath(compileContext, module))
        .forEach(moduleOutputPath -> moduleOutputPath.ifPresent(callback));
  }

  private void reportModuleCompilationResults(final boolean aborted, final int errors,
      final int warnings, final Module module) {
    final String moduleName = module.getModuleScope().getDisplayName();
    System.out.printf("%s module compilation results:%n aborted: %b, errors: %d, warnings: %d%n/%n",
        moduleName, aborted, errors, warnings);
  }

  private Optional<ModuleOutputPath> extractModuleOutputPath(final CompileContext compileContext,
      final Module module) {
    return Optional.ofNullable(compileContext.getModuleOutputDirectory(module))
        .map(outputPath -> new ModuleOutputPath(module, outputPath.getPath()));
  }
}
