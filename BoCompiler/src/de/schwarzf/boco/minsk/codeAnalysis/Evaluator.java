package de.schwarzf.boco.minsk.codeAnalysis;

import de.schwarzf.boco.minsk.codeAnalysis.binding.*;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

public final class Evaluator {
    BoundExpression root;
    public Evaluator(BoundExpression root) {
        this.root = root;
    }
    public Object evaluate() {
        return evaluateExpression(this.root);
    }
    private Object evaluateExpression(BoundExpression node) {

        if (node instanceof BoundLiteralExpression) {
            BoundLiteralExpression n = (BoundLiteralExpression) node;
            return n.getValue();
        }

        if (node instanceof BoundUnaryExpression) {
            BoundUnaryExpression u = (BoundUnaryExpression) node;

            Object operand = evaluateExpression(u.getOperand());

            switch (u.getOperator().getKind()) {
                case IDENTITY:
                    return (int) operand;
                case NEGATION:
                    return - (int) operand;
                case LOGICAL_NEGATION:
                    return !(boolean) operand;
                default:
                    throw new IllegalArgumentException("Unexpected unary operator <" + u.getOperator().getKind() + ">");
            }


        }

        if (node instanceof BoundBinaryExpression) {
            BoundBinaryExpression b = (BoundBinaryExpression) node;

            Object left = evaluateExpression(b.getLeft());
            Object right = evaluateExpression(b.getRight());

            switch (b.getOperator().getKind()) {
                case ADDITION:
                    return (int) left + (int) right;
                case SUBTRACTION:
                    return (int) left - (int) right;
                case MULTIPLICATION:
                    return (int) left * (int) right;
                case DIVISION:
                    return (int) left / (int) right;
                case LOGICAL_AND:
                    return (boolean) left && (boolean) right;
                case LOGICAL_OR:
                    return (boolean) left || (boolean) right;
                default:
                    throw new IllegalArgumentException("Unexpected binary operator <" + b.getOperator().getKind() + ">");
            }
        }

        throw new IllegalArgumentException("Unexpected node <" + node.getKind() + ">");
    }
}
