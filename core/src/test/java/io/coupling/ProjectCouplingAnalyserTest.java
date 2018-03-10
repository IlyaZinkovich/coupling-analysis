package io.coupling;

import static org.junit.Assert.assertEquals;

import io.coupling.domain.core.AnalysedClass;
import io.coupling.service.ProjectCouplingAnalyser;
import java.util.List;
import java.util.Optional;
import org.junit.Test;

public class ProjectCouplingAnalyserTest {

  @Test
  public void analyse() {
    final String target = "build/classes/test/io/coupling/classes";
    final List<AnalysedClass> analysedClasses = new ProjectCouplingAnalyser(target).analyse();
    final Optional<AnalysedClass> caller = analysedClasses.stream()
        .filter(analysedClass -> "io/coupling/classes/Caller".equals(analysedClass.className()))
        .findAny();
    assertEquals(Optional.of(3), caller.map(AnalysedClass::efferentCoupling));
    assertEquals(Optional.of(0), caller.map(AnalysedClass::afferentCoupling));
    final Optional<AnalysedClass> receiver = analysedClasses.stream()
        .filter(analysedClass -> "io/coupling/classes/Receiver".equals(analysedClass.className()))
        .findAny();
    assertEquals(Optional.of(3), receiver.map(AnalysedClass::efferentCoupling));
    assertEquals(Optional.of(2), receiver.map(AnalysedClass::afferentCoupling));
  }
}