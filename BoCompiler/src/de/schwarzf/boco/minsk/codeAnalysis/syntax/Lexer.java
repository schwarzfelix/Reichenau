package de.schwarzf.boco.minsk.codeAnalysis.syntax;

// https://www.youtube.com/watch?v=wgHIkdUQbp0&list=PLRAdsfhKI4OWNOSfS7EUu5GRAVmze1t2y

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


final class Lexer {

    private String text;
    private int position;
    private ArrayList<String> diagnostics = new ArrayList<>();

    public Lexer (String text) {
        this.text = text;
    }

    public ArrayList<String> getDiagnostics() {
        return this.diagnostics;
    }

    private char getCurrent(){
        if (position >= text.length()) {
            return '\0';
        }
        return text.charAt(position);
    }

    private void nextChar(){
        position++;
    }

    // nextToken()/lex() is the heart of the lexer
    public SyntaxToken lex(){

        if (position >= text.length() || getCurrent() == '\0') {
            return new SyntaxToken(SyntaxKind.END_OF_FILE_TOKEN, position, "\0", null);
        }

        Set<Character> digits = new HashSet<>();
        digits.addAll(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        if (digits.contains(getCurrent())) {
            int start = position;
            while (digits.contains(getCurrent())) {
                nextChar();
            }
            int length = position - start;
            String text = this.text.substring(start, start + length);

            int value = 0;

            try {
                value = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                this.diagnostics.add(String.format("LEXER ERROR: The number %s is not a valid Int32.", text));
            }

            return new SyntaxToken(SyntaxKind.NUMBER_TOKEN, start, text, value);
        }

        // whitespace and newlines
        if (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {
            int start = position;
            while (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {
                nextChar();
            }
            int length = position - start;
            String text = this.text.substring(start, start + length);
            return new SyntaxToken(SyntaxKind.WHITESPACE, start, text, null);
        }

        switch (getCurrent()) {
            case '+':
                return new SyntaxToken(SyntaxKind.PLUS_TOKEN, position++, "+", null);
            case '-':
                return new SyntaxToken(SyntaxKind.MINUS_TOKEN, position++, "-", null);
            case '*':
                return new SyntaxToken(SyntaxKind.STAR_TOKEN, position++, "*", null);
            case '/':
                return new SyntaxToken(SyntaxKind.SLASH_TOKEN, position++, "/", null);
            case '(':
                return new SyntaxToken(SyntaxKind.OPEN_PARENTHESIS_TOKEN, position++, "(", null);
            case ')':
                return new SyntaxToken(SyntaxKind.CLOSE_PARENTHESIS_TOKEN, position++, ")", null);
        }

        this.diagnostics.add(String.format("LEXER ERROR: Bad character input: '%s'", getCurrent()));
        return new SyntaxToken(SyntaxKind.BAD_TOKEN, position++, text.substring(position -1, position), null);
    }
}
