package de.schwarzf.boco.minsk;

public abstract class SyntaxNode {

    private SyntaxToken.TokenType type;


    public SyntaxToken.TokenType getType() {
        return type;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
