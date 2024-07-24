package de.schwarzf.synthesis;

import de.schwarzf.reichenau.codeAnalysis.binding.BoundExpression;
import de.schwarzf.reichenau.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;
import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import de.schwarzf.reichenau.codeAnalysis.binding.Binder;

import java.util.HashMap;

public abstract class Synthesizer {

    protected BoundExpression root;

    public Synthesizer(SyntaxTree syntax) {
        Binder binder = new Binder(new HashMap<VariableSymbol, Object>());
        root = binder.bindExpression((ExpressionSyntax) syntax.getRoot());
    }
    public abstract String synthesize();
}
