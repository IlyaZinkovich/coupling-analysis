package io.coupling.intellij.plugin;

import static java.awt.BorderLayout.NORTH;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

class DetailedAnalysisPanel extends JPanel {

  void show(final AnalysedClass selectedClass) {
    this.removeAll();
    JLabel label = new JLabel(selectedClass.className());
    add(label, NORTH);
    add(new JTable());
  }
}
