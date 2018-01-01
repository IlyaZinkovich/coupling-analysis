package io.coupling.statistic;

import io.coupling.core.CouplingStatistic;

import java.util.Arrays;

import static java.util.Collections.emptyList;

public class CouplingStatisticsCombiner implements CouplingStatisticGenerator {

    private CouplingStatisticGenerator[] generators;

    public CouplingStatisticsCombiner(CouplingStatisticGenerator... generators) {
        this.generators = generators;
    }

    @Override
    public CouplingStatistic couplingStatistic() {
        return Arrays.stream(generators)
                .map(CouplingStatisticGenerator::couplingStatistic)
                .reduce(CouplingStatistic::merge)
                .orElse(new CouplingStatistic(emptyList()));
    }
}
