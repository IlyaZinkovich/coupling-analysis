package io.coupling;

import io.coupling.extract.BytecodeSource;
import io.coupling.extract.MultiSourceCouplingExtractor;
import io.coupling.filesystem.FileBytecodeSourcesGenerator;

import java.nio.file.Paths;
import java.util.List;

public class ProjectCouplingAnalyser {

    private String targetDirectoryPath;

    public ProjectCouplingAnalyser(String targetDirectoryPath) {
        this.targetDirectoryPath = targetDirectoryPath;
    }

    public List<AnalysedClass> analyse() {
        final BytecodeSource[] bytecodeSources = new FileBytecodeSourcesGenerator(Paths.get(targetDirectoryPath)).bytecodeSources();
        final CouplingStatistic statistic = new MultiSourceCouplingExtractor(bytecodeSources).couplingStatistic();
        return statistic.analyse();
    }
}
