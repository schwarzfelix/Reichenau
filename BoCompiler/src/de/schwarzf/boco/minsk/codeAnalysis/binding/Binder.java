package de.schwarzf.boco.minsk.codeAnalysis.binding;

import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

import java.util.ArrayList;

public class Binder {

    private ArrayList<String> diagnostics = new ArrayList<>();

    public ArrayList<String> getDiagnostics() {
        return this.diagnostics;
    }

    public BoundExpression bindExpression(ExpressionSyntax syntax) {

        switch (syntax.getKind()) {
            case LITERAL_EXPRESSION:
                return bindLiteralExpression((LiteralExpressionSyntax)syntax);
            case UNARY_EXPRESSION:
                return bindUnaryExpression((UnaryExpressionSyntax)syntax);
            case BINARY_EXPRESSION:
                return bindBinaryExpression((BinaryExpressionSyntax)syntax);
            default:
                throw new IllegalArgumentException("Unexpected syntax: " + syntax.getKind());
        }

    }

    private BoundExpression bindLiteralExpression(LiteralExpressionSyntax syntax) {

        Object value;

        if (syntax.getValue() != null) {
            value = syntax.getValue();
        } else {
            value = 0;
        }

        return new BoundLiteralExpression(value);
    }

    private BoundExpression bindUnaryExpression(UnaryExpressionSyntax syntax) {

        BoundExpression boundOperand = bindExpression(syntax.getOperand());
        BoundUnaryOperatorKind boundOperatorKind = bindUnaryOperatorKind(syntax.getOperatorToken().getKind(), boundOperand.getType());

        if (boundOperatorKind == null) {
            diagnostics.add("Unary operator " + syntax.getOperatorToken().getText() + " is not defined for type " + boundOperand.getType() + ".");
            return boundOperand;
        }

        return new BoundUnaryExpression(boundOperatorKind, boundOperand);
    }

    private BoundExpression bindBinaryExpression(BinaryExpressionSyntax syntax) {

        BoundExpression boundLeft = bindExpression(syntax.getLeft());
        BoundExpression boundRight = bindExpression(syntax.getRight());
        BoundBinaryOperatorKind boundOperatorKind = bindBinaryOperatorKind(syntax.getOperatorToken().getKind(), boundLeft.getType(), boundRight.getType());

        if (boundOperatorKind == null) {
            diagnostics.add("Binary operator " + syntax.getOperatorToken().getText() + " is not defined for types " + boundLeft.getType() + " and " + boundRight.getType() + ".");
            return boundLeft;
        }

        return new BoundBinaryExpression(boundLeft, boundOperatorKind, boundRight);
    }

    private BoundUnaryOperatorKind bindUnaryOperatorKind(SyntaxKind kind, Class<?> operandType) {

        if (operandType != int.class) {
            diagnostics.add("Unary operator " + kind + " is not defined for type " + operandType + ".");
        }

        switch (kind) {
            case PLUS_TOKEN:
                return BoundUnaryOperatorKind.IDENTITY;
            case MINUS_TOKEN:
                return BoundUnaryOperatorKind.NEGATION;
            default:
                throw new IllegalArgumentException("Unexpected unary operator: " + kind);
        }
    }
    private BoundBinaryOperatorKind bindBinaryOperatorKind(SyntaxKind kind, Class<?> leftType, Class<?> rightType) {

        if (leftType != Integer.class || rightType != Integer.class) {
            diagnostics.add("Binary operator " + kind + " is not defined for types " + leftType + " and " + rightType + ".");
        }

        switch (kind) {
            case PLUS_TOKEN:
                return BoundBinaryOperatorKind.ADDITION;
            case MINUS_TOKEN:
                return BoundBinaryOperatorKind.SUBTRACTION;
            case STAR_TOKEN:
                return BoundBinaryOperatorKind.MULTIPLICATION;
            case SLASH_TOKEN:
                return BoundBinaryOperatorKind.DIVISION;
            default:
                throw new IllegalArgumentException("Unexpected binary operator: " + kind);
        }
    }
}
