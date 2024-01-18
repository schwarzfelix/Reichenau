package de.schwarzf.boco.minsk.codeAnalysis;

import java.util.Arrays;

public final class UnaryExpressionSyntax extends ExpressionSyntax {

    private SyntaxToken operatorToken;
    private ExpressionSyntax operand;

    public UnaryExpressionSyntax(SyntaxToken operatorToken, ExpressionSyntax operand) {
        this.operatorToken = operatorToken;
        this.operand = operand;
    }

    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }

    public ExpressionSyntax getOperand() {
        return operand;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.UnaryExpression;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{operatorToken, operand});
    }
}
