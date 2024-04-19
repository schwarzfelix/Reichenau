package de.schwarzf.boco.synthesis;

import de.schwarzf.boco.minsk.codeAnalysis.VariableSymbol;
import de.schwarzf.boco.minsk.codeAnalysis.binding.Binder;
import de.schwarzf.boco.minsk.codeAnalysis.binding.BoundExpression;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxTree;

import java.util.HashMap;

public abstract class Synthesizer {

    protected BoundExpression root;

    public Synthesizer(SyntaxTree syntax) {
        Binder binder = new Binder(new HashMap<VariableSymbol, Object>());
        root = binder.bindExpression((ExpressionSyntax) syntax.getRoot());
    }
    public abstract String synthesize();
}
