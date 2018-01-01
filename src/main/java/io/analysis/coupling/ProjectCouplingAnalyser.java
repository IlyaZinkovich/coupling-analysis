package io.analysis.coupling;

import io.analysis.coupling.extract.CouplingExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        try (Stream<Path> targetFilesPaths = Files.walk(Paths.get(targetDirectoryPath))) {
            final List<Coupling> couplings = targetFilesPaths.filter(path -> path.toString().endsWith(".class"))
                    .flatMap(path -> new CouplingExtractor(new FileBytecodeSource(path)).couplings().stream())
                    .collect(toList());
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
        } catch (IOException e) {
            throw new BytecodeNotFoundByPathException(targetDirectoryPath, e);
        }
    }
}
