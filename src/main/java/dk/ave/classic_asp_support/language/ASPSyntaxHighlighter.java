package dk.ave.classic_asp_support.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import dk.ave.classic_asp_support.language.psi.ASPTypes;
import org.jetbrains.annotations.NotNull;

public class ASPSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey CODE_START = TextAttributesKey.createTextAttributesKey("ASP_CODE_START", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey KEY = TextAttributesKey.createTextAttributesKey("ASP_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ASPLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if(tokenType.equals(ASPTypes.ASP_KEYWORD_DIM) || tokenType.equals(ASPTypes.ASP_KEYWORD_SET)) {
            return KEY_KEYS;
        }
        return EMPTY_KEYS;
    }
}
