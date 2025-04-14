package dk.ave.classic_asp_support.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ASPFileType extends LanguageFileType {
    public static final ASPFileType INSTANCE = new ASPFileType();

    private ASPFileType() {
        super(ASPLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Classic ASP File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Classic \"Active Server Pages\" file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "asp";
    }

    @Override
    public Icon getIcon() {
        return ASPIcons.FILE;
    }
}
