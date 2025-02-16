// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.jetbrains.lang.dart.ide.index;

import com.intellij.util.indexing.*;
import com.intellij.util.io.EnumeratorStringDescriptor;
import com.intellij.util.io.KeyDescriptor;
import com.jetbrains.lang.dart.DartFileType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DartSymbolIndex extends ScalarIndexExtension<String> {
  public static final ID<String, Void> DART_SYMBOL_INDEX = ID.create("DartSymbolIndex");
  private final DataIndexer<String, Void, FileContent> myDataIndexer = new MyDataIndexer();

  @Override
  public @NotNull ID<String, Void> getName() {
    return DART_SYMBOL_INDEX;
  }

  @Override
  public int getVersion() {
    return DartIndexUtil.INDEX_VERSION;
  }

  @Override
  public @NotNull DataIndexer<String, Void, FileContent> getIndexer() {
    return myDataIndexer;
  }

  @Override
  public @NotNull KeyDescriptor<String> getKeyDescriptor() {
    return EnumeratorStringDescriptor.INSTANCE;
  }

  @Override
  public @NotNull FileBasedIndex.InputFilter getInputFilter() {
    return new DefaultFileTypeSpecificInputFilter(DartFileType.INSTANCE);
  }

  @Override
  public boolean dependsOnFileContent() {
    return true;
  }

  private static class MyDataIndexer implements DataIndexer<String, Void, FileContent> {
    @Override
    public @NotNull Map<String, Void> map(final @NotNull FileContent inputData) {
      List<String> symbols = DartIndexUtil.indexFile(inputData).getSymbols();
      final Map<String, Void> result = new HashMap<>();
      for (String symbol : symbols) {
        result.put(symbol, null);
      }
      return result;
    }
  }
}
