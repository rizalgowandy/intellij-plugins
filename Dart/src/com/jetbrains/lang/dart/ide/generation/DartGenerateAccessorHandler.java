// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.jetbrains.lang.dart.ide.generation;

import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.lang.dart.DartComponentType;
import com.jetbrains.lang.dart.psi.DartClass;
import com.jetbrains.lang.dart.psi.DartComponent;
import com.jetbrains.lang.dart.util.DartResolveUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class DartGenerateAccessorHandler extends BaseDartGenerateHandler {

  private final CreateGetterSetterFix.Strategy myStrategy;

  protected DartGenerateAccessorHandler(CreateGetterSetterFix.Strategy strategy) {
    myStrategy = strategy;
  }

  @Override
  protected @NotNull BaseCreateMethodsFix createFix(final @NotNull DartClass dartClass) {
    return new CreateGetterSetterFix(dartClass, myStrategy);
  }

  @Override
  protected void collectCandidates(final @NotNull DartClass dartClass, final @NotNull List<DartComponent> candidates) {
    final List<DartComponent> subComponents = DartResolveUtil.getNamedSubComponents(dartClass);

    candidates.addAll(ContainerUtil.findAll(computeClassMembersMap(dartClass, true).values(),
                                            component -> DartComponentType.typeOf(component) == DartComponentType.FIELD &&
                                                         myStrategy.accept(component.getName(), subComponents)));
  }
}
