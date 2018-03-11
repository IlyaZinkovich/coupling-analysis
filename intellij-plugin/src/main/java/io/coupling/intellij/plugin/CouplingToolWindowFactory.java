package io.coupling.intellij.plugin;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class CouplingToolWindowFactory implements ToolWindowFactory {

  @Override
  public void createToolWindowContent(@NotNull final Project project,
      @NotNull final ToolWindow toolWindow) {
    final CompilerManager compilerManager = CompilerManager.getInstance(project);
    final AnalysedClassesTableFactory factory = new AnalysedClassesTableFactory();
    final ModuleCompilationCallback callback = new ModuleCompilationCallback(toolWindow, factory);
    final ProjectCompiler projectCompiler = new ProjectCompiler(compilerManager, callback);
    projectCompiler.compile();
  }
}
