package io.coupling.intellij.plugin;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class CouplingToolWindowFactory implements ToolWindowFactory {

  @Override
  public void createToolWindowContent(@NotNull final Project project,
      @NotNull final ToolWindow toolWindow) {
    final JPanel projectPanel = createToolWindowPanel("Project", toolWindow);
    final JPanel currentFilePanel = createToolWindowPanel("Current File", toolWindow);
    currentFilePanel.add(new JButton("bla"));
    final CompilerManager compilerManager = CompilerManager.getInstance(project);
    new ProjectCompiler(compilerManager, this::moduleCompilationCallback).compile();
  }

  private void moduleCompilationCallback(final ModuleOutputPath path) {
    final List<AnalysedClass> analysedClasses =
        new ProjectCouplingAnalyser(path.absolute()).analyse();
    analysedClasses.stream()
        .filter(analysedClass -> analysedClass.className().startsWith("io/coupling"))
        .forEach(
            analysedClass -> System.out.printf("%s in: %d, out: %d%n", analysedClass.className(),
                analysedClass.afferentCoupling(), analysedClass.efferentCoupling()));
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
