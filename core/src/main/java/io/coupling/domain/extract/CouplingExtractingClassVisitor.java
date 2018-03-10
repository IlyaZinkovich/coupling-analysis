package io.coupling.domain.extract;

import static java.util.stream.Collectors.toList;

import io.coupling.domain.core.Coupling;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CouplingExtractingClassVisitor extends ClassVisitor {

  private String callingClassName;
  private List<CouplingExtractingMethodVisitor> methodVisitors = new ArrayList<>();

  CouplingExtractingClassVisitor() {
    super(Opcodes.ASM6);
  }

  @Override
  public void visit(int version, int access, String name, String signature, String superName,
      String[] interfaces) {
    super.visit(version, access, name, signature, superName, interfaces);
    callingClassName = name;
  }

  @Override
  public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
      String[] exceptions) {
    super.visitMethod(access, name, descriptor, signature, exceptions);
    final CouplingExtractingMethodVisitor methodVisitor =
        new CouplingExtractingMethodVisitor(callingClassName, name, descriptor);
    methodVisitors.add(methodVisitor);
    return methodVisitor;
  }

  List<Coupling> coupling() {
    return methodVisitors.stream().flatMap(visitor -> visitor.coupling().stream())
        .collect(toList());
  }
}
