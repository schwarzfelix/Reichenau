package de.schwarzf.boco.minsk.codeAnalysis.binding;

import de.schwarzf.boco.minsk.codeAnalysis.VariableSymbol;

public final class BoundVariableExpression extends BoundExpression {
    private VariableSymbol variable;

    public BoundVariableExpression(VariableSymbol variable) {
        this.variable = variable;
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.VARIABLE_EXPRESSION;
    }

    @Override
    public Class<?> getType() {
        return this.variable.getType();
    }

    public VariableSymbol getVariable() {
        return this.variable;
    }
}
