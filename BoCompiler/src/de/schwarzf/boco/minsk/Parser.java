package de.schwarzf.boco.minsk;

import java.util.ArrayList;

public class Parser {

    private SyntaxToken[] tokens;
    private int position;

    public Parser(String text) {
        ArrayList<SyntaxToken> tokensList = new ArrayList<>();

        Lexer lexer = new Lexer(text);
        SyntaxToken token;
        do {
            token = lexer.nextToken();

            if (token.getType() != SyntaxToken.TokenType.Whitespace && token.getType() != SyntaxToken.TokenType.BadToken) {
                tokensList.add(token);
            }
        } while (token.getType() != SyntaxToken.TokenType.EOF);

        this.tokens = new SyntaxToken[tokensList.size()];
        this.tokens = (SyntaxToken[]) tokensList.toArray(this.tokens);

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

    private SyntaxToken matchToken(SyntaxToken.TokenType type) {
        if (getCurrent().getType() == type) {
            return getNextToken();
        }
        return new SyntaxToken(type, getCurrent().getPosition(), null, null);
    }

    public ExpressionSyntax parse() {
        ExpressionSyntax left = parsePrimaryExpression();

        while (getCurrent().getType() == SyntaxToken.TokenType.Plus || getCurrent().getType() == SyntaxToken.TokenType.Minus) {
            SyntaxToken operatorToken = getNextToken();
            ExpressionSyntax right = parsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    private ExpressionSyntax parsePrimaryExpression() {
        SyntaxToken numberToken = matchToken(SyntaxToken.TokenType.NumberToken);
        return new NumberExpressionSyntax(numberToken);
    }
}
