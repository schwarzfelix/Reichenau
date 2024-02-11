package de.schwarzf.boco.minsk.codeAnalysis.syntax;

// https://www.youtube.com/watch?v=wgHIkdUQbp0&list=PLRAdsfhKI4OWNOSfS7EUu5GRAVmze1t2y

import de.schwarzf.boco.minsk.codeAnalysis.Diagnostic;
import de.schwarzf.boco.minsk.codeAnalysis.DiagnosticBag;
import de.schwarzf.boco.minsk.codeAnalysis.TextSpan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;


final class Lexer {

    private String text;
    private int position;
    private DiagnosticBag diagnostics = new DiagnosticBag();

    public Lexer (String text) {
        this.text = text;
    }

    public DiagnosticBag getDiagnostics() {
        return this.diagnostics;
    }

    private char getCurrent(){
        return peek(0);
    }

    private char lookAhead(){
        return peek(1);
    }

    private char peek(int offset){
        int index = position + offset;
        if (index >= text.length()) {
            return '\0';
        }
        return text.charAt(index);
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
                this.diagnostics.reportInvalidNumber(new TextSpan(start, length), text, Integer.class);
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

        // letters and keywords
        if (Character.isLetter(getCurrent())) {
            int start = position;
            while (Character.isLetter(getCurrent())) {
                nextChar();
            }
            int length = position - start;
            String text = this.text.substring(start, start + length);
            SyntaxKind kind = SyntaxFacts.getKeywordKind(text);
            return new SyntaxToken(kind, start, text, null);
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

            case '&':
                if (lookAhead() == '&') {
                    return new SyntaxToken(SyntaxKind.AMPERSAND_AMPERSAND_TOKEN, position += 2, "&&", null);
                }
                break;
            case '|':
                if (lookAhead() == '|') {
                    return new SyntaxToken(SyntaxKind.PIPE_PIPE_TOKEN, position += 2, "||", null);
                }
                break;
            case '=':
                if (lookAhead() == '=') {
                    return new SyntaxToken(SyntaxKind.EQUALS_EQUALS_TOKEN, position += 2, "==", null);
                }
                break;
            case '!':
                if (lookAhead() == '=') {
                    return new SyntaxToken(SyntaxKind.BANG_EQUALS_TOKEN, position += 2, "!=", null);
                } else {
                    return new SyntaxToken(SyntaxKind.BANG_TOKEN, position++, "!", null);
                }

        }

        //this.diagnostics.add(String.format("LEXER ERROR: Bad character input: '%s'", getCurrent()));
        this.diagnostics.reportBadCharacter(position, getCurrent());
        return new SyntaxToken(SyntaxKind.BAD_TOKEN, position++, text.substring(position -1, position), null);
    }
}
