package de.schwarzf.boco.minsk;

public final class NumberSyntax extends ExpressionSyntax{
    private SyntaxToken numberToken;
    public NumberSyntax(SyntaxToken token){
        this.numberToken = token;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NumberToken;
    }

    public SyntaxToken getNumberToken(){
        return numberToken;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return (Iterable<SyntaxNode>) numberToken;
        //generated code, not checked from source
    }
}
