package de.schwarzf.reichenau.codeAnalysis.syntax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SyntaxFactTests {

    @Test
    public void GetTextRoundTrips(){
        SyntaxKind[] kinds = SyntaxKind.values();

        for (SyntaxKind kind : kinds) {
            GetTextRoundTrips(kind);
        }
    }

    public void GetTextRoundTrips(SyntaxKind kind){
        String text = SyntaxFacts.getText(kind);

        if (text == null) {
            return;
        }

        ArrayList<SyntaxToken> tokens = (ArrayList<SyntaxToken>) SyntaxTree.parseTokens(text);
        SyntaxToken token = tokens.get(0);

        assertEquals(1, tokens.size());
        assertEquals(kind, token.getKind());
        assertEquals(text, token.getText());

    }

}
