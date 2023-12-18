package de.schwarzf.boco.slides;

import de.schwarzf.boco.minsk.SyntaxTree;

public class Num extends Semantic {
    public int f(SyntaxTree t, int n) {
        if (t.getChildNumber() == 2) {
            SyntaxTree digit = t.getChild(0);
            SyntaxTree num = t.getChild(1);
            int v = num.value.f(num, UNDEFINED);
            return digit.value.f(digit, UNDEFINED) * potenz(num) + v;
        } else {
            SyntaxTree digit = t.getChild(0);
            return digit.value.f(digit, UNDEFINED);
        }
    }

    private int potenz(SyntaxTree t){
        SyntaxTree ch;
        int p;
        ch = t;
        p = 1;
        // Im Syntaxbaum von num solange nach unten steigen, bis Blatt erreicht
        while (ch.getChildNumber() != 0) {
            // Regel num->digit num
            if (ch.getChildNumber()==2){
                ch = ch.getChild(1);
                p = p * 10;
            } else {
                p = p*10;
                break;
            }
        }
        return p;
    }
}
