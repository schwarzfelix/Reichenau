package de.schwarzf.boco.synthesis;

import de.schwarzf.boco.minsk.codeAnalysis.Diagnostic;
import de.schwarzf.boco.minsk.codeAnalysis.Evaluator;
import de.schwarzf.boco.minsk.codeAnalysis.VariableSymbol;
import de.schwarzf.boco.minsk.codeAnalysis.binding.*;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxTree;

import java.util.ArrayList;
import java.util.HashMap;

import static de.schwarzf.boco.minsk.codeAnalysis.binding.BoundBinaryOperatorKind.*;


public class Synthesizer {

    private BoundExpression root;

    public Synthesizer(SyntaxTree syntax) {
        Binder binder = new Binder(new HashMap<VariableSymbol, Object>());
        root = binder.bindExpression((ExpressionSyntax) syntax.getRoot());
    }

    public String synthesize() {

        String prefix = """
import java.util.HashMap;
public class Main {
    public static void main(String[] args) {
        //HashMap<String, Object> variables;
        System.out.println(
""";
        String suffix = """
                ) ; } }
""";

        return prefix + synthesizeExpression(this.root) + suffix;
    }

    private String synthesizeExpression(BoundExpression node) {
        return switch (node.getKind()) {
            case BoundNodeKind.LITERAL_EXPRESSION -> synthesizeLiteralExpression((BoundLiteralExpression) node);
            //case BoundNodeKind.VARIABLE_EXPRESSION -> synthesizeVariableExpression((BoundVariableExpression) node);
            //case ASSIGNMENT_EXPRESSION -> evaluateAssignmentExpression((BoundAssignmentExpression) node);
            case BoundNodeKind.UNARY_EXPRESSION -> synthesizeUnaryExpression((BoundUnaryExpression) node);
            case BoundNodeKind.BINARY_EXPRESSION -> synthesizeBinaryExpression((BoundBinaryExpression) node);
            default -> throw new IllegalStateException("Synthesizer - not yet implemented: " + node.getKind());
        };
    }

    private String synthesizeLiteralExpression(BoundLiteralExpression node) {
        return node.getValue().toString();
    }

    private String synthesizeBinaryExpression(BoundBinaryExpression node) {
        return synthesizeExpression(node.getLeft()) + " " + stringifyBinaryOperator(node.getOperator()) + " " + synthesizeExpression(node.getRight());
    }

    private String synthesizeUnaryExpression(BoundUnaryExpression node) {
        return stringifyUnaryOperator(node.getOperator()) + synthesizeExpression(node.getOperand());
    }

    public static String stringifyBinaryOperator(BoundBinaryOperator operator){
        return switch (operator.getKind()) {
            case ADDITION -> "+";
            case SUBTRACTION -> "-";
            case MULTIPLICATION -> "*";
            case DIVISION -> "/";
            case LOGICAL_AND -> "&&";
            case LOGICAL_OR -> "||";
            case EQUALS -> "==";
            case NOT_EQUALS -> "!=";
        };
    }

    public static String stringifyUnaryOperator(BoundUnaryOperator operator){
        return switch (operator.getKind()) {
            case IDENTITY -> "+";
            case NEGATION -> "-";
            case LOGICAL_NEGATION -> "!";
        };
    }

}
