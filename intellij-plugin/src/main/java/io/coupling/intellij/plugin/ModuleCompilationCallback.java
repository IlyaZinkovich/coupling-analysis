package io.coupling.intellij.plugin;

import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import java.util.function.Consumer;

public class ModuleCompilationCallback implements Consumer<ModuleOutputPath> {

  private static final String PROJECT_CLASSES_DIRECTORY = "io/coupling";

  private final ToolWindowContent toolWindowContent;
  private final AnalysedClassesPanelFactory panelFactory;
  private final AnalysedClassesTableFactory tableFactory;

  ModuleCompilationCallback(final ToolWindowContent toolWindowContent,
      final AnalysedClassesPanelFactory panelFactory,
      final AnalysedClassesTableFactory tableFactory) {
    this.toolWindowContent = toolWindowContent;
    this.panelFactory = panelFactory;
    this.tableFactory = tableFactory;
  }

  @Override
  public void accept(final ModuleOutputPath path) {
    final String moduleName = path.moduleName();
    final AnalysedClassesTable table = tableFactory.create();
    final AnalysedClassesPanel analysedClassesPanel = panelFactory.create(moduleName);
    analysedClassesPanel.add(table);
    toolWindowContent.add(analysedClassesPanel);
    final ProjectCouplingAnalyser analyser = new ProjectCouplingAnalyser(path.absolute());
    final List<AnalysedClass> analysedClasses = analyser.analyse();
    analysedClasses.stream()
        .filter(analysedClass -> analysedClass.className().startsWith(PROJECT_CLASSES_DIRECTORY))
        .forEach(table::add);
  }
}
