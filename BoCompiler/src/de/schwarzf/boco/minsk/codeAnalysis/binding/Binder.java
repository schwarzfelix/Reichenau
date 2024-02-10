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
            case LiteralExpression:
                return bindLiteralExpression((LiteralExpressionSyntax)syntax);
            case UnaryExpression:
                return bindUnaryExpression((UnaryExpressionSyntax)syntax);
            case BinaryExpression:
                return bindBinaryExpression((BinaryExpressionSyntax)syntax);
            default:
                throw new IllegalArgumentException("Unexpected syntax: " + syntax.getKind());
        }

    }

    private BoundExpression bindLiteralExpression(LiteralExpressionSyntax syntax) {

        int value = syntax.getLiteralToken().getValue();

        if (syntax.getLiteralToken().getValue() instanceof Integer) {
            value = (int)syntax.getLiteralToken().getValue();
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
            throw new IllegalArgumentException("Unary operator cannot be applied to type " + operandType);
        }

        switch (kind) {
            case PlusToken:
                return BoundUnaryOperatorKind.IDENTITY;
            case MinusToken:
                return BoundUnaryOperatorKind.NEGATION;
            default:
                throw new IllegalArgumentException("Unexpected unary operator: " + kind);
        }
    }
    private BoundBinaryOperatorKind bindBinaryOperatorKind(SyntaxKind kind, Class<?> leftType, Class<?> rightType) {

        if (leftType != int.class || rightType != int.class) {
            throw new IllegalArgumentException("Binary operator cannot be applied to types " + leftType + " and " + rightType);
        }

        switch (kind) {
            case PlusToken:
                return BoundBinaryOperatorKind.ADDITION;
            case MinusToken:
                return BoundBinaryOperatorKind.SUBTRACTION;
            case StarToken:
                return BoundBinaryOperatorKind.MULTIPLICATION;
            case SlashToken:
                return BoundBinaryOperatorKind.DIVISION;
            default:
                throw new IllegalArgumentException("Unexpected binary operator: " + kind);
        }
    }
}
