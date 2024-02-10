package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import de.schwarzf.boco.minsk.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxKind;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxNode;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxToken;

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
        return SyntaxKind.BinaryExpression;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{left, operatorToken, right});
    }
}
