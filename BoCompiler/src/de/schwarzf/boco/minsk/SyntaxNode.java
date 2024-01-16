package de.schwarzf.boco.minsk;

public abstract class SyntaxNode {

    private SyntaxKind type;


    public SyntaxKind getType() {
        return type;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
