package dk.ave.classic_asp_support.language.psi;

import com.intellij.psi.tree.IElementType;
import dk.ave.classic_asp_support.language.ASPLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class ASPElementType extends IElementType {
    public ASPElementType(@NonNls @NotNull String debugName) {
        super(debugName, ASPLanguage.INSTANCE);
    }
}
