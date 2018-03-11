package io.coupling.intellij.plugin;

import com.intellij.util.ui.JBUI.Borders;
import java.awt.BorderLayout;

public class AnalysedClassesPanelFactory {

  private static final int PADDING = 5;

  public AnalysedClassesPanel create(final String name) {
    final AnalysedClassesPanel panel = new AnalysedClassesPanel(name);
    panel.setLayout(new BorderLayout());
    panel.setBorder(Borders.empty(PADDING));
    return panel;
  }
}
