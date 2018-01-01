package io.analysis.coupling;

public class Coupling {

    private Dependency to;
    private Dependency from;

    public Coupling(Dependency from, Dependency to) {
        this.to = to;
        this.from = from;
    }

    public Dependency to() {
        return to;
    }

    public Dependency from() {
        return from;
    }
}
