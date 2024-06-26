// This is a generated file. Not intended for manual editing.
package org.intellij.prisma.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.prisma.lang.psi.PrismaElementTypes.*;
import org.intellij.prisma.lang.psi.*;
import org.intellij.prisma.lang.psi.stubs.PrismaTypeAliasStub;
import com.intellij.psi.stubs.IStubElementType;

public class PrismaTypeAliasImpl extends PrismaTypeAliasMixin implements PrismaTypeAlias {

  public PrismaTypeAliasImpl(@NotNull ASTNode node) {
    super(node);
  }

  public PrismaTypeAliasImpl(@NotNull PrismaTypeAliasStub stub, @NotNull IStubElementType<?, ?> type) {
    super(stub, type);
  }

  public void accept(@NotNull PrismaVisitor visitor) {
    visitor.visitTypeAlias(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PrismaVisitor) accept((PrismaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<PrismaFieldAttribute> getFieldAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, PrismaFieldAttribute.class);
  }

  @Override
  @Nullable
  public PrismaFieldType getFieldType() {
    return PsiTreeUtil.getChildOfType(this, PrismaFieldType.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return notNullChild(findChildByType(IDENTIFIER));
  }

}
