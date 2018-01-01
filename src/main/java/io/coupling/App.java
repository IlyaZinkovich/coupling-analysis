package io.coupling;

import io.coupling.bytecode.BytecodeSource;
import io.coupling.bytecode.MultiSourceCouplingStatisticGenerator;
import io.coupling.filesystem.FileBytecodeSourcesGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        final BytecodeSource[] bytecodeSources = new FileBytecodeSourcesGenerator(Paths.get("build/classes")).bytecodeSources();
        new MultiSourceCouplingStatisticGenerator(bytecodeSources).couplingStatistic().analyse().stream()
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
