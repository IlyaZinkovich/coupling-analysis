package io.coupling.domain.extract;

import io.coupling.domain.core.CouplingStatistic;
import io.coupling.domain.core.CouplingStatisticGenerator;
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
