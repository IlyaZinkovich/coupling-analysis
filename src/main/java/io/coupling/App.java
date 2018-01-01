package io.coupling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        new ProjectCouplingAnalyser("build/classes").analyse().stream()
                .filter(analysedClass -> analysedClass.className().startsWith("io"))
                .filter(analysedClass -> !analysedClass.className().endsWith("Test"))
                .forEach(analysedClass -> {
                    final String className = analysedClass.className();
                    final int afferentCoupling = analysedClass.afferentCoupling();
                    final int efferentCoupling = analysedClass.efferentCoupling();
                    logger.info(String.format("%s %d %d", className, afferentCoupling, efferentCoupling));
                });
    }
}
