package de.schwarzf.boco.minsk.codeAnalysis.binding;

public final class BoundAssignmentExpression extends BoundExpression {
    private String name;
    private BoundExpression expression;

    public BoundAssignmentExpression(String name, BoundExpression expression) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public BoundExpression getExpression() {
        return expression;
    }
}
