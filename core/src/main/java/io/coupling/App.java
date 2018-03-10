package io.coupling;

import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    new ProjectCouplingAnalyser("core/out/production/classes").analyse().stream()
        .filter(analysedClass -> analysedClass.className().startsWith("io"))
        .filter(analysedClass -> !analysedClass.className().endsWith("Test"))
        .sorted(Comparator.comparing(AnalysedClass::className))
        .forEach(analysedClass -> {
          final String className = analysedClass.className();
          final int afferentCoupling = analysedClass.afferentCoupling();
          final int efferentCoupling = analysedClass.efferentCoupling();
          logger.info(String.format("%s %d %d", className, afferentCoupling, efferentCoupling));
        });
  }
}
