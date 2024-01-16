package de.schwarzf.boco.minsk;


import java.util.Collections;

public class SyntaxToken extends SyntaxNode{

    public enum SyntaxKind {

        //Tokens
            NumberToken,
            PlusToken,
            MinusToken,
            StarToken,
            SlashToken,

        //Expressions
            BinaryExpression,
            NumberExpression,
            OpenParenthesisToken,
            CloseParenthesisToken,

        //Other
            Whitespace,
            EndOfFileToken,
            BadToken

    }
    private SyntaxKind type;
    private int position;
    private String text;

    private Integer value;
    public SyntaxToken(SyntaxKind type, int position, String text, Integer value){
        this.type = type;
        this.position = position;
        this.text = text;
        this.value = value;
    }

    public SyntaxKind getType() {
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
