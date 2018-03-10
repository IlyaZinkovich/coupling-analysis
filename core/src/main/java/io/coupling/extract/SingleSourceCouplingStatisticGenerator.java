package io.coupling.extract;

import io.coupling.core.CouplingStatistic;
import io.coupling.statistic.CouplingStatisticGenerator;
import org.objectweb.asm.ClassReader;

public class SingleSourceCouplingStatisticGenerator implements CouplingStatisticGenerator {

  private BytecodeSource bytecodeSource;

  public SingleSourceCouplingStatisticGenerator(BytecodeSource bytecodeSource) {
    this.bytecodeSource = bytecodeSource;
  }

  @Override
  public CouplingStatistic couplingStatistic() {
    ClassReader reader = new ClassReader(bytecodeSource.bytecode());
    CouplingExtractingClassVisitor classVisitor = new CouplingExtractingClassVisitor();
    reader.accept(classVisitor, 0);
    return new CouplingStatistic(classVisitor.coupling());
  }
}
