package dk.ave.classic_asp_support.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import dk.ave.classic_asp_support.language.psi.ASPTypes;

%%

%class ClassicASPLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state ASP_CODE

%%

/* ---------- HTML Mode (Default State) ---------- */
<YYINITIAL> {

  // Recognize the start of an ASP block: either <%= (echo) or <% (code)
  "<%="         { yybegin(ASP_CODE); return ASPTypes.ASP_ECHO_START; }
  "<%"          { yybegin(ASP_CODE); return ASPTypes.ASP_CODE_START; }

  // HTML Comment: start and end markers (simplified)
  "<!--"       { return ASPTypes.HTML_COMMENT_START; }
  "-->"         { return ASPTypes.HTML_COMMENT_END; }

  // HTML Tag (simplified regex; adjust as needed)
  "</?[a-zA-Z][a-zA-Z0-9]*(\\s+[^>]+)?>" { return ASPTypes.HTML_TAG; }

  // Any text that is not a tag
  [^<]+         { return ASPTypes.HTML_TEXT; }

  // A lone "<" character (edge case)
  "<"           { return ASPTypes.HTML_LESS_THAN; }
}

/* ---------- ASP Mode: Inside <% ... %> ---------- */
<ASP_CODE> {

  // End of the ASP block. Switch back to HTML mode.
  "%>"         { yybegin(YYINITIAL); return ASPTypes.ASP_CODE_END; }

  // Recognize some VBScript keywords (expand as needed)
  (I|i)(F|f)              { return ASPTypes.ASP_KEYWORD_IF; }
  (T|t)(H|h)(E|e)(N|n)    { return ASPTypes.ASP_KEYWORD_THEN; }
  (E|e)(L|l)(S|s)(E|e)    { return ASPTypes.ASP_KEYWORD_ELSE; }
  (E|e)(N|n)(D|d)         { return ASPTypes.ASP_KEYWORD_END; }
  (F|f)(O|o)(R|r)         { return ASPTypes.ASP_KEYWORD_FOR; }
  (N|n)(E|e)(X|x)(T|t)    { return ASPTypes.ASP_KEYWORD_NEXT; }
  (D|d)(I|i)(M|m)         { return ASPTypes.ASP_KEYWORD_DIM; }
  (S|s)(E|e)(T|t)         { return ASPTypes.ASP_KEYWORD_SET; }
  // Add more VBScript keywords as needed

  // Identifiers (variables, function names, etc.)
  [a-zA-Z_][a-zA-Z0-9_]*  { return ASPTypes.ASP_IDENTIFIER; }

  // Numeric literals (integer and floating point)
  [0-9]+(\\.[0-9]+)?      { return ASPTypes.ASP_NUMBER; }

  // String literals (double-quoted)
  \"([^\"\\]|\\.)*\"      { return ASPTypes.ASP_STRING; }

  // String literals (single-quoted)
  \'([^\'\\]|\\.)*\'      { return ASPTypes.ASP_STRING; }

  // Operators and punctuation
  "=="     { return ASPTypes.ASP_OPERATOR; }
  "!="     { return ASPTypes.ASP_OPERATOR; }
  "="      { return ASPTypes.ASP_OPERATOR; }
  "\\+"    { return ASPTypes.ASP_OPERATOR; }
  "-"      { return ASPTypes.ASP_OPERATOR; }
  "\\*"    { return ASPTypes.ASP_OPERATOR; }
  "/"      { return ASPTypes.ASP_OPERATOR; }
  "%"      { return ASPTypes.ASP_OPERATOR; }
  "\\("    { return ASPTypes.ASP_PARENTHESIS; }
  "\\)"    { return ASPTypes.ASP_PARENTHESIS; }
  ";"      { return ASPTypes.ASP_SEMICOLON; }
  ","      { return ASPTypes.ASP_COMMA; }
  "\\."    { return ASPTypes.ASP_DOT; }

  // Whitespace: skip spaces, tabs, newlines in ASP mode.
  [ \t\r\n]+ { /* skip whitespace */ }

  // Any other single character not matched above
  .      { return ASPTypes.ASP_OTHER; }
}