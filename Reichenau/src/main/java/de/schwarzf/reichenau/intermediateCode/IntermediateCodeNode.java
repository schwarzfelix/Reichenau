package de.schwarzf.reichenau.intermediateCode;

import de.schwarzf.reichenau.codeAnalysis.binding.BoundBinaryOperatorKind;
import de.schwarzf.reichenau.codeAnalysis.binding.BoundUnaryOperatorKind;

public abstract class IntermediateCodeNode {
    public abstract IntermediateCodeNodeKind getKind();
    public abstract String toString();

    public static IntermediateCodeNodeKind getIntermediateCodeKind(BoundBinaryOperatorKind kind) {
        return switch (kind) {
            case ADDITION -> IntermediateCodeNodeKind.ADDITION;
            case SUBTRACTION -> IntermediateCodeNodeKind.SUBTRACTION;
            case MULTIPLICATION -> IntermediateCodeNodeKind.MULTIPLICATION;
            case DIVISION -> IntermediateCodeNodeKind.DIVISION;
            case LOGICAL_AND -> IntermediateCodeNodeKind.LOGICAL_AND;
            case LOGICAL_OR -> IntermediateCodeNodeKind.LOGICAL_OR;
            case EQUALS -> IntermediateCodeNodeKind.EQUALS;
            case NOT_EQUALS -> IntermediateCodeNodeKind.NOT_EQUALS;
        };
    }

    public static IntermediateCodeNodeKind getIntermediateCodeKind(BoundUnaryOperatorKind kind) {
        return switch (kind) {
            case IDENTITY -> IntermediateCodeNodeKind.IDENTITY;
            case NEGATION -> IntermediateCodeNodeKind.NEGATION;
            case LOGICAL_NEGATION -> IntermediateCodeNodeKind.LOGICAL_NEGATION;
        };
    }
}
