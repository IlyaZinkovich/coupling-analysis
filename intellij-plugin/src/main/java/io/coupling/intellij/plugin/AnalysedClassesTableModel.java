package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import javax.swing.table.DefaultTableModel;

public class AnalysedClassesTableModel extends DefaultTableModel {

  AnalysedClassesTableModel() {
    super(new Object[]{"Class", "In", "Out"}, 0);
  }

  public void add(AnalysedClass analysedClass) {
    final String className = analysedClass.className().replaceAll("/", ".");
    final int inboundCoupling = analysedClass.afferentCoupling();
    final int outboundCoupling = analysedClass.efferentCoupling();
    addRow(new Object[]{className, inboundCoupling, outboundCoupling});
  }

  @Override
  public Class getColumnClass(int column) {
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
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
