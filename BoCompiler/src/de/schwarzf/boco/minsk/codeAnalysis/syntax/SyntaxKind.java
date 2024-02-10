package de.schwarzf.boco.minsk.codeAnalysis.syntax;

public enum SyntaxKind {

    //Tokens
    NUMBER_TOKEN,
    PLUS_TOKEN,
    MINUS_TOKEN,
    STAR_TOKEN,
    SLASH_TOKEN,
    OPEN_PARENTHESIS_TOKEN,
    CLOSE_PARENTHESIS_TOKEN,


    //Expressions
    UNARY_EXPRESSION,
    BINARY_EXPRESSION,
    LITERAL_EXPRESSION,
    PARENTHESIZED_EXPRESSION,


    //Other
    WHITESPACE,
    END_OF_FILE_TOKEN,

    BAD_TOKEN

}
