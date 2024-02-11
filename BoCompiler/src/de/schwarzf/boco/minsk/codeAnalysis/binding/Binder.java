package de.schwarzf.boco.minsk.codeAnalysis.binding;

import de.schwarzf.boco.minsk.codeAnalysis.DiagnosticBag;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

import java.util.ArrayList;

public class Binder {

    private DiagnosticBag diagnostics = new DiagnosticBag();

    public DiagnosticBag getDiagnostics() {
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
            case PARENTHESIZED_EXPRESSION:
                return bindExpression(((ParenthesizedExpressionSyntax)syntax).getExpression());
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
        BoundUnaryOperator boundOperator = BoundUnaryOperator.bind(syntax.getOperatorToken().getKind(), boundOperand.getType());

        if (boundOperator == null) {
            diagnostics.reportUndefinedUnaryOperator(syntax.getOperatorToken().getSpan(), syntax.getOperatorToken().getText(), boundOperand.getType());
            return boundOperand;
        }

        return new BoundUnaryExpression(boundOperator, boundOperand);
    }

    private BoundExpression bindBinaryExpression(BinaryExpressionSyntax syntax) {

        BoundExpression boundLeft = bindExpression(syntax.getLeft());
        BoundExpression boundRight = bindExpression(syntax.getRight());
        BoundBinaryOperator boundOperator = BoundBinaryOperator.bind(syntax.getOperatorToken().getKind(), boundLeft.getType(), boundRight.getType());

        if (boundOperator == null) {
            diagnostics.reportUndefinedBinaryOperator(syntax.getOperatorToken().getSpan(), syntax.getOperatorToken().getText(), boundLeft.getType(), boundRight.getType());
            return boundLeft;
        }

        return new BoundBinaryExpression(boundLeft, boundOperator, boundRight);
    }

}

