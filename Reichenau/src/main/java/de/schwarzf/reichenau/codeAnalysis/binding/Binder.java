package de.schwarzf.reichenau.codeAnalysis.binding;

import de.schwarzf.reichenau.codeAnalysis.DiagnosticBag;
import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import de.schwarzf.reichenau.codeAnalysis.syntax.*;

import java.util.HashMap;

public class Binder {

    private HashMap<VariableSymbol, Object> variables;
    private DiagnosticBag diagnostics = new DiagnosticBag();

    public Binder(HashMap<VariableSymbol, Object> variables){
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

        for (VariableSymbol v : variables.keySet()) {
            if (v.getName().equals(name)) {
                variables.remove(v);
                break;
            }
        }

        VariableSymbol variable = new VariableSymbol(name, boundExpression.getType());
        variables.put(variable, null);

        return new BoundAssignmentExpression(variable, boundExpression);
    }

    private BoundExpression bindNameExpression(NameExpressionSyntax syntax) {

        String name = syntax.getIdentifierToken().getText();

        VariableSymbol variable = null;
        for (VariableSymbol v : variables.keySet()) {
            if (v.getName().equals(name)) {
                variable = v;
                break;
            }
        }

        if(variable == null){
            diagnostics.reportUndefinedName(syntax.getIdentifierToken().getSpan(), name);
            return new BoundLiteralExpression(0);
        }

        return new BoundVariableExpression(variable);
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

