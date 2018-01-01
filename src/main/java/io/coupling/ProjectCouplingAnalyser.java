package io.coupling;

import io.coupling.core.AnalysedClass;
import io.coupling.extract.BytecodeSource;
import io.coupling.extract.SingleSourceCouplingStatisticGenerator;
import io.coupling.filesystem.FileBytecodeSourcesGenerator;
import io.coupling.statistic.CouplingStatisticsCombiner;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ProjectCouplingAnalyser {

    private String targetDirectory;

    public ProjectCouplingAnalyser(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public List<AnalysedClass> analyse() {
        final BytecodeSource[] bytecodeSources = new FileBytecodeSourcesGenerator(Paths.get(targetDirectory)).bytecodeSources();
        final SingleSourceCouplingStatisticGenerator[] generators = Arrays.stream(bytecodeSources)
                .map(SingleSourceCouplingStatisticGenerator::new)
                .toArray(SingleSourceCouplingStatisticGenerator[]::new);
        return new CouplingStatisticsCombiner(generators).couplingStatistic().analyse();
    }
}
