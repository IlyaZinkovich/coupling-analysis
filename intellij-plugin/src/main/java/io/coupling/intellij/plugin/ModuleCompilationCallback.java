package io.coupling.intellij.plugin;

import com.intellij.openapi.wm.ToolWindow;
import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import java.util.function.Consumer;

public class ModuleCompilationCallback implements Consumer<ModuleOutputPath> {

  private static final String PROJECT_CLASSES_DIRECTORY = "io/coupling";

  private final ToolWindow toolWindow;
  private final AnalysedClassesTableFactory factory;

  ModuleCompilationCallback(final ToolWindow toolWindow,
      final AnalysedClassesTableFactory factory) {
    this.toolWindow = toolWindow;
    this.factory = factory;
  }

  @Override
  public void accept(final ModuleOutputPath path) {
    final String moduleName = path.moduleName();
    final AnalysedClassesPanel analysedClassesPanel = new AnalysedClassesPanel(moduleName, factory);
    analysedClassesPanel.addTo(toolWindow);
    final AnalysedClassesTable table = analysedClassesPanel.getTable();
    final ProjectCouplingAnalyser analyser = new ProjectCouplingAnalyser(path.absolute());
    final List<AnalysedClass> analysedClasses = analyser.analyse();
    analysedClasses.stream()
        .filter(analysedClass -> analysedClass.className().startsWith(PROJECT_CLASSES_DIRECTORY))
        .forEach(table::add);
  }
}
