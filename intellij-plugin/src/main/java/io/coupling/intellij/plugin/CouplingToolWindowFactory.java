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
    final AnalysedClassesTableFactory tableFactory = new AnalysedClassesTableFactory();
    final ToolWindowContent content = new ToolWindowContent(toolWindow);
    final AnalysedClassesPanelFactory panelFactory = new AnalysedClassesPanelFactory(tableFactory,
        content);
    final ModuleCompilationCallback callback = new ModuleCompilationCallback(panelFactory);
    final ProjectCompiler projectCompiler = new ProjectCompiler(compilerManager, callback);
    projectCompiler.compile();
  }
}
