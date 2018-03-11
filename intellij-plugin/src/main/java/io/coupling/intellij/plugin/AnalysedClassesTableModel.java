package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.table.DefaultTableModel;

class AnalysedClassesTableModel extends DefaultTableModel {

  private static final char DIRECTORY_SYNTAX = '/';
  private static final char PACKAGE_SYNTAX = '.';
  private static final boolean NOT_EDITABLE = false;
  private static final int CLASS_NAME_COLUMN = 0;

  AnalysedClassesTableModel(final Object[] columnNames, final int rowCount) {
    super(columnNames, rowCount);
  }

  void add(final AnalysedClass analysedClass) {
    final String className = analysedClass.className().replace(DIRECTORY_SYNTAX, PACKAGE_SYNTAX);
    final int inboundCoupling = analysedClass.afferentCoupling();
    final int outboundCoupling = analysedClass.efferentCoupling();
    final double instability = analysedClass.instability();
    addRow(new Object[]{className, inboundCoupling, outboundCoupling, instability});
  }

  String className(final int selectedRow) {
    final String classNameView = (String) getValueAt(selectedRow, CLASS_NAME_COLUMN);
    return classNameView.replace(PACKAGE_SYNTAX, DIRECTORY_SYNTAX);
  }

  @Override
  public Class getColumnClass(final int column) {
    switch (column) {
      case 0:
        return String.class;
      case 1:
        return Integer.class;
      case 2:
        return Integer.class;
      case 3:
        return Double.class;
      default:
        return String.class;
    }
  }

  @Override
  public boolean isCellEditable(final int row, final int column) {
    return NOT_EDITABLE;
  }
}
