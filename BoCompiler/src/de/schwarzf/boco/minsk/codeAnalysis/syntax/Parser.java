package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import de.schwarzf.boco.minsk.codeAnalysis.Diagnostic;
import de.schwarzf.boco.minsk.codeAnalysis.DiagnosticBag;

import java.util.ArrayList;

final class Parser {

    private SyntaxToken[] tokens;
    private int position;
    private DiagnosticBag diagnostics = new DiagnosticBag();

    public Parser(String text) {
        ArrayList<SyntaxToken> tokensList = new ArrayList<>();

        Lexer lexer = new Lexer(text);
        SyntaxToken token;
        do {
            token = lexer.lex();

            if (token.getKind() != SyntaxKind.WHITESPACE && token.getKind() != SyntaxKind.BAD_TOKEN) {
                tokensList.add(token);
            }
        } while (token.getKind() != SyntaxKind.END_OF_FILE_TOKEN);

        this.tokens = new SyntaxToken[tokensList.size()];
        this.tokens = (SyntaxToken[]) tokensList.toArray(this.tokens);

        this.diagnostics.addAll(lexer.getDiagnostics());
    }

    public DiagnosticBag getDiagnostics() {
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
        this.diagnostics.reportUnexpectedToken(getCurrent().getSpan(), getCurrent().getKind(), kind);
        return new SyntaxToken(kind, getCurrent().getPosition(), null, null);
    }

    public SyntaxTree parse() {
        ExpressionSyntax expression =  parseExpression();
        SyntaxToken endOfFileToken = matchToken(SyntaxKind.END_OF_FILE_TOKEN);
        return new SyntaxTree(this.diagnostics, expression, endOfFileToken);
    }

    private ExpressionSyntax parseExpression() {
        return parseExpression(0);
    }
    private ExpressionSyntax parseExpression(int parentPrecedence) {

         ExpressionSyntax left;
        int unaryOperatorPrecedence = SyntaxFacts.getUnaryOperatorPrecedence(getCurrent().getKind());
        if (unaryOperatorPrecedence != 0 && unaryOperatorPrecedence >= parentPrecedence) {

            SyntaxToken operatorToken = getNextToken();
            ExpressionSyntax operand = parseExpression(unaryOperatorPrecedence);
            left = new UnaryExpressionSyntax(operatorToken, operand);
        }
        else {
            left = parsePrimaryExpression();
        }

         while (true) {
             int precedence = SyntaxFacts.getBinaryOperatorPrecedence(getCurrent().getKind());
             if (precedence == 0 || precedence <= parentPrecedence) {
                 break;
             }

                SyntaxToken operatorToken = getNextToken();
                ExpressionSyntax right = parseExpression(precedence);
                left = new BinaryExpressionSyntax(left, operatorToken, right);
         }

         return left;

    }


    private ExpressionSyntax parsePrimaryExpression() {

        switch (getCurrent().getKind()) {
            case OPEN_PARENTHESIS_TOKEN:
                SyntaxToken left = getNextToken();
                ExpressionSyntax expression = parseExpression();
                SyntaxToken right = matchToken(SyntaxKind.CLOSE_PARENTHESIS_TOKEN);
                return new ParenthesizedExpressionSyntax(left, expression, right);
            case FALSE_KEYWORD:
            case TRUE_KEYWORD:
                SyntaxToken keywordToken = getNextToken();
                boolean value = keywordToken.getKind() == SyntaxKind.TRUE_KEYWORD;
                return new LiteralExpressionSyntax(keywordToken, value);
            default:
                SyntaxToken numberToken = matchToken(SyntaxKind.NUMBER_TOKEN);
                return new LiteralExpressionSyntax(numberToken);
        }
    }
}
