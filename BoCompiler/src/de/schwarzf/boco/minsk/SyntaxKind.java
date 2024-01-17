package de.schwarzf.boco.minsk;

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
