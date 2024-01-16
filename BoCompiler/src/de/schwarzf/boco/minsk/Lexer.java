package de.schwarzf.boco.minsk;

// https://www.youtube.com/watch?v=wgHIkdUQbp0&list=PLRAdsfhKI4OWNOSfS7EUu5GRAVmze1t2y

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


public class Lexer {

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

    public SyntaxToken nextToken(){

        if (position >= text.length() || getCurrent() == '\0') {
            return new SyntaxToken(SyntaxKind.EndOfFileToken, position, "\0", null);
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
            return new SyntaxToken(SyntaxKind.NumberToken, start, text, Integer.parseInt(text));
        }

        // whitespace and newlines
        if (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {
            int start = position;
            while (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {
                nextChar();
            }
            int length = position - start;
            String text = this.text.substring(start, start + length);
            return new SyntaxToken(SyntaxKind.Whitespace, start, text, null);
        }

        if (getCurrent() == '+') {
            return new SyntaxToken(SyntaxKind.PlusToken, position++, "+", null);
        }

        if (getCurrent() == '-') {
            return new SyntaxToken(SyntaxKind.MinusToken, position++, "-", null);
        }

        if (getCurrent() == '*') {
            return new SyntaxToken(SyntaxKind.StarToken, position++, "*", null);
        }

        if (getCurrent() == '/') {
            return new SyntaxToken(SyntaxKind.SlashToken, position++, "/", null);
        }

        if (getCurrent() == '(') {
            return new SyntaxToken(SyntaxKind.OpenParenthesisToken, position++, "(", null);
        }

        if (getCurrent() == ')') {
            return new SyntaxToken(SyntaxKind.CloseParenthesisToken, position++, ")", null);
        }

        this.diagnostics.add(String.format("LEXER ERROR: Bad character input: '%s'", getCurrent()));
        return new SyntaxToken(SyntaxKind.BadToken, position++, text.substring(position -1, position), null);
    }
}
