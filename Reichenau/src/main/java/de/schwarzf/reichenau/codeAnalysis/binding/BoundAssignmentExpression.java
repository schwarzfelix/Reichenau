package de.schwarzf.reichenau.codeAnalysis.binding;

import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;

public final class BoundAssignmentExpression extends BoundExpression {
    private VariableSymbol variable;
    private BoundExpression expression;

    public BoundAssignmentExpression(VariableSymbol variable, BoundExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.ASSIGNMENT_EXPRESSION;
    }

    @Override
    public Class<?> getType() {
        return this.expression.getType();
    }

    public VariableSymbol getVariable() {
        return this.variable;
    }

    public BoundExpression getExpression() {
        return expression;
    }
}
