package de.schwarzf.reichenau.intermediateCode;

public enum IntermediateCodeNodeKind {
    OPERAND(IntermediateCodeOperatorKind.OPERAND),

    ADDITION(IntermediateCodeOperatorKind.BINARY),
    SUBTRACTION(IntermediateCodeOperatorKind.BINARY),
    MULTIPLICATION(IntermediateCodeOperatorKind.BINARY),
    LOGICAL_AND(IntermediateCodeOperatorKind.BINARY),
    LOGICAL_OR(IntermediateCodeOperatorKind.BINARY),
    EQUALS(IntermediateCodeOperatorKind.BINARY),
    NOT_EQUALS(IntermediateCodeOperatorKind.BINARY),
    DIVISION(IntermediateCodeOperatorKind.BINARY),

    IDENTITY(IntermediateCodeOperatorKind.UNARY),
    NEGATION(IntermediateCodeOperatorKind.UNARY),
    LOGICAL_NEGATION(IntermediateCodeOperatorKind.UNARY);

    private final IntermediateCodeOperatorKind intermediateCodeOperatorKind;

    IntermediateCodeNodeKind(IntermediateCodeOperatorKind intermediateCodeOperatorKind) {
        this.intermediateCodeOperatorKind = intermediateCodeOperatorKind;
    }

    public IntermediateCodeOperatorKind getIntermediateCodeOperatorKind() {
        return this.intermediateCodeOperatorKind;
    }
}
