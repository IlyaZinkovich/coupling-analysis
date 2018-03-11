package io.coupling.intellij.plugin;

import com.intellij.util.ui.JBUI.Borders;
import java.awt.BorderLayout;

class AnalysedClassesPanelFactory {

  private static final int PADDING = 5;

  private final AnalysedClassesTableFactory tableFactory;
  private final ToolWindowContent toolWindowContent;

  AnalysedClassesPanelFactory(final AnalysedClassesTableFactory tableFactory,
      final ToolWindowContent toolWindowContent) {
    this.tableFactory = tableFactory;
    this.toolWindowContent = toolWindowContent;
  }

  AnalysedClassesPanel create(final String name) {
    final AnalysedClassesTable table = tableFactory.create();
    final AnalysedClassesPanel panel = new AnalysedClassesPanel(name);
    panel.setLayout(new BorderLayout());
    panel.setBorder(Borders.empty(PADDING));
    panel.add(table);
    toolWindowContent.add(panel);
    return panel;
  }
}
