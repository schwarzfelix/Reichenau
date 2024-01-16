package de.schwarzf.boco.minsk;

public abstract class SyntaxNode {

    private SyntaxToken.SyntaxKind type;


    public SyntaxToken.SyntaxKind getType() {
        return type;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
