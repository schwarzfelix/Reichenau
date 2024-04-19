package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.util.Arrays;

public class AssignmentExpressionSyntax extends ExpressionSyntax{

    private SyntaxToken identifierToken;
    private SyntaxToken equalsToken;
    private ExpressionSyntax expression;

    public AssignmentExpressionSyntax(SyntaxToken identifierToken, SyntaxToken equalsToken, ExpressionSyntax expression){
        this.identifierToken = identifierToken;
        this.equalsToken = equalsToken;
        this.expression = expression;
    }

    public SyntaxToken getIdentifierToken(){
        return identifierToken;
    }

    public SyntaxToken getEqualsToken() {
        return equalsToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.ASSIGNMENT_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{
                identifierToken,
                equalsToken,
                expression
        });
    }
}
