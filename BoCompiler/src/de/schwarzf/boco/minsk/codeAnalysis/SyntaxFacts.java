package de.schwarzf.boco.minsk.codeAnalysis;

public class SyntaxFacts {
    public static int getBinaryOperatorPrecedence(SyntaxKind kind) {
        switch (kind) {
            case StarToken:
            case SlashToken:
                return 2;
            case PlusToken:
            case MinusToken:
                return 1;
            default:
                return 0;
        }
    }
}
