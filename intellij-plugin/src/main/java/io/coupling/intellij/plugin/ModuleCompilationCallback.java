package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import java.util.function.Consumer;

class ModuleCompilationCallback implements Consumer<ModuleOutputPath> {

  private static final String PROJECT_CLASSES_DIRECTORY = "io/coupling";

  private final AnalysedClassesPanelFactory panelFactory;

  ModuleCompilationCallback(final AnalysedClassesPanelFactory panelFactory) {
    this.panelFactory = panelFactory;
  }

  @Override
  public void accept(final ModuleOutputPath path) {
    final String moduleName = path.moduleName();
    final AnalysedClassesPanel analysedClassesPanel = panelFactory.create(moduleName);
    final ProjectCouplingAnalyser analyser = new ProjectCouplingAnalyser(path.absolute());
    final List<AnalysedClass> analysedClasses = analyser.analyse();
    final AnalysedClassesTable table = analysedClassesPanel.table();
    analysedClasses.stream()
        .filter(analysedClass -> analysedClass.className().startsWith(PROJECT_CLASSES_DIRECTORY))
        .forEach(table::add);
  }
}
