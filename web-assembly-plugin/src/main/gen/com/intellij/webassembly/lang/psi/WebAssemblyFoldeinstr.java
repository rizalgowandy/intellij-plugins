// This is a generated file. Not intended for manual editing.
package com.intellij.webassembly.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface WebAssemblyFoldeinstr extends PsiElement {

  @Nullable
  WebAssemblyBlocktype getBlocktype();

  @NotNull
  List<WebAssemblyFoldeinstr> getFoldeinstrList();

  @NotNull
  List<WebAssemblyInstr> getInstrList();

  @Nullable
  WebAssemblyPlaininstr getPlaininstr();

}
