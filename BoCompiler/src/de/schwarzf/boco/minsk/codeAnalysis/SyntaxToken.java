package de.schwarzf.boco.minsk.codeAnalysis;


import java.util.Collections;

public class SyntaxToken extends SyntaxNode{

    private SyntaxKind kind;
    private int position;
    private String text;

    private Integer value;
    public SyntaxToken(SyntaxKind kind, int position, String text, Integer value){
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

    public Integer getValue() {
        return value;
    }
}