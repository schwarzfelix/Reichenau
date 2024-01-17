package de.schwarzf.boco.minsk.codeAnalysis;

import java.util.ArrayList;

final class Parser {

    private SyntaxToken[] tokens;
    private int position;
    private ArrayList<String> diagnostics = new ArrayList<>();

    public Parser(String text) {
        ArrayList<SyntaxToken> tokensList = new ArrayList<>();

        Lexer lexer = new Lexer(text);
        SyntaxToken token;
        do {
            token = lexer.nextToken();

            if (token.getKind() != SyntaxKind.Whitespace && token.getKind() != SyntaxKind.BadToken) {
                tokensList.add(token);
            }
        } while (token.getKind() != SyntaxKind.EndOfFileToken);

        this.tokens = new SyntaxToken[tokensList.size()];
        this.tokens = (SyntaxToken[]) tokensList.toArray(this.tokens);

        this.diagnostics.addAll(lexer.getDiagnostics());
    }

    public ArrayList<String> getDiagnostics() {
        return this.diagnostics;
    }

    private SyntaxToken getPeek(int offset) {
        int index = position + offset;
        if (index >= tokens.length) {
            return tokens[tokens.length - 1];
        }
        return tokens[index];
    }

    private SyntaxToken getCurrent() {
        return getPeek(0);
    }

    private SyntaxToken getNextToken() {
        SyntaxToken current = getCurrent();
        position++;
        return current;
    }

    private SyntaxToken matchToken(SyntaxKind kind) {
        if (getCurrent().getKind() == kind) {
            return getNextToken();
        }
        this.diagnostics.add("PARSER ERROR: Unexpected token <" + getCurrent().getKind() + ">, expected <" + kind + ">");
        return new SyntaxToken(kind, getCurrent().getPosition(), null, null);
    }

    public SyntaxTree parse() {
        ExpressionSyntax expression =  parseExpression();
        SyntaxToken endOfFileToken = matchToken(SyntaxKind.EndOfFileToken);
        return new SyntaxTree(this.diagnostics, expression, endOfFileToken);
    }

    private ExpressionSyntax parseExpression() {
        return parseTerm();
    }

    private ExpressionSyntax parseTerm() {

        ExpressionSyntax left = parseFactor();

        while (
                getCurrent().getKind() == SyntaxKind.PlusToken  ||
                getCurrent().getKind() == SyntaxKind.MinusToken
        ) {
            SyntaxToken operatorToken = getNextToken();
            ExpressionSyntax right = parseFactor();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    private ExpressionSyntax parseFactor() {

        ExpressionSyntax left = parsePrimaryExpression();

        while (
                getCurrent().getKind() == SyntaxKind.StarToken  ||
                getCurrent().getKind() == SyntaxKind.SlashToken
        ) {
            SyntaxToken operatorToken = getNextToken();
            ExpressionSyntax right = parsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;

    }

    private ExpressionSyntax parsePrimaryExpression() {

        if (getCurrent().getKind() == SyntaxKind.OpenParenthesisToken) {

            SyntaxToken left = getNextToken();
            ExpressionSyntax expression = parseExpression();
            SyntaxToken right = matchToken(SyntaxKind.CloseParenthesisToken);

            return new ParenthesizedExpressionSyntax(left, expression, right);
        }

        SyntaxToken numberToken = matchToken(SyntaxKind.NumberToken);
        return new LiteralExpressionSyntax(numberToken);
    }
}
