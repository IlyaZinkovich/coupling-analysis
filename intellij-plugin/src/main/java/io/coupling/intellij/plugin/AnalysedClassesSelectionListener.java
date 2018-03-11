package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class AnalysedClassesSelectionListener implements ListSelectionListener {

  private final AnalysedClassesTableModel tableModel;
  private final DetailedAnalysisPanel detailedAnalysisPanel;

  AnalysedClassesSelectionListener(final AnalysedClassesTableModel tableModel,
      final DetailedAnalysisPanel detailedAnalysisPanel) {
    this.tableModel = tableModel;
    this.detailedAnalysisPanel = detailedAnalysisPanel;
  }

  @Override
  public void valueChanged(final ListSelectionEvent e) {
    final int selectedRowIndex = e.getFirstIndex();
    final AnalysedClass selectedClass = tableModel.analysedClass(selectedRowIndex);
    detailedAnalysisPanel.show(selectedClass);
  }
}
