package dk.ave.classic_asp_support.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import dk.ave.classic_asp_support.language.psi.ASPTypes;
import org.jetbrains.annotations.NotNull;

public class ASPSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey CODE_BLOCK = TextAttributesKey.createTextAttributesKey("ASP_BLOCK", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey KEY = TextAttributesKey.createTextAttributesKey("ASP_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey OPERATOR = TextAttributesKey.createTextAttributesKey("ASP_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey("ASP_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey("ASP_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("ASP_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey INSTANCE = TextAttributesKey.createTextAttributesKey("ASP_INSTANCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    public static final TextAttributesKey PROCEDURE = TextAttributesKey.createTextAttributesKey("ASP_PROCEDURE", DefaultLanguageHighlighterColors.INSTANCE_METHOD);

    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] CODE_KEYS = new TextAttributesKey[]{CODE_BLOCK};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] INSTANCES_KEYS = new TextAttributesKey[]{INSTANCE};
    private static final TextAttributesKey[] PROCEDURES_KEYS = new TextAttributesKey[]{PROCEDURE};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new ASPLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if(
            tokenType.equals(ASPTypes.ASP_KEYWORD_DIM) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_SET) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_IF) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_ELSE) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_END) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_TO) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_NEXT) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_FOR) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_WHILE) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_WEND) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_THEN) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_FUNCTION) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_SUB) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_CALL) ||
            tokenType.equals(ASPTypes.ASP_KEYWORD_NEW)
        ) {
            return KEY_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_STRING)) {
            return STRING_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_IDENTIFIER)) {
            return IDENTIFIER_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_INSTANCE)) {
            return INSTANCES_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_FUNCTION_NAME)) {
            return PROCEDURES_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_CODE_START) || tokenType.equals(ASPTypes.ASP_CODE_END) || tokenType.equals(ASPTypes.ASP_ECHO_START)) {
            return CODE_KEYS;
        }
        if(
            tokenType.equals(ASPTypes.ASP_OPERATOR) ||
            tokenType.equals(ASPTypes.ASP_OPERATOR_ASSIGNMENT) ||
            tokenType.equals(ASPTypes.ASP_LOGICAL_OPERATOR)
        ) {
            return OPERATOR_KEYS;
        }
        if(tokenType.equals(ASPTypes.ASP_COMMENT)) {
            return COMMENT_KEYS;
        }
        return EMPTY_KEYS;
    }
}
