package dk.ave.classic_asp_support.language;

import com.intellij.psi.tree.TokenSet;
import dk.ave.classic_asp_support.language.psi.ASPTypes;

public interface ASPTokenSets {
    TokenSet IDENTIFIERS = TokenSet.create(ASPTypes.ASP_IDENTIFIER);

    TokenSet COMMENTS = TokenSet.create();
}
