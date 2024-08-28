package de.schwarzf.reichenau.codeAnalysis.binding;

public final class BoundUnaryExpression extends BoundExpression{
    private final BoundUnaryOperator operator;
    private final BoundExpression operand;

    public BoundUnaryExpression(BoundUnaryOperator operator, BoundExpression operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public BoundUnaryOperator getOperator() {
        return operator;
    }

    public BoundExpression getOperand() {
        return operand;
    }

    @Override
    public Class<?> getType() {
        return operator.getType();
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.UNARY_EXPRESSION;
    }
}
