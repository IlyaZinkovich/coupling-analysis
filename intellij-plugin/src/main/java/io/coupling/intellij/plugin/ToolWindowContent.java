package io.coupling.intellij.plugin;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
import javax.swing.JPanel;

class ToolWindowContent {

  private final ToolWindow toolWindow;

  ToolWindowContent(final ToolWindow toolWindow) {
    this.toolWindow = toolWindow;
  }

  void add(final JPanel panel) {
    final ContentFactory contentFactory = SERVICE.getInstance();
    final boolean isNotLockable = false;
    final String panelName = panel.getName();
    final Content content = contentFactory.createContent(panel, panelName, isNotLockable);
    toolWindow.getContentManager().addContent(content);
  }
}
