package de.schwarzf.reichenau.codeAnalysis.syntax;

import java.util.Arrays;

public final class NumberSyntax extends ExpressionSyntax {
    private SyntaxToken numberToken;
    public NumberSyntax(SyntaxToken token){
        this.numberToken = token;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NUMBER_TOKEN;
    }

    public SyntaxToken getNumberToken(){
        return numberToken;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Arrays.asList(new SyntaxToken[]{numberToken});
    }
}
