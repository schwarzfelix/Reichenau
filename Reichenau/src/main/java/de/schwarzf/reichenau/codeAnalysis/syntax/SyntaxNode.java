package de.schwarzf.reichenau.codeAnalysis.syntax;

public abstract class SyntaxNode {

    private SyntaxKind kind;


    public SyntaxKind getKind() {
        return kind;
    }

    public abstract Iterable<SyntaxNode> getChildren();
}
