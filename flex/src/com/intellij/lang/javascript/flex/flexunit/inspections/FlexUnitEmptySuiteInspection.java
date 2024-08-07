// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.lang.javascript.flex.flexunit.inspections;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.lang.ASTNode;
import com.intellij.lang.javascript.flex.FlexBundle;
import com.intellij.lang.javascript.flex.flexunit.FlexUnitSupport;
import com.intellij.lang.javascript.psi.ecmal4.JSClass;
import org.jetbrains.annotations.NotNull;

public final class FlexUnitEmptySuiteInspection extends FlexUnitSuiteInspectionBase {

  @Override
  public @NotNull String getShortName() {
    return "FlexUnitEmptySuiteInspection";
  }


  @Override
  protected void visitSuite(JSClass aClass, @NotNull ProblemsHolder holder, FlexUnitSupport support) {
    if (support.isFlexUnit1SuiteSubclass(aClass) || support.isFlexUnit1Subclass(aClass)) return;
    if (FlexUnitSupport.SUITE_RUNNER.equals(FlexUnitSupport.getCustomRunner(aClass)) && support.getSuiteTestClasses(aClass).isEmpty()) {
      final ASTNode nameIdentifier = aClass.findNameIdentifier();
      if (nameIdentifier != null) {
        holder.registerProblem(nameIdentifier.getPsi(), FlexBundle.message("flexunit.inspection.emptysuite.message"),
                               ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
      }
    }

  }
}