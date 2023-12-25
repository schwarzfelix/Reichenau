package de.schwarzf.boco.minsk;

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
}
