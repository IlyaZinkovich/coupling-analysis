package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.table.DefaultTableModel;

public class AnalysedClassesTableModel extends DefaultTableModel {

  private static final Object[] COLUMN_NAMES = {"Class", "In", "Out"};
  private static final int INITIAL_ROW_COUNT = 0;
  private static final String DIRECTORY_SYNTAX = "/";
  private static final String PACKAGE_SYNTAX = ".";
  private static final boolean NOT_EDITABLE = false;

  AnalysedClassesTableModel() {
    super(COLUMN_NAMES, INITIAL_ROW_COUNT);
  }

  public void add(final AnalysedClass analysedClass) {
    final String className = analysedClass.className().replaceAll(DIRECTORY_SYNTAX, PACKAGE_SYNTAX);
    final int inboundCoupling = analysedClass.afferentCoupling();
    final int outboundCoupling = analysedClass.efferentCoupling();
    addRow(new Object[]{className, inboundCoupling, outboundCoupling});
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
      default:
        return String.class;
    }
  }

  @Override
  public boolean isCellEditable(final int row, final int column) {
    return NOT_EDITABLE;
  }
}
