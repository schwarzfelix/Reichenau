package de.schwarzf.boco.minsk.codeAnalysis;

import de.schwarzf.boco.minsk.codeAnalysis.binding.*;
import java.util.HashMap;

public final class Evaluator {
    private BoundExpression root;
    private HashMap<VariableSymbol, Object> variables;

    public Evaluator(BoundExpression root, HashMap<VariableSymbol, Object> variables) {
        this.root = root;
        this.variables = variables;
    }
    public Object evaluate() {
        return evaluateExpression(this.root);
    }
    private Object evaluateExpression(BoundExpression node) {

        return switch (node.getKind()) {
            case LITERAL_EXPRESSION -> evaluateLiteralExpression((BoundLiteralExpression) node);
            case VARIABLE_EXPRESSION -> evaluateVariableExpression((BoundVariableExpression) node);
            case ASSIGNMENT_EXPRESSION -> evaluateAssignmentExpression((BoundAssignmentExpression) node);
            case UNARY_EXPRESSION -> evaluateUnaryExpression((BoundUnaryExpression) node);
            case BINARY_EXPRESSION -> evaluateBinaryExpression((BoundBinaryExpression) node);
        };

    }

    private static Object evaluateLiteralExpression(BoundLiteralExpression n) {
        return n.getValue();
    }

    private Object evaluateVariableExpression(BoundVariableExpression v) {
        return this.variables.get(v.getVariable());
    }

    private Object evaluateAssignmentExpression(BoundAssignmentExpression a) {
        Object value = evaluateExpression(a.getExpression());
        this.variables.put(a.getVariable(), value);
        return value;
    }

    private Object evaluateUnaryExpression(BoundUnaryExpression u) {
        Object operand = evaluateExpression(u.getOperand());
        switch (u.getOperator().getKind()) {
            case IDENTITY:
                return (int) operand;
            case NEGATION:
                return -(int) operand;
            case LOGICAL_NEGATION:
                return !(boolean) operand;
            default:
                throw new IllegalArgumentException("Unexpected unary operator <" + u.getOperator().getKind() + ">");
        }
    }

    private Object evaluateBinaryExpression(BoundBinaryExpression b) {
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
            case EQUALS:
                return left.equals(right);
            case NOT_EQUALS:
                return !left.equals(right);
            default:
                throw new IllegalArgumentException("Unexpected binary operator <" + b.getOperator().getKind() + ">");
        }
    }

}
