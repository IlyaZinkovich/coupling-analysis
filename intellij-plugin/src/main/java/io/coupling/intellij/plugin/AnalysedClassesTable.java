package io.coupling.intellij.plugin;

import static com.intellij.ui.ScrollPaneFactory.createScrollPane;
import static java.awt.BorderLayout.WEST;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.JPanel;
import javax.swing.JTable;

class AnalysedClassesTable extends JTable {

  private static final int PREFERRED_CLASS_COLUMN_WIDTH = 300;

  private final AnalysedClassesTableModel tableModel;
  private final transient AnalysedClassesCache cache;

  AnalysedClassesTable(final AnalysedClassesTableModel tableModel,
      final AnalysedClassesCache cache) {
    super(tableModel);
    this.tableModel = tableModel;
    this.cache = cache;
  }

  public void addTo(final JPanel panel) {
    final int first = 0;
    this.getColumnModel().getColumn(first).setPreferredWidth(PREFERRED_CLASS_COLUMN_WIDTH);
    this.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
    this.setAutoCreateRowSorter(true);
    this.getSelectionModel().addListSelectionListener(e -> listSelectionListener());
    panel.add(createScrollPane(this), WEST);
  }

  private void listSelectionListener() {
    final int selectedRow = getSelectedRow();
    final String selectedClassName = tableModel.className(selectedRow);
    final AnalysedClass selectedAnalysedClass = cache.get(selectedClassName);
    System.out.println(selectedAnalysedClass.className());
  }

  public void add(final AnalysedClass analysedClass) {
    cache.put(analysedClass);
    tableModel.add(analysedClass);
  }
}
