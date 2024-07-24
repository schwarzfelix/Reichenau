package de.schwarzf.reichenau.codeAnalysis.syntax;

import java.util.Arrays;

public final class BinaryExpressionSyntax extends ExpressionSyntax {

    private ExpressionSyntax left;
    private SyntaxToken operatorToken;
    private ExpressionSyntax right;

    public BinaryExpressionSyntax(ExpressionSyntax left, SyntaxToken operatorToken, ExpressionSyntax right) {
        this.left = left;
        this.operatorToken = operatorToken;
        this.right = right;
    }

    public ExpressionSyntax getLeft() {
        return left;
    }

    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }

    public ExpressionSyntax getRight() {
        return right;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.BINARY_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{left, operatorToken, right});
    }
}
