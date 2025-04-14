package dk.ave.classic_asp_support.language;

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
import dk.ave.classic_asp_support.language.parser.ASPParser;
import dk.ave.classic_asp_support.language.psi.ASPTypes;
import org.jetbrains.annotations.NotNull;

public class ASPParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(ASPLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ASPLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new ASPParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return ASPTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return ASPTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new ASPFile(viewProvider);
    }
}
