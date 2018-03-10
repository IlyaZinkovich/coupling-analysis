package io.coupling.extract;

import static java.util.Collections.unmodifiableList;

import io.coupling.core.ClassPartDescriptor;
import io.coupling.core.Coupling;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CouplingExtractingMethodVisitor extends MethodVisitor {

  private String callingClassName;
  private String callingMethodName;
  private String callingMethodDescriptor;
  private List<Coupling> couplings = new ArrayList<>();

  public CouplingExtractingMethodVisitor(String callingClassName, String callingMethodName,
      String callingMethodDescriptor) {
    super(Opcodes.ASM6);
    this.callingClassName = callingClassName;
    this.callingMethodName = callingMethodName;
    this.callingMethodDescriptor = callingMethodDescriptor;
  }

  @Override
  public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
    super.visitMethodInsn(opcode, owner, name, desc, itf);
    final ClassPartDescriptor source = new ClassPartDescriptor(callingClassName, callingMethodName,
        callingMethodDescriptor);
    final ClassPartDescriptor target = new ClassPartDescriptor(owner, name, desc);
    couplings.add(new Coupling(source, target));
  }

  public List<Coupling> coupling() {
    return unmodifiableList(couplings);
  }
}
