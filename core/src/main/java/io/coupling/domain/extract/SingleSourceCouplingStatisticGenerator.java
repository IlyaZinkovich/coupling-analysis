package io.coupling.domain.extract;

import io.coupling.domain.core.CouplingStatistic;
import io.coupling.domain.core.CouplingStatisticGenerator;
import org.objectweb.asm.ClassReader;

public class SingleSourceCouplingStatisticGenerator implements CouplingStatisticGenerator {

  private final BytecodeSource bytecodeSource;

  public SingleSourceCouplingStatisticGenerator(final BytecodeSource bytecodeSource) {
    this.bytecodeSource = bytecodeSource;
  }

  @Override
  public CouplingStatistic couplingStatistic() {
    final ClassReader reader = new ClassReader(bytecodeSource.bytecode());
    final CouplingExtractingClassVisitor classVisitor = new CouplingExtractingClassVisitor();
    reader.accept(classVisitor, 0);
    return new CouplingStatistic(classVisitor.coupling());
  }
}
