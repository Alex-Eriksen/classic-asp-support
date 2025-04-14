package dk.ave.classic_asp_support.language;

public class ASPLanguage extends com.intellij.lang.Language {

    public static final ASPLanguage INSTANCE = new ASPLanguage();

    private ASPLanguage() {
        super("ClassicASP");
    }
}
