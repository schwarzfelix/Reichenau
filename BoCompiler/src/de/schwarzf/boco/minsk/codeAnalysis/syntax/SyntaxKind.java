package de.schwarzf.boco.minsk.codeAnalysis.syntax;

public enum SyntaxKind {

    //Tokens
    NUMBER_TOKEN,
    PLUS_TOKEN,
    MINUS_TOKEN,
    STAR_TOKEN,
    SLASH_TOKEN,
    BANG_TOKEN,
    AMPERSAND_AMPERSAND_TOKEN,
    PIPE_PIPE_TOKEN,
    OPEN_PARENTHESIS_TOKEN,
    CLOSE_PARENTHESIS_TOKEN,


    //Expressions
    UNARY_EXPRESSION,
    BINARY_EXPRESSION,
    LITERAL_EXPRESSION,
    PARENTHESIZED_EXPRESSION,

    //Keywords
    TRUE_KEYWORD,
    FALSE_KEYWORD,


    //Other
    IDENTIFIER_TOKEN,

    WHITESPACE,
    END_OF_FILE_TOKEN,

    BAD_TOKEN


}