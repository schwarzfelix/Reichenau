package de.schwarzf.boco.minsk.codeAnalysis;

import java.util.Arrays;

public final class NumberExpressionSyntax extends ExpressionSyntax {

    private SyntaxToken numberToken;

    public NumberExpressionSyntax(SyntaxToken numberToken){
        this.numberToken = numberToken;
    }

    public SyntaxToken getNumberToken(){
        return numberToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NumberExpression;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        //return (Iterable<SyntaxNode>) numberToken;
        return Arrays.asList(new SyntaxToken[]{numberToken});
    }
}
