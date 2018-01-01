package io.analysis.coupling;

import org.objectweb.asm.ClassReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class CouplingCalculator {

    private BytecodeSource bytecodeSource;

    public CouplingCalculator(BytecodeSource bytecodeSource) {
        this.bytecodeSource = bytecodeSource;
    }

    public List<Coupling> couplings() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        return classVisitor.couplings();
    }

    public EfferentCoupling efferentCoupling() {
        ClassReader reader = new ClassReader(bytecodeSource.bytecode());
        CouplingCalculatingClassVisitor classVisitor = new CouplingCalculatingClassVisitor();
        reader.accept(classVisitor, 0);
        final Set<String> analysedClasses = classVisitor.couplings().stream()
                .flatMap(coupling -> Stream.of(coupling.from(), coupling.to()).map(Dependency::className))
                .collect(toSet());
        final Map<String, List<Coupling>> couplingsMap =
                classVisitor.couplings().stream().collect(groupingBy(coupling -> coupling.from().className()));
        return new EfferentCoupling("", new HashMap<>());
    }
}
