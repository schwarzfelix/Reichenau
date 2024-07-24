package de.schwarzf.reichenau.intermediateCode;

import de.schwarzf.reichenau.codeAnalysis.binding.BoundBinaryOperatorKind;
import de.schwarzf.reichenau.codeAnalysis.binding.BoundUnaryOperatorKind;

public abstract class IntermediateCodeNode {
    public abstract IntermediateCodeKind getKind();
    public abstract String toString();

    public static IntermediateCodeKind getIntermediateCodeKind(BoundBinaryOperatorKind kind) {
        return switch (kind) {
            case ADDITION -> IntermediateCodeKind.ADDITION;
            case SUBTRACTION -> IntermediateCodeKind.SUBTRACTION;
            case MULTIPLICATION -> IntermediateCodeKind.MULTIPLICATION;
            case DIVISION -> IntermediateCodeKind.DIVISION;
            case LOGICAL_AND -> IntermediateCodeKind.LOGICAL_AND;
            case LOGICAL_OR -> IntermediateCodeKind.LOGICAL_OR;
            case EQUALS -> IntermediateCodeKind.EQUALS;
            case NOT_EQUALS -> IntermediateCodeKind.NOT_EQUALS;
        };
    }

    public static IntermediateCodeKind getIntermediateCodeKind(BoundUnaryOperatorKind kind) {
        return switch (kind) {
            case IDENTITY -> IntermediateCodeKind.IDENTITY;
            case NEGATION -> IntermediateCodeKind.NEGATION;
            case LOGICAL_NEGATION -> IntermediateCodeKind.LOGICAL_NEGATION;
        };
    }
}
