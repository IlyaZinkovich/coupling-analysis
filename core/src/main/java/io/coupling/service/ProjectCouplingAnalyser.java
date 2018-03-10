package io.coupling.service;

import io.coupling.domain.core.AnalysedClass;
import io.coupling.domain.core.CouplingStatisticsCombiner;
import io.coupling.domain.extract.BytecodeSource;
import io.coupling.domain.extract.SingleSourceCouplingStatisticGenerator;
import io.coupling.infrastructure.extract.filesystem.FileBytecodeSourcesGenerator;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ProjectCouplingAnalyser {

  private String targetDirectory;

  public ProjectCouplingAnalyser(String targetDirectory) {
    this.targetDirectory = targetDirectory;
  }

  public List<AnalysedClass> analyse() {
    final BytecodeSource[] bytecodeSources = new FileBytecodeSourcesGenerator(
        Paths.get(targetDirectory)).bytecodeSources();
    final SingleSourceCouplingStatisticGenerator[] generators = Arrays.stream(bytecodeSources)
        .map(SingleSourceCouplingStatisticGenerator::new)
        .toArray(SingleSourceCouplingStatisticGenerator[]::new);
    return new CouplingStatisticsCombiner(generators).couplingStatistic().analyse();
  }
}
