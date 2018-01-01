package io.coupling.statistic;

import io.coupling.core.AnalysedClass;

import java.util.List;

public class ProjectCouplingAnalyser {

    private final CouplingStatisticGenerator statisticGenerator;

    public ProjectCouplingAnalyser(CouplingStatisticGenerator statisticGenerator) {
        this.statisticGenerator = statisticGenerator;
    }

    public List<AnalysedClass> analyse() {
        return statisticGenerator.couplingStatistic().analyse();
    }
}
