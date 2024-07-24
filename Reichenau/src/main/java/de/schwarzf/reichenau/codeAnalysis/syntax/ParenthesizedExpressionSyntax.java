package de.schwarzf.reichenau.codeAnalysis.syntax;

import java.util.Arrays;

public final class ParenthesizedExpressionSyntax  extends ExpressionSyntax {
    public final SyntaxToken openParenthesisToken;
    public final ExpressionSyntax expression;
    public final SyntaxToken closeParenthesisToken;

    public ParenthesizedExpressionSyntax(SyntaxToken openParenthesisToken, ExpressionSyntax expression, SyntaxToken closeParenthesisToken) {
        this.openParenthesisToken = openParenthesisToken;
        this.expression = expression;
        this.closeParenthesisToken = closeParenthesisToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.PARENTHESIZED_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{openParenthesisToken, expression, closeParenthesisToken});
    }

    public SyntaxToken getOpenParenthesisToken() {
        return openParenthesisToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    public SyntaxToken getCloseParenthesisToken() {
        return closeParenthesisToken;
    }
}
