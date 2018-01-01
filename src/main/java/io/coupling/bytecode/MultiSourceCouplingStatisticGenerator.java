package io.coupling.bytecode;

import io.coupling.core.CouplingStatistic;
import io.coupling.statistic.CouplingStatisticGenerator;

import java.util.Arrays;

import static java.util.Collections.emptyList;

public class MultiSourceCouplingStatisticGenerator implements CouplingStatisticGenerator {

    private BytecodeSource[] bytecodeSources;

    public MultiSourceCouplingStatisticGenerator(BytecodeSource... bytecodeSources) {
        this.bytecodeSources = bytecodeSources;
    }

    @Override
    public CouplingStatistic couplingStatistic() {
        return Arrays.stream(bytecodeSources)
                .map(SingleSourceCouplingStatisticGenerator::new)
                .map(SingleSourceCouplingStatisticGenerator::couplingStatistic)
                .reduce(CouplingStatistic::merge)
                .orElse(new CouplingStatistic(emptyList()));
    }
}
