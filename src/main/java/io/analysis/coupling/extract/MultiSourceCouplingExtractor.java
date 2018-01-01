package io.analysis.coupling.extract;

import io.analysis.coupling.Coupling;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MultiSourceCouplingExtractor implements CouplingExtractor {

    private BytecodeSource[] bytecodeSources;

    public MultiSourceCouplingExtractor(BytecodeSource... bytecodeSources) {
        this.bytecodeSources = bytecodeSources;
    }

    @Override
    public List<Coupling> coupling() {
        return Arrays.stream(bytecodeSources)
                .map(SingleSourceCouplingExtractor::new)
                .map(SingleSourceCouplingExtractor::coupling)
                .flatMap(Collection::stream)
                .collect(toList());
    }
}
