package io.coupling.intellij.plugin;

import java.util.HashMap;

public class AnalysedClassesTableFactory {

  AnalysedClassesTable create() {
    final AnalysedClassesTableModel tableModel = new AnalysedClassesTableModel();
    final AnalysedClassesCache cache = new AnalysedClassesCache(new HashMap<>());
    return new AnalysedClassesTable(tableModel, cache);
  }
}
