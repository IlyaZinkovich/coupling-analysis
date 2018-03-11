package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.JTable;

class AnalysedClassesTable extends JTable {

  private final AnalysedClassesTableModel tableModel;
  private final transient AnalysedClassesCache cache;

  AnalysedClassesTable(final AnalysedClassesTableModel tableModel,
      final AnalysedClassesCache cache) {
    super(tableModel);
    this.tableModel = tableModel;
    this.cache = cache;
  }

  void add(final AnalysedClass analysedClass) {
    cache.put(analysedClass);
    tableModel.add(analysedClass);
  }

  void registerSelectionListener() {
    this.getSelectionModel().addListSelectionListener(e -> listSelectionListener());
  }

  private void listSelectionListener() {
    final int selectedRow = getSelectedRow();
    final String selectedClassName = tableModel.className(selectedRow);
    final AnalysedClass selectedAnalysedClass = cache.get(selectedClassName);
    System.out.println(selectedAnalysedClass.className());
  }
}
