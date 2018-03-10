package io.coupling.intellij.plugin;

import com.intellij.openapi.wm.ToolWindow;
import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import java.util.function.Consumer;

public class ModuleCompilationCallback implements Consumer<ModuleOutputPath> {

  private final ToolWindow toolWindow;

  ModuleCompilationCallback(final ToolWindow toolWindow) {
    this.toolWindow = toolWindow;
  }

  @Override
  public void accept(final ModuleOutputPath path) {
    final AnalysedClassesPanel analysedClassesPanel = new AnalysedClassesPanel(path.moduleName());
    analysedClassesPanel.addTo(toolWindow);
    final AnalysedClassesTableModel tableModel = analysedClassesPanel.getTableModel();
    final ProjectCouplingAnalyser analyser = new ProjectCouplingAnalyser(path.absolute());
    final List<AnalysedClass> analysedClasses = analyser.analyse();
    analysedClasses.stream()
        .filter(analysedClass -> analysedClass.className().startsWith("io/coupling"))
        .forEach(tableModel::add);
  }
}
