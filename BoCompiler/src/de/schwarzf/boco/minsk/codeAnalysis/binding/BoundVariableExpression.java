package de.schwarzf.boco.minsk.codeAnalysis.binding;

public final class BoundVariableExpression extends BoundExpression {
    private String name;
    private Class<?> type;

    public BoundVariableExpression(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.VARIABLE_EXPRESSION;
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }
}
