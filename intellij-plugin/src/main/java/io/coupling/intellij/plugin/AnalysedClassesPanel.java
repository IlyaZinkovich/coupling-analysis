package io.coupling.intellij.plugin;

import static com.intellij.ui.ScrollPaneFactory.createScrollPane;
import static java.awt.BorderLayout.WEST;

import javax.swing.JPanel;

public class AnalysedClassesPanel extends JPanel {

  private final String name;

  AnalysedClassesPanel(final String name) {
    this.name = name;
  }

  public void add(final AnalysedClassesTable table) {
    this.add(createScrollPane(table), WEST);
  }

  @Override
  public String getName() {
    return name;
  }
}
