package de.schwarzf.boco.minsk;

import java.util.ArrayList;

public final class SyntaxTree {
    private SyntaxNode root;
    private SyntaxToken endOfFileToken;
    private String[] diagnostics;
    public SyntaxTree(ArrayList<String> diagonstics, SyntaxNode root, SyntaxToken endOfFileToken) {
        this.root = root;
        this.endOfFileToken = endOfFileToken;
        this.diagnostics = new String[diagonstics.size()];
        this.diagnostics = diagonstics.toArray(this.diagnostics);
    }
    public SyntaxNode getRoot() {
        return root;
    }
    public SyntaxToken getEndOfFileToken() {
        return endOfFileToken;
    }
    public String[] getDiagnostics() {
        return diagnostics;
    }
}
