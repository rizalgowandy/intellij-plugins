/*
 * Copyright (C) 2020 ThoughtWorks, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.thoughtworks.gauge.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.thoughtworks.gauge.language.SpecFile;
import com.thoughtworks.gauge.language.SpecTokenSets;
import com.thoughtworks.gauge.language.Specification;
import com.thoughtworks.gauge.language.token.SpecTokenTypes;
import com.thoughtworks.gauge.lexer.SpecLexer;
import org.jetbrains.annotations.NotNull;

public final class SpecParserDefinition implements ParserDefinition {
  public static final IFileElementType FILE = new IFileElementType(Specification.INSTANCE);

  @Override
  public @NotNull Lexer createLexer(Project project) {
    return new SpecLexer();
  }

  @Override
  public @NotNull TokenSet getWhitespaceTokens() {
    return SpecTokenSets.WHITE_SPACES;
  }

  @Override
  public @NotNull TokenSet getCommentTokens() {
    return SpecTokenSets.COMMENTS;
  }

  @Override
  public @NotNull TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @Override
  public @NotNull PsiParser createParser(Project project) {
    return new SpecParser();
  }

  @Override
  public @NotNull IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
    return new SpecFile(viewProvider);
  }

  @Override
  public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

  @Override
  public @NotNull PsiElement createElement(ASTNode node) {
    return SpecTokenTypes.Factory.createElement(node);
  }
}
