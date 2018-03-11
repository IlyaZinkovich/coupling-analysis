package io.coupling.intellij.plugin;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

import java.util.HashMap;

class AnalysedClassesTableFactory {

  private static final int PREFERRED_CLASS_COLUMN_WIDTH = 400;
  private static final int CLASS_COLUMN = 0;
  private static final Object[] COLUMN_NAMES = {"Class", "In", "Out", "Instability"};
  private static final int NO_ROWS = 0;

  AnalysedClassesTable create(final DetailedAnalysisPanel detailedAnalysisPanel) {
    final AnalysedClassesCache cache = new AnalysedClassesCache(new HashMap<>());
    final AnalysedClassesTableModel analysedClassesTableModel =
        new AnalysedClassesTableModel(COLUMN_NAMES, NO_ROWS, cache);
    final AnalysedClassesTable table = new AnalysedClassesTable(analysedClassesTableModel);
    table.getColumnModel().getColumn(CLASS_COLUMN).setPreferredWidth(PREFERRED_CLASS_COLUMN_WIDTH);
    table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    table.setAutoCreateRowSorter(true);
    final AnalysedClassesSelectionListener selectionListener =
        new AnalysedClassesSelectionListener(analysedClassesTableModel, detailedAnalysisPanel);
    table.registerSelectionListener(selectionListener);
    return table;
  }
}
