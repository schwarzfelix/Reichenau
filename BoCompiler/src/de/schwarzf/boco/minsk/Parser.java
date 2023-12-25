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

        this.tokens = (SyntaxToken[]) tokensList.toArray();
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
}
