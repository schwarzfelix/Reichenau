package de.schwarzf.boco.minsk.codeAnalysis.binding;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
            diagnostics.add("BINDER ERROR: Unary operator " + syntax.getOperatorToken().getText() + " is not defined for type " + boundOperand.getType() + ".");
            return boundOperand;
        }

        return new BoundUnaryExpression(boundOperator, boundOperand);
    }

    private BoundExpression bindBinaryExpression(BinaryExpressionSyntax syntax) {

        BoundExpression boundLeft = bindExpression(syntax.getLeft());
        BoundExpression boundRight = bindExpression(syntax.getRight());
        BoundBinaryOperator boundOperator = BoundBinaryOperator.bind(syntax.getOperatorToken().getKind(), boundLeft.getType(), boundRight.getType());

        if (boundOperator == null) {
            diagnostics.add("BINDER ERROR: Binary operator " + syntax.getOperatorToken().getText() + " is not defined for types " + boundLeft.getType() + " and " + boundRight.getType() + ".");
            return boundLeft;
        }

        return new BoundBinaryExpression(boundLeft, boundOperator, boundRight);
    }

}

