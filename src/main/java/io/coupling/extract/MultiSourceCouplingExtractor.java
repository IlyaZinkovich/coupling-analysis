package io.coupling.extract;

import io.coupling.CouplingStatistic;

import java.util.Arrays;

import static java.util.Collections.emptyList;

public class MultiSourceCouplingExtractor implements CouplingExtractor {

    private BytecodeSource[] bytecodeSources;

    public MultiSourceCouplingExtractor(BytecodeSource... bytecodeSources) {
        this.bytecodeSources = bytecodeSources;
    }

    @Override
    public CouplingStatistic couplingStatistic() {
        return Arrays.stream(bytecodeSources)
                .map(SingleSourceCouplingExtractor::new)
                .map(SingleSourceCouplingExtractor::couplingStatistic)
                .reduce(CouplingStatistic::merge)
                .orElse(new CouplingStatistic(emptyList()));
    }
}
