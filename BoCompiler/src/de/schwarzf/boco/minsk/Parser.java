package de.schwarzf.boco.minsk;

import java.util.ArrayList;

public class Parser {

    private SyntaxToken[] tokens;
    private int position;
    private ArrayList<String> diagnostics = new ArrayList<>();

    public Parser(String text) {
        ArrayList<SyntaxToken> tokensList = new ArrayList<>();

        Lexer lexer = new Lexer(text);
        SyntaxToken token;
        do {
            token = lexer.nextToken();

            if (token.getType() != SyntaxKind.Whitespace && token.getType() != SyntaxKind.BadToken) {
                tokensList.add(token);
            }
        } while (token.getType() != SyntaxKind.EndOfFileToken);

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

    private SyntaxToken matchToken(SyntaxKind type) {
        if (getCurrent().getType() == type) {
            return getNextToken();
        }
        this.diagnostics.add("PARSER ERROR: Unexpected token <" + getCurrent().getType() + ">, expected <" + type + ">");
        return new SyntaxToken(type, getCurrent().getPosition(), null, null);
    }

    public ExpressionSyntax parse() {
        ExpressionSyntax left = parsePrimaryExpression();

        while (getCurrent().getType() == SyntaxKind.PlusToken || getCurrent().getType() == SyntaxKind.MinusToken) {
            SyntaxToken operatorToken = getNextToken();
            ExpressionSyntax right = parsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    private ExpressionSyntax parsePrimaryExpression() {
        SyntaxToken numberToken = matchToken(SyntaxKind.NumberToken);
        return new NumberExpressionSyntax(numberToken);
    }
}
