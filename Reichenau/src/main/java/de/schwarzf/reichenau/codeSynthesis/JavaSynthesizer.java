package de.schwarzf.reichenau.codeSynthesis;

import de.schwarzf.reichenau.codeAnalysis.binding.*;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;


public final class JavaSynthesizer extends Synthesizer {

    public JavaSynthesizer(SyntaxTree syntax) {
        super(syntax);
    }

    public String synthesize() {

        String prefix = """
import java.util.HashMap;
public class Export {
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

    private static String stringifyBinaryOperator(BoundBinaryOperator operator){
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

    private static String stringifyUnaryOperator(BoundUnaryOperator operator){
        return switch (operator.getKind()) {
            case IDENTITY -> "+";
            case NEGATION -> "-";
            case LOGICAL_NEGATION -> "!";
        };
    }

}
