package dk.ave.classic_asp_support.language.psi;

import com.intellij.psi.tree.IElementType;
import dk.ave.classic_asp_support.language.ASPLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ASPTokenType extends IElementType {
    public ASPTokenType(@NotNull @NonNls String debugName) {
        super(debugName, ASPLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "ASPTokenType." + super.toString();
    }
}
