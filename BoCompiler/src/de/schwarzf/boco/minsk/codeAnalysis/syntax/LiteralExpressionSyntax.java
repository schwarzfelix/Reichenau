package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.util.Arrays;

public final class LiteralExpressionSyntax extends ExpressionSyntax {

    private SyntaxToken literalToken;

    public LiteralExpressionSyntax(SyntaxToken literalToken){
        this.literalToken = literalToken;
    }

    public SyntaxToken getLiteralToken(){
        return literalToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.LITERAL_EXPRESSION;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        //return (Iterable<SyntaxNode>) numberToken;
        return Arrays.asList(new SyntaxToken[]{literalToken});
    }
}
