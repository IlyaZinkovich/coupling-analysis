package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

class AnalysedClassesTable extends JTable {

  private final AnalysedClassesTableModel tableModel;

  AnalysedClassesTable(final AnalysedClassesTableModel tableModel) {
    super(tableModel);
    this.tableModel = tableModel;
  }

  void add(final AnalysedClass analysedClass) {
    tableModel.add(analysedClass);
  }

  void registerSelectionListener(ListSelectionListener listSelectionListener) {
    this.getSelectionModel().addListSelectionListener(listSelectionListener);
  }
}
