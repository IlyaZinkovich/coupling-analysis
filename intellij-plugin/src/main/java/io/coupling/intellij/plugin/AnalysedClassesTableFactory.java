package io.coupling.intellij.plugin;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

import java.util.HashMap;

class AnalysedClassesTableFactory {

  private static final int PREFERRED_CLASS_COLUMN_WIDTH = 400;
  private static final int CLASS_COLUMN = 0;
  private static final Object[] COLUMN_NAMES = {"Class", "In", "Out", "Instability"};
  private static final int NO_ROWS = 0;

  AnalysedClassesTable create() {
    final AnalysedClassesTableModel model = new AnalysedClassesTableModel(COLUMN_NAMES, NO_ROWS);
    final AnalysedClassesCache cache = new AnalysedClassesCache(new HashMap<>());
    final AnalysedClassesTable table = new AnalysedClassesTable(model, cache);
    table.getColumnModel().getColumn(CLASS_COLUMN).setPreferredWidth(PREFERRED_CLASS_COLUMN_WIDTH);
    table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    table.setAutoCreateRowSorter(true);
    table.registerSelectionListener();
    return table;
  }
}
