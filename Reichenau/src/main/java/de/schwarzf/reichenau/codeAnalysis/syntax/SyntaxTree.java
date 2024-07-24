package de.schwarzf.reichenau.codeAnalysis.syntax;

import de.schwarzf.reichenau.codeAnalysis.DiagnosticBag;

import java.util.ArrayList;
import java.util.Collection;

public final class SyntaxTree {
    private SyntaxNode root;
    private SyntaxToken endOfFileToken;
    private DiagnosticBag diagnostics;
    public SyntaxTree(DiagnosticBag diagonstics, SyntaxNode root, SyntaxToken endOfFileToken) {
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
    public DiagnosticBag getDiagnostics() {
        return diagnostics;
    }

    public static SyntaxTree parse(String text) {
        Parser parser = new Parser(text);
        return parser.parse();
    }

    public static Collection<SyntaxToken> parseTokens(String text) {
        Lexer lexer = new Lexer(text);
        ArrayList<SyntaxToken> tokens = new ArrayList<>();
        while (true) {
            SyntaxToken token = lexer.lex();
            if (token.getKind() == SyntaxKind.END_OF_FILE_TOKEN) {
                break;
            }
            tokens.add(token);
        }
        return tokens;
    }
}
