package dk.ave.classic_asp_support.language;

import com.intellij.lexer.FlexAdapter;

public class ASPLexerAdapter extends FlexAdapter {
    public ASPLexerAdapter() {
        super(new ClassicASPLexer(null));
    }
}
