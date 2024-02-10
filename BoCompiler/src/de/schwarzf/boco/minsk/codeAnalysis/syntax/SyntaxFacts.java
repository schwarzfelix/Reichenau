package de.schwarzf.boco.minsk.codeAnalysis.syntax;

public class SyntaxFacts {
    public static int getUnaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case PLUS_TOKEN:
            case MINUS_TOKEN:
            case BANG_TOKEN:
                return 5;
            default:
                return 0;
        }
    }
    public static int getBinaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case STAR_TOKEN:
            case SLASH_TOKEN:
                return 4;
            case PLUS_TOKEN:
            case MINUS_TOKEN:
                return 3;
            case AMPERSAND_AMPERSAND_TOKEN:
                return 2;
            case PIPE_PIPE_TOKEN:
                return 1;
            default:
                return 0;
        }
    }

    public static SyntaxKind getKeywordKind(String text) {
        switch (text) {
            case "true":
                return SyntaxKind.TRUE_KEYWORD;
            case "false":
                return SyntaxKind.FALSE_KEYWORD;
            default:
                return SyntaxKind.IDENTIFIER_TOKEN;
        }
    }
}
