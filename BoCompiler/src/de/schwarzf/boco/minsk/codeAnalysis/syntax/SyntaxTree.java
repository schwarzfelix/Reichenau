package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.util.ArrayList;
import java.util.Collection;

public final class SyntaxTree {
    private SyntaxNode root;
    private SyntaxToken endOfFileToken;
    private ArrayList<String> diagnostics;
    public SyntaxTree(ArrayList<String> diagonstics, SyntaxNode root, SyntaxToken endOfFileToken) {
        this.root = root;
        this.endOfFileToken = endOfFileToken;
        this.diagnostics = diagonstics;
    }
    public SyntaxNode getRoot() {
        return root;
    }
    public SyntaxToken getEndOfFileToken() {
        return endOfFileToken;
    }
    public Collection<? extends String> getDiagnostics() {
        return diagnostics;
    }

    public static SyntaxTree parse(String text) {
        Parser parser = new Parser(text);
        return parser.parse();
    }
}
