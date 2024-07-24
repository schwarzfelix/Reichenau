package de.schwarzf.reichenau.codeAnalysis;

public final class Diagnostic {

    private final TextSpan span;
    private final String message;

    public Diagnostic(TextSpan span, String message) {
        this.span = span;
        this.message = message;
    }

    public TextSpan getSpan() {
        return span;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
