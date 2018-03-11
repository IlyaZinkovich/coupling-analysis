package io.coupling.intellij.plugin;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

import java.util.HashMap;

public class AnalysedClassesTableFactory {

  private static final int PREFERRED_CLASS_COLUMN_WIDTH = 300;
  private static final int CLASS_COLUMN = 0;

  AnalysedClassesTable create() {
    final AnalysedClassesTableModel tableModel = new AnalysedClassesTableModel();
    final AnalysedClassesCache cache = new AnalysedClassesCache(new HashMap<>());
    final AnalysedClassesTable table = new AnalysedClassesTable(tableModel, cache);
    table.getColumnModel().getColumn(CLASS_COLUMN).setPreferredWidth(PREFERRED_CLASS_COLUMN_WIDTH);
    table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    table.setAutoCreateRowSorter(true);
    table.registerSelectionListener();
    return table;
  }
}
