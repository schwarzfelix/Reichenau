package de.schwarzf.reichenau.codeAnalysis;

import de.schwarzf.reichenau.codeAnalysis.binding.BoundExpression;
import de.schwarzf.reichenau.codeAnalysis.syntax.ExpressionSyntax;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;
import de.schwarzf.reichenau.codeAnalysis.binding.Binder;

import java.util.ArrayList;
import java.util.HashMap;

public final class Compilation {
    private final SyntaxTree syntax;

    public Compilation(SyntaxTree syntax) {
        this.syntax = syntax;
    }

    public EvaluationResult evaluate(HashMap<VariableSymbol, Object> variables) {
        Binder binder = new Binder(variables);
        BoundExpression boundExpression = binder.bindExpression((ExpressionSyntax) syntax.getRoot());

        ArrayList<Diagnostic> allDiagnostics = new ArrayList<>();
        allDiagnostics.addAll(syntax.getDiagnostics());
        allDiagnostics.addAll(binder.getDiagnostics());

        Evaluator evaluator = new Evaluator(boundExpression, variables);
        Object value = evaluator.evaluate();

        return new EvaluationResult(allDiagnostics, value);
    }
}
