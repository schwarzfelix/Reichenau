package de.schwarzf.boco.minsk.codeAnalysis;

import de.schwarzf.boco.minsk.codeAnalysis.binding.*;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

public final class Evaluator {
    BoundExpression root;
    public Evaluator(BoundExpression root) {
        this.root = root;
    }
    public int evaluate() {
        return evaluateExpression(this.root);
    }
    private int evaluateExpression(BoundExpression node) {

        if (node instanceof BoundLiteralExpression) {
            BoundLiteralExpression n = (BoundLiteralExpression) node;
            return (int) n.getValue();
        }

        if (node instanceof BoundUnaryExpression) {
            BoundUnaryExpression u = (BoundUnaryExpression) node;

            int operand = evaluateExpression(u.getOperand());

            switch (u.getOperatorKind()) {
                case IDENTITY:
                    return operand;
                case NEGATION:
                    return -operand;
                default:
                    throw new IllegalArgumentException("Unexpected unary operator <" + u.getOperatorKind() + ">");
            }


        }

        if (node instanceof BoundBinaryExpression) {
            BoundBinaryExpression b = (BoundBinaryExpression) node;

            int left = evaluateExpression(b.getLeft());
            int right = evaluateExpression(b.getRight());

            switch (b.getOperatorKind()) {
                case ADDITION:
                    return left + right;
                case SUBTRACTION:
                    return left - right;
                case MULTIPLICATION:
                    return left * right;
                case DIVISION:
                    return left / right;
                default:
                    throw new IllegalArgumentException("Unexpected binary operator <" + b.getOperatorKind() + ">");
            }
        }

        throw new IllegalArgumentException("Unexpected node <" + node.getKind() + ">");
    }
}
