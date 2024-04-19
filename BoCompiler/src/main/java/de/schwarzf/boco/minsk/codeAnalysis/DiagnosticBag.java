package de.schwarzf.boco.minsk.codeAnalysis;

import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxKind;

import java.util.ArrayList;

public final class DiagnosticBag extends ArrayList<Diagnostic> {
    private void report(TextSpan span, String message) {
        add(new Diagnostic(span, message));
    }

    public void reportInvalidNumber(TextSpan textSpan, String text, Class<Integer> integerClass) {
        String message = String.format("The number %s isn't a valid %s.", text, integerClass.getSimpleName());
        report(textSpan, message);
    }

    public void reportBadCharacter(int position, char current) {
        String message = String.format("Bad character input: '%s'.", current);
        report(new TextSpan(position, 1), message);
    }

    public void reportUnexpectedToken(TextSpan span, SyntaxKind actualKind, SyntaxKind expectedKind) {
        String message = String.format("Unexpected token <%s>, expected <%s>.", actualKind, expectedKind);
        report(span, message);
    }

    public void reportUndefinedUnaryOperator(TextSpan span, String text, Class<?> type) {
        String message = String.format("Unary operator '%s' is not defined for type '%s'.", text, type.getSimpleName());
        report(span, message);
    }

    public void reportUndefinedBinaryOperator(TextSpan span, String text, Class<?> leftType, Class<?> rightType) {
        String message = String.format("Binary operator '%s' is not defined for types '%s' and '%s'.", text, leftType.getSimpleName(), rightType.getSimpleName());
        report(span, message);
    }

    public void reportUndefinedName(TextSpan span, String name) {
        String message = String.format("Variable '%s' doesn't exist.", name);
        report(span, message);
    }
}
