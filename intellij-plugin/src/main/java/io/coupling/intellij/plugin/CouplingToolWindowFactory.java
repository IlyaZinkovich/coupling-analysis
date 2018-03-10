package io.coupling.intellij.plugin;

import com.intellij.openapi.compiler.CompileContext;
import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
import java.util.Arrays;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class CouplingToolWindowFactory implements ToolWindowFactory {

  private Project project;
  private ToolWindow toolWindow;

  @Override
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    this.project = project;
    this.toolWindow = toolWindow;
    final JPanel projectPanel = createToolWindowPanel("Project", toolWindow);
    final JPanel currentFilePanel = createToolWindowPanel("Current File", toolWindow);
    currentFilePanel.add(new JButton("bla"));
    final ModuleManager moduleManager = ModuleManager.getInstance(project);
    final Module[] modules = moduleManager.getModules();
    final CompilerManager compilerManager = CompilerManager.getInstance(project);
    Arrays.stream(modules)
        .forEach(module -> compilerManager.compile(module, this::compilationResults));
  }

  private void compilationResults(boolean aborted, int errors, int warnings,
      CompileContext compileContext) {
    final Module module = compileContext.getCompileScope().getAffectedModules()[0];
    final String moduleName = module.getModuleScope().getDisplayName();
    System.out.printf("%s module compilation results:%n aborted: %b, errors: %d, warnings: %d%n/%n",
        moduleName, aborted, errors, warnings);
    Optional.ofNullable(compileContext.getModuleOutputDirectory(module))
        .ifPresent(System.out::println);
  }

  private JPanel createToolWindowPanel(final String name, final ToolWindow toolWindow) {
    final ContentFactory contentFactory = SERVICE.getInstance();
    final JPanel toolWindowPanel = new JPanel();
    final boolean isNotLockable = false;
    final Content projectContent = contentFactory.createContent(toolWindowPanel,
        name, isNotLockable);
    toolWindow.getContentManager().addContent(projectContent);
    return toolWindowPanel;
  }
}
