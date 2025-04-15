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
%state NEW_INSTANCE
%state FUNCTION

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

    \'[^\r\n]*      { return ASPTypes.ASP_COMMENT; }

  // Keywords
  (I|i)(F|f)                                 { return ASPTypes.ASP_KEYWORD_IF; }
  (T|t)(H|h)(E|e)(N|n)                       { return ASPTypes.ASP_KEYWORD_THEN; }
  (E|e)(L|l)(S|s)(E|e)                       { return ASPTypes.ASP_KEYWORD_ELSE; }
  (E|e)(N|n)(D|d)                            { return ASPTypes.ASP_KEYWORD_END; }
  (F|f)(O|o)(R|r)                            { return ASPTypes.ASP_KEYWORD_FOR; }
  (N|n)(E|e)(X|x)(T|t)                       { return ASPTypes.ASP_KEYWORD_NEXT; }
  (D|d)(I|i)(M|m)                            { return ASPTypes.ASP_KEYWORD_DIM; }
  (S|s)(E|e)(T|t)                            { return ASPTypes.ASP_KEYWORD_SET; }
  (W|w)(H|h)(I|i)(L|l)(E|e)                  { return ASPTypes.ASP_KEYWORD_WHILE; }
  (W|w)(E|e)(N|n)(D|d)                       { return ASPTypes.ASP_KEYWORD_WEND; }
  (F|f)(U|u)(N|n)(C|c)(T|t)(I|i)(O|o)(N|n)   {
          yybegin(FUNCTION);
          return ASPTypes.ASP_KEYWORD_FUNCTION;
  }
  (S|s)(U|u)(B|b)                            { return ASPTypes.ASP_KEYWORD_SUB; }
  (C|c)(A|a)(L|l)(L|l)                       { return ASPTypes.ASP_KEYWORD_CALL; }
  (N|n)(E|e)(W|w) {
          yybegin(NEW_INSTANCE);
          return ASPTypes.ASP_KEYWORD_NEW;
  }


  "(" { return ASPTypes.ASP_LPAREN; }
  ")" { return ASPTypes.ASP_RPAREN; }
  "," { return ASPTypes.ASP_COMMA; }

      // Arithmetic operators
      "+"      { return ASPTypes.ASP_OPERATOR; }
      "-"      { return ASPTypes.ASP_OPERATOR; }
      "*"      { return ASPTypes.ASP_OPERATOR; }
      "/"      { return ASPTypes.ASP_OPERATOR; }
      "%"      { return ASPTypes.ASP_OPERATOR; }
      "^"      { return ASPTypes.ASP_OPERATOR; }

      // Comparison operators
      "="      { return ASPTypes.ASP_OPERATOR_ASSIGNMENT; }
      "<>"     { return ASPTypes.ASP_OPERATOR; }
      ">"      { return ASPTypes.ASP_OPERATOR; }
      "<"      { return ASPTypes.ASP_OPERATOR; }
      "<="     { return ASPTypes.ASP_OPERATOR; }
      ">="     { return ASPTypes.ASP_OPERATOR; }

      // Logical operators
      (A|a)(N|n)(D|d)     { return ASPTypes.ASP_LOGICAL_OPERATOR; }
      (O|o)(R|r)          { return ASPTypes.ASP_LOGICAL_OPERATOR; }
      (N|n)(O|o)(T|t)     { return ASPTypes.ASP_LOGICAL_OPERATOR; }
      (X|x)(O|o)(R|r)     { return ASPTypes.ASP_LOGICAL_OPERATOR; }

      // Concatination operators
      "&"      { return ASPTypes.ASP_OPERATOR; }

    // Numeric literals (integer and floating point)
    [0-9]+(\.[0-9]+)?      { return ASPTypes.ASP_NUMBER; }

  // Identifiers (variables, function names, etc.)
  [a-zA-Z_][a-zA-Z0-9_]*  { return ASPTypes.ASP_IDENTIFIER; }


    // String literals (double-quoted)
    \"[^\"\r\n]*\"      { return ASPTypes.ASP_STRING; }

    // Comment

  <NEW_INSTANCE> {
      \r?\n {
          yybegin(ASP_CODE);
      }

      [ \t]+    { /* skip whitespace */ }

      [a-zA-Z_][a-zA-Z0-9_]* {
        yybegin(ASP_CODE);
        return ASPTypes.ASP_INSTANCE;
      }

      . {
          yybegin(ASP_CODE);
          return ASPTypes.ASP_OTHER;
      }
  }

  <FUNCTION> {
    \r?\n {
      yybegin(ASP_CODE);
    }

    [ \t]+    { /* skip whitespace */ }

    [a-zA-Z_][a-zA-Z0-9_]* {
        yybegin(ASP_CODE);
        return ASPTypes.ASP_FUNCTION_NAME;
    }

    . {
      yybegin(ASP_CODE);
      return ASPTypes.ASP_OTHER;
    }
  }

  // Whitespace: skip spaces, tabs, newlines in ASP mode.
  [ \t\r\n]+ { /* skip whitespace */ }

  // Any other single character not matched above
    .   { return ASPTypes.ASP_OTHER; }
}