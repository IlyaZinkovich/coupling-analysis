package io.analysis.coupling.extract;

import io.analysis.coupling.Coupling;

import java.util.List;

public interface CouplingExtractor {

    List<Coupling> coupling();
}
