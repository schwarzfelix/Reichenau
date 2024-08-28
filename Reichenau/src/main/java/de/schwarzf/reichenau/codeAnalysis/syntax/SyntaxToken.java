package de.schwarzf.reichenau.codeAnalysis.syntax;


import de.schwarzf.reichenau.codeAnalysis.TextSpan;

import java.util.Collections;

public final class SyntaxToken extends SyntaxNode{

    private SyntaxKind kind;
    private int position;
    private String text;

    private Object value;
    public SyntaxToken(SyntaxKind kind, int position, String text, Object value){
        this.kind = kind;
        this.position = position;
        this.text = text;
        this.value = value;
    }

    public SyntaxKind getKind() {
        return kind;
    }

    @Override
    public Iterable<SyntaxNode> getChildren() {
        return Collections.emptyList();
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public Object getValue() {
        return value;
    }

    public TextSpan getSpan() {
        return new TextSpan(position, text.length());
    }
}
