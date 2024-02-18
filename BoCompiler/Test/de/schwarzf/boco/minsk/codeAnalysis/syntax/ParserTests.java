package de.schwarzf.boco.minsk.codeAnalysis.syntax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTests {

    @Test
    public void BinaryExpressionHonorsPrecedences() {
        for (SyntaxKind op1 : SyntaxFacts.getBinaryOperatorKinds()) {
            for (SyntaxKind op2 : SyntaxFacts.getBinaryOperatorKinds()) {
                BinaryExpressionHonorsPrecedences(op1, op2);
            }
        }
    }
    public void BinaryExpressionHonorsPrecedences(SyntaxKind op1, SyntaxKind op2) {

        //TODO implement test using ext class/flatten tree e4 1:16 and following tests
    }

}
