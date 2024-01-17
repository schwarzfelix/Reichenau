package de.schwarzf.boco.minsk;

import java.util.ArrayList;

public abstract class SyntaxNode {

    private SyntaxKind kind;


    public SyntaxKind getKind() {
        return kind;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
