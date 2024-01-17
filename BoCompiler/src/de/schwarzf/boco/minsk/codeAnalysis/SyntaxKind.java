package de.schwarzf.boco.minsk.codeAnalysis;

public enum SyntaxKind {

    //Tokens
    NumberToken,
    PlusToken,
    MinusToken,
    StarToken,
    SlashToken,

    //Expressions
    BinaryExpression,
    NumberExpression,
    OpenParenthesisToken,
    CloseParenthesisToken,

    //Other
    Whitespace,
    EndOfFileToken,
    ParenthesizedExpression, BadToken

}
