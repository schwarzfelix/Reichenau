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

    private static final Set<Character> digits = new HashSet<>();

    private String text;
    private DiagnosticBag diagnostics = new DiagnosticBag();

    private int position;

    private int start;
    private SyntaxKind kind;
    private Object value;


    public Lexer (String text) {
        this.text = text;

        digits.addAll(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
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

        this.start = this.position;
        this.kind = SyntaxKind.BAD_TOKEN;
        this.value = null;

        if (digits.contains(getCurrent())) {

            readNumberToken();

        } else if (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {

            readWhiteSpace();

        } else if (Character.isLetter(getCurrent())) {

            readIdentifierOrKeyword();

        } else {

            switch (getCurrent()) {
                case '\0':
                    this.kind = SyntaxKind.END_OF_FILE_TOKEN;
                    position++;
                    break;
                case '+':
                    this.kind = SyntaxKind.PLUS_TOKEN;
                    position++;
                    break;
                case '-':
                    this.kind = SyntaxKind.MINUS_TOKEN;
                    position++;
                    break;
                case '*':
                    this.kind = SyntaxKind.STAR_TOKEN;
                    position++;
                    break;
                case '/':
                    this.kind = SyntaxKind.SLASH_TOKEN;
                    position++;
                    break;
                case '(':
                    this.kind = SyntaxKind.OPEN_PARENTHESIS_TOKEN;
                    position++;
                    break;
                case ')':
                    this.kind = SyntaxKind.CLOSE_PARENTHESIS_TOKEN;
                    position++;
                    break;

                case '&':
                    if (lookAhead() == '&') {
                        this.kind = SyntaxKind.AMPERSAND_AMPERSAND_TOKEN;
                        position += 2;
                        break;
                    }
                    break;
                case '|':
                    if (lookAhead() == '|') {
                        this.kind = SyntaxKind.PIPE_PIPE_TOKEN;
                        position += 2;
                        break;
                    }
                    break;
                case '=':
                    if (lookAhead() == '=') {
                        this.kind = SyntaxKind.EQUALS_EQUALS_TOKEN;
                        position += 2;
                        break;
                    } else {
                        this.kind = SyntaxKind.EQUALS_TOKEN;
                        position++;
                        break;
                    }
                case '!':
                    if (lookAhead() == '=') {
                        this.kind = SyntaxKind.BANG_EQUALS_TOKEN;
                        position += 2;
                        break;
                    } else {
                        this.kind = SyntaxKind.BANG_TOKEN;
                        position++;
                        break;
                    }
                default:
                    this.diagnostics.reportBadCharacter(position, getCurrent());
                    this.position++;
                    break;
            }

        }

        int length = this.position - this.start;
        String text = SyntaxFacts.getText(this.kind);
        if (text == null) {
            text = this.text.substring(start, length);
        }

        return new SyntaxToken(this.kind, this.start, text, (int)this.value);
    }

    private void readIdentifierOrKeyword() {
        while (Character.isLetter(getCurrent())) {
            nextChar();
        }

        int length = position - start;
        String text = this.text.substring(start, start + length);

        this.kind = SyntaxFacts.getKeywordKind(text);
    }

    private void readWhiteSpace() {

        while (getCurrent() == ' ' || getCurrent() == '\r' || getCurrent() == '\n') {
            nextChar();
        }

        this.kind = SyntaxKind.WHITESPACE;

    }

    private void readNumberToken(){

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

        this.value = value;
        this.kind = SyntaxKind.NUMBER_TOKEN;
    }
}
