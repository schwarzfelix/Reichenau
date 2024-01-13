package de.schwarzf.boco.minsk;

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
    public SyntaxToken.TokenType getType() {
        return SyntaxToken.TokenType.NumberExpression;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        //return (Iterable<SyntaxNode>) numberToken;
        return Arrays.asList(new SyntaxToken[]{numberToken});
    }
}
