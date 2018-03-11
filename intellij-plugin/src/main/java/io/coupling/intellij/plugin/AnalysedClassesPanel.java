package io.coupling.intellij.plugin;

import static com.intellij.ui.ScrollPaneFactory.createScrollPane;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;

import javax.swing.JPanel;

class AnalysedClassesPanel extends JPanel {

  private final String name;
  private AnalysedClassesTable table;

  AnalysedClassesPanel(final String name) {
    this.name = name;
  }

  void add(final AnalysedClassesTable table) {
    this.add(createScrollPane(table), WEST);
    this.table = table;
  }

  void add(final DetailedAnalysisPanel detailedAnalysisPanel) {
    this.add(detailedAnalysisPanel, EAST);
  }

  @Override
  public String getName() {
    return name;
  }

  AnalysedClassesTable table() {
    return table;
  }
}
