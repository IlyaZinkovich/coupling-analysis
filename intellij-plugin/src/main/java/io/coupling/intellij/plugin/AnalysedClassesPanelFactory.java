package io.coupling.intellij.plugin;

import com.intellij.util.ui.JBUI.Borders;
import java.awt.BorderLayout;
import javax.swing.JPanel;

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
    final DetailedAnalysisPanel detailedAnalysisPanel = new DetailedAnalysisPanel();
    configurePanelLayout(detailedAnalysisPanel);
    final AnalysedClassesTable table = tableFactory.create(detailedAnalysisPanel);
    final AnalysedClassesPanel analysedClassesPanel = new AnalysedClassesPanel(name);
    configurePanelLayout(analysedClassesPanel);
    analysedClassesPanel.add(table);
    analysedClassesPanel.add(detailedAnalysisPanel);
    toolWindowContent.add(analysedClassesPanel);
    return analysedClassesPanel;
  }

  private void configurePanelLayout(final JPanel panel) {
    panel.setLayout(new BorderLayout());
    panel.setBorder(Borders.empty(PADDING));
  }
}
