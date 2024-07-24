package de.schwarzf.reichenau.intermediateCode;

public enum IntermediateCodeNodeKind {
    OPERAND,

    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    LOGICAL_AND,
    LOGICAL_OR,
    EQUALS,
    NOT_EQUALS,
    DIVISION,

    IDENTITY,
    NEGATION,
    LOGICAL_NEGATION;
}
