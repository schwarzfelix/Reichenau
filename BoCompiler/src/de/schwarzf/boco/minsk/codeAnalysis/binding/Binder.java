package de.schwarzf.boco.minsk.codeAnalysis.binding;

import de.schwarzf.boco.minsk.codeAnalysis.DiagnosticBag;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

import java.util.ArrayList;
import java.util.Dictionary;

public class Binder {

    private Dictionary<String, Object> variables;
    private DiagnosticBag diagnostics = new DiagnosticBag();

    public Binder(Dictionary<String, Object> variables){
        this.variables = variables;
    }

    public DiagnosticBag getDiagnostics() {
        return this.diagnostics;
    }

    public BoundExpression bindExpression(ExpressionSyntax syntax) {

        switch (syntax.getKind()) {
            case PARENTHESIZED_EXPRESSION:
                return bindParenthesizedExpression((ParenthesizedExpressionSyntax)syntax);
            case LITERAL_EXPRESSION:
                return bindLiteralExpression((LiteralExpressionSyntax)syntax);
            case UNARY_EXPRESSION:
                return bindUnaryExpression((UnaryExpressionSyntax)syntax);
            case BINARY_EXPRESSION:
                return bindBinaryExpression((BinaryExpressionSyntax)syntax);
            case NAME_EXPRESSION:
                return bindNameExpression(((NameExpressionSyntax)syntax));
            case ASSIGNMENT_EXPRESSION:
                return bindAssignmentExpression((AssignmentExpressionSyntax)syntax);
            default:
                throw new IllegalArgumentException("Unexpected syntax: " + syntax.getKind());
        }

    }

    private BoundExpression bindAssignmentExpression(AssignmentExpressionSyntax syntax) {
        String name = syntax.getIdentifierToken().getText();
        BoundExpression boundExpression = bindExpression(syntax.getExpression());
        return new BoundAssignmentExpression(name, boundExpression);
    }

    private BoundExpression bindNameExpression(NameExpressionSyntax syntax) {
        String name = syntax.getIdentifierToken().getText();
        Object value = variables.get(name);

        if(value == null){
            diagnostics.reportUndefinedName(syntax.getIdentifierToken().getSpan(), name);
            return new BoundLiteralExpression(0);
        }

        //return new BoundVariableExpression(name, value.getClass());
        return new BoundVariableExpression(name, Integer.class);
    }

    private BoundExpression bindParenthesizedExpression(ParenthesizedExpressionSyntax syntax) {
        return bindExpression(syntax.getExpression());
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

