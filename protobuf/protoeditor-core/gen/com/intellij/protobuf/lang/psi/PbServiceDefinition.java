// This is a generated file. Not intended for manual editing.
package com.intellij.protobuf.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.protobuf.lang.stub.PbServiceDefinitionStub;

public interface PbServiceDefinition extends PbDefinition, PbNamedElement, PbSymbolOwner, StubBasedPsiElement<PbServiceDefinitionStub> {

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  PbServiceBody getBody();

}
