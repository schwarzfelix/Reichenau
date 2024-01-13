package de.schwarzf.boco.minsk;

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
    public SyntaxToken.TokenType getType() {
        return SyntaxToken.TokenType.BinaryExpression;
    }

    @Override
    public String toString() {
        return "BinaryExpressionSyntax{" +
                "left=" + left +
                ", operatorToken=" + operatorToken +
                ", right=" + right +
                '}';
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        // return list of left, operator and right
        return Arrays.asList(new SyntaxNode[]{left, operatorToken, right});
    }
}
