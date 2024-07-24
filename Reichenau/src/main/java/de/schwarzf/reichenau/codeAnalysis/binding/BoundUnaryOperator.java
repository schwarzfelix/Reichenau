package de.schwarzf.reichenau.codeAnalysis.binding;

import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxKind;

public final class BoundUnaryOperator {

    public final SyntaxKind syntaxKind;
    public final BoundUnaryOperatorKind kind;
    public final Class<?> operandType;
    public final Class<?> resultType;

    public BoundUnaryOperator(SyntaxKind syntaxKind, BoundUnaryOperatorKind kind, Class<?> operandType, Class<?> resultType) {
        this.syntaxKind = syntaxKind;
        this.kind = kind;
        this.operandType = operandType;
        this.resultType = resultType;
    }

    public BoundUnaryOperator(SyntaxKind syntaxKind, BoundUnaryOperatorKind kind, Class<?> operandType) {
        this(syntaxKind, kind, operandType, operandType);
    }

    private static BoundUnaryOperator[] operators = {
        new BoundUnaryOperator(SyntaxKind.BANG_TOKEN,  BoundUnaryOperatorKind.LOGICAL_NEGATION, Boolean.class),
        new BoundUnaryOperator(SyntaxKind.PLUS_TOKEN,  BoundUnaryOperatorKind.IDENTITY,         Integer.class),
        new BoundUnaryOperator(SyntaxKind.MINUS_TOKEN, BoundUnaryOperatorKind.NEGATION,         Integer.class),
    };

    public static BoundUnaryOperator bind(SyntaxKind syntaxKind, Class<?> operandType) {
        for (BoundUnaryOperator op : operators) {
            if (op.syntaxKind == syntaxKind && op.operandType == operandType) {
                return op;
            }
        }
        return null;
    }

    public SyntaxKind getSyntaxKind() {
        return syntaxKind;
    }

    public BoundUnaryOperatorKind getKind() {
        return kind;
    }

    public Class<?> getOperandType() {
        return operandType;
    }

    public Class<?> getType() {
        return resultType;
    }

    public static BoundUnaryOperator[] getOperators() {
        return operators;
    }
}
