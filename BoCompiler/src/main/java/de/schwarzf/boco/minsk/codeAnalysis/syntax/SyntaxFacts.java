package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import java.util.ArrayList;

public class SyntaxFacts {
    public static int getUnaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case PLUS_TOKEN:
            case MINUS_TOKEN:
            case BANG_TOKEN:
                return 6;
            default:
                return 0;
        }
    }
    public static int getBinaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case STAR_TOKEN:
            case SLASH_TOKEN:
                return 5;
            case PLUS_TOKEN:
            case MINUS_TOKEN:
                return 4;
            case EQUALS_EQUALS_TOKEN:
            case BANG_EQUALS_TOKEN:
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

    public static String getText(SyntaxKind kind) {
        switch (kind) {
            case TRUE_KEYWORD:
                return "true";
            case FALSE_KEYWORD:
                return "false";
            case PLUS_TOKEN:
                return "+";
            case MINUS_TOKEN:
                return "-";
            case STAR_TOKEN:
                return "*";
            case SLASH_TOKEN:
                return "/";
            case BANG_TOKEN:
                return "!";
            case AMPERSAND_AMPERSAND_TOKEN:
                return "&&";
            case EQUALS_EQUALS_TOKEN:
                return "==";
            case BANG_EQUALS_TOKEN:
                return "!=";
            case EQUALS_TOKEN:
                return "=";
            case PIPE_PIPE_TOKEN:
                return "||";
            case OPEN_PARENTHESIS_TOKEN:
                return "(";
            case CLOSE_PARENTHESIS_TOKEN:
                    return ")";
            default:
                return null;
        }
    }

    public static ArrayList<SyntaxKind> getBinaryOperatorKinds() {
        SyntaxKind[] kinds = SyntaxKind.values();
        ArrayList<SyntaxKind> binaryOperators = new ArrayList<>();
        for (SyntaxKind k : kinds) {
            if (getBinaryOperatorPrecedence(k) > 0) {
                binaryOperators.add(k);
            }
        }
        return binaryOperators;
    }

    public static ArrayList<SyntaxKind> getUnaryOperatorKinds() {
        SyntaxKind[] kinds = SyntaxKind.values();
        ArrayList<SyntaxKind> unaryOperators = new ArrayList<>();
        for (SyntaxKind k : kinds) {
            if (getUnaryOperatorPrecedence(k) > 0) {
                unaryOperators.add(k);
            }
        }
        return unaryOperators;
    }
}
