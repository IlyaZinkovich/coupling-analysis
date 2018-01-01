package io.analysis.coupling;

import io.analysis.coupling.extract.BytecodeSource;
import io.analysis.coupling.extract.MultiSourceCouplingExtractor;
import io.analysis.coupling.filesystem.FileBytecodeSourcesGenerator;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.*;

public class ProjectCouplingAnalyser {

    private String targetDirectoryPath;

    public ProjectCouplingAnalyser(String targetDirectoryPath) {
        this.targetDirectoryPath = targetDirectoryPath;
    }

    public List<AnalysedClass> analyse() {
        final BytecodeSource[] bytecodeSources = new FileBytecodeSourcesGenerator(Paths.get(targetDirectoryPath)).bytecodeSources();
        final List<Coupling> couplings = new MultiSourceCouplingExtractor(bytecodeSources).coupling();
        final Set<String> analysedClassesNames = couplings.stream()
                .flatMap(coupling -> Stream.of(coupling.source(), coupling.target()))
                .map(ClassPartDescriptor::className)
                .collect(toSet());
        final Map<String, List<Coupling>> outboundCouplings = couplings.stream()
                .collect(groupingBy(coupling -> coupling.source().className()));
        final Map<String, List<Coupling>> inboundCouplings = couplings.stream()
                .collect(groupingBy(coupling -> coupling.target().className()));
        return analysedClassesNames.stream().map(className -> new AnalysedClass(
                        className,
                        outboundCouplings.getOrDefault(className, emptyList()),
                        inboundCouplings.getOrDefault(className, emptyList())
                )
        ).collect(toList());
    }
}
