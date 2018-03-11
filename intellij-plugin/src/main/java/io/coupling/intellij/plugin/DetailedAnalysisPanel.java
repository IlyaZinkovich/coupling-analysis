package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DetailedAnalysisPanel extends JPanel {

  void show(final AnalysedClass selectedClass) {
    JLabel label = new JLabel(selectedClass.className());
    add(label);
  }
}
