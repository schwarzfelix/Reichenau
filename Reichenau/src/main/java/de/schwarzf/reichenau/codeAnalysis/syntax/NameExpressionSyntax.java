package de.schwarzf.reichenau.codeAnalysis.syntax;

import java.util.Arrays;

public class NameExpressionSyntax extends ExpressionSyntax{
    private SyntaxToken identifierToken;

    public NameExpressionSyntax(SyntaxToken identifierToken){
        this.identifierToken = identifierToken;
    }

    public SyntaxToken getIdentifierToken(){
        return identifierToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NAME_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxNode[]{identifierToken});
    }
}
