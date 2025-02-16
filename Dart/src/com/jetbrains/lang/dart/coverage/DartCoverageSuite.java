// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.jetbrains.lang.dart.coverage;

import com.intellij.coverage.BaseCoverageSuite;
import com.intellij.coverage.CoverageEngine;
import com.intellij.coverage.CoverageFileProvider;
import com.intellij.coverage.CoverageRunner;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DartCoverageSuite extends BaseCoverageSuite {
  private static final @NonNls String CONTEXT_FILE_PATH = "CONTEXT_FILE_PATH";

  private @Nullable String myContextFilePath;
  private final @Nullable ProcessHandler myCoverageProcess;

  public DartCoverageSuite() {
    myCoverageProcess = null;
  }

  public DartCoverageSuite(@NotNull Project project,
                           @NotNull String name,
                           @NotNull CoverageFileProvider fileProvider,
                           @NotNull CoverageRunner coverageRunner,
                           long timestamp,
                           @Nullable String contextFilePath,
                           @Nullable ProcessHandler coverageProcess) {
    super(name, project, coverageRunner, fileProvider, timestamp);
    myContextFilePath = contextFilePath;
    myCoverageProcess = coverageProcess;
  }

  @Override
  public @NotNull CoverageEngine getCoverageEngine() {
    return DartCoverageEngine.getInstance();
  }

  public @Nullable String getContextFilePath() {
    return myContextFilePath;
  }

  public @Nullable ProcessHandler getCoverageProcess() {
    return myCoverageProcess;
  }

  @Override
  public void writeExternal(final Element element) throws WriteExternalException {
    super.writeExternal(element);

    if (myContextFilePath != null) {
      element.setAttribute(CONTEXT_FILE_PATH, myContextFilePath);
    }
  }

  @Override
  public void readExternal(final Element element) throws InvalidDataException {
    super.readExternal(element);

    final String contextFilePath = element.getAttributeValue(CONTEXT_FILE_PATH);
    if (contextFilePath != null) {
      myContextFilePath = contextFilePath;
    }
  }
}
