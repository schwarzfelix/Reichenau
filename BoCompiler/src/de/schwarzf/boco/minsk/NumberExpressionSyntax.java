package de.schwarzf.boco.minsk;

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
        return SyntaxToken.TokenType.Number;
    }

}
