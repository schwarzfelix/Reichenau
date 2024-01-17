package de.schwarzf.boco.minsk;

import java.util.ArrayList;

public abstract class SyntaxNode {

    private SyntaxKind type;


    public SyntaxKind getType() {
        return type;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
