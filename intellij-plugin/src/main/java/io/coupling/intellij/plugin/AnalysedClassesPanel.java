package io.coupling.intellij.plugin;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactory.SERVICE;
import com.intellij.util.ui.JBUI.Borders;
import java.awt.BorderLayout;
import java.util.Optional;
import javax.swing.JPanel;

public class AnalysedClassesPanel extends JPanel {

  private static final int PADDING = 5;

  private final String name;
  private final transient AnalysedClassesTableFactory factory;
  private AnalysedClassesTable table;

  AnalysedClassesPanel(final String name, final AnalysedClassesTableFactory factory) {
    this.name = name;
    this.factory = factory;
  }

  public void addTo(final ToolWindow toolWindow) {
    final ContentFactory contentFactory = SERVICE.getInstance();
    final boolean isNotLockable = false;
    final Content projectContent = contentFactory.createContent(this, name, isNotLockable);
    toolWindow.getContentManager().addContent(projectContent);
    this.setLayout(new BorderLayout());
    this.setBorder(Borders.empty(PADDING));
  }

  public AnalysedClassesTable getTable() {
    return Optional.ofNullable(table).orElseGet(() -> {
      table = factory.create();
      table.addTo(this);
      return table;
    });
  }
}
