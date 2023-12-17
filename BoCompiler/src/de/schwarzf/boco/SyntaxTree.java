package de.schwarzf.boco;

import java.util.LinkedList;

public class SyntaxTree {
    private LinkedList<SyntaxTree> childNodes;
    private byte token;
    private char character;
    public Semantic value;

    public SyntaxTree(byte token) {
        this.token = token;
        /*this.childNodes = new LinkedList<SyntaxTree>();*/
    }

    public void setToken(byte token) {
        this.token = token;
    }
    public byte getToken() {
        return this.token;
    }
    public void setCharacter(char character) {
        this.character = character;
    }
    public char getCharacter() {
        return this.character;
    }
    public void setSemantic(Semantic value) {
        this.value = value;
    }
    /*public SyntaxTree insertSubtree(byte b) {
        SyntaxTree t = new SyntaxTree(b);
        this.childNodes.add(t);
        return t;
    }*/
    public SyntaxTree getChild(int i) {
        return this.childNodes.get(i);
    }
}
