package de.schwarzf.boco.minsk.codeAnalysis;

public enum SyntaxKind {

    //Tokens
    NumberToken,
    PlusToken,
    MinusToken,
    StarToken,
    SlashToken,
    OpenParenthesisToken,
    CloseParenthesisToken,


    //Expressions
    UnaryExpression,
    BinaryExpression,
    LiteralExpression,
    ParenthesizedExpression,


    //Other
    Whitespace,
    EndOfFileToken,

    BadToken

}
