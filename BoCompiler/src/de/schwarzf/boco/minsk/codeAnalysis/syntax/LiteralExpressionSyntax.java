package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.util.Arrays;

public final class LiteralExpressionSyntax extends ExpressionSyntax {

    private SyntaxToken literalToken;
    private Object value;

    public LiteralExpressionSyntax(SyntaxToken literalToken, Object value){
        this.literalToken = literalToken;
        this.value = value;
    }

    public LiteralExpressionSyntax(SyntaxToken literalToken){
        this.literalToken = literalToken;
        this.value = literalToken.getValue();
    }

    public SyntaxToken getLiteralToken(){
        return literalToken;
    }

    public Object getValue(){
        return value;
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
