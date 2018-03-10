package io.coupling.intellij.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
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
