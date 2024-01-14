package de.schwarzf.boco.minsk;


import com.sun.org.apache.bcel.internal.generic.LineNumberGen;

import java.util.Collection;
import java.util.Collections;

public class SyntaxToken extends SyntaxNode{

    public enum TokenType {
        EndOfFileToken,
        NumberToken,
        Whitespace,
        Plus,
        Minus,
        Star,
        Slash,
        OpenParenthesis,
        CloseParenthesis,
        Percent,
        Caret,
        BinaryExpression,
        NumberExpression,
        BadToken
    }
    private TokenType type;
    private int position;
    private String text;

    private Integer value;
    public SyntaxToken(TokenType type, int position, String text, Integer value){
        this.type = type;
        this.position = position;
        this.text = text;
        this.value = value;
    }

    public TokenType getType() {
        return type;
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
