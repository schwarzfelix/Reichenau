package de.schwarzf.reichenau.intermediateCode;

import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import de.schwarzf.reichenau.codeAnalysis.binding.*;
import de.schwarzf.reichenau.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class IntermediateCodeGenerator {

    SyntaxTree syntaxTree;
    BoundExpression rootBoundExpression;

    public IntermediateCodeGenerator(SyntaxTree syntaxTree) {
        this.syntaxTree = syntaxTree;
        Binder binder = new Binder(new HashMap<VariableSymbol, Object>());
        rootBoundExpression = binder.bindExpression((ExpressionSyntax) syntaxTree.getRoot());
    }

    public LinkedList<IntermediateCodeNode> generateIntermediateCode() {
        return generateExpression(rootBoundExpression);
    }

    public LinkedList<IntermediateCodeNode> generateExpression(BoundExpression boundNode) {
        return switch (boundNode.getKind()) {
            case LITERAL_EXPRESSION -> generateLiteralExpression((BoundLiteralExpression) boundNode);
            //case VARIABLE_EXPRESSION -> generateVariableExpression((BoundVariableExpression) node);
            //case ASSIGNMENT_EXPRESSION -> generateAssignmentExpression((BoundAssignmentExpression) node);
            case UNARY_EXPRESSION -> generateUnaryExpression((BoundUnaryExpression) boundNode);
            case BINARY_EXPRESSION -> generateBinaryExpression((BoundBinaryExpression) boundNode);
            default -> throw new IllegalArgumentException("Unexpected value: " + boundNode.getKind());
        };
    }

    private LinkedList<IntermediateCodeNode> generateLiteralExpression(BoundLiteralExpression boundNode) {
        LinkedList<IntermediateCodeNode> intermediateCodeNodes = new LinkedList<>();
        intermediateCodeNodes.add(new IntermediateCodeOperand(boundNode.getValue()));
        return intermediateCodeNodes;
    }

    private LinkedList<IntermediateCodeNode> generateBinaryExpression(BoundBinaryExpression boundNode) {
        LinkedList<IntermediateCodeNode> intermediateCodeNodes = new LinkedList<>();
        intermediateCodeNodes.addAll(generateExpression(boundNode.getLeft()));
        intermediateCodeNodes.addAll(generateExpression(boundNode.getRight()));
        intermediateCodeNodes.add(new IntermediateCodeOperator(IntermediateCodeNode.getIntermediateCodeKind(boundNode.getOperator().getKind())));
        return intermediateCodeNodes;
    }

    private LinkedList<IntermediateCodeNode> generateUnaryExpression(BoundUnaryExpression boundNode) {
        LinkedList<IntermediateCodeNode> intermediateCodeNodes = new LinkedList<>();
        intermediateCodeNodes.addAll(generateExpression(boundNode.getOperand()));
        intermediateCodeNodes.add(new IntermediateCodeOperator(IntermediateCodeNode.getIntermediateCodeKind(boundNode.getOperator().getKind())));
        return intermediateCodeNodes;
    }
}
