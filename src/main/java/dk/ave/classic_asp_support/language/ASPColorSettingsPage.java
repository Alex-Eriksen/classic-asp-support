package dk.ave.classic_asp_support.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public final class ASPColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Keyword", ASPSyntaxHighlighter.KEY),
            new AttributesDescriptor("Code block", ASPSyntaxHighlighter.CODE_BLOCK),
            new AttributesDescriptor("Operators", ASPSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Class", ASPSyntaxHighlighter.INSTANCE),
            new AttributesDescriptor("Identifiers//String", ASPSyntaxHighlighter.STRING),
            new AttributesDescriptor("Identifiers//Variable", ASPSyntaxHighlighter.IDENTIFIER),
    };

    @Override
    public @Nullable Icon getIcon() {
        return ASPIcons.FILE;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new ASPSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText() {
        return """
                <%
                    dim httpObj, response
                    set httpObj = CreateObject("MSXML2.ServerXMLHTTP")
                    httpObj.Open "GET", "google.com", false
                    httpObj.send()
                    response = httpObj.responseText
                    set httpObjectg = nothing
                %>
                
                <script>
                    function abc() {
                        let test = <%=response%>
                    }
                </script>
                
                <div class="w-full" style="width: 100%;">
                    <%=response%>
                </div>
                """;
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return "Classic ASP Support";
    }
}
