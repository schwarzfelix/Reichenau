package de.schwarzf.boco.slides;

import de.schwarzf.boco.minsk.SyntaxTree;

public class Digit extends Semantic {
    public int f(SyntaxTree t, int n) {
        SyntaxTree symbol = t.getChild(0);
        switch (symbol.getCharacter()){
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            default : return UNDEFINED; // Error
        }
    }
}
