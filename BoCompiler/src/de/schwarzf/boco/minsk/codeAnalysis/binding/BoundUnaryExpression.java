package de.schwarzf.boco.minsk.codeAnalysis.binding;

final class BoundUnaryExpression extends BoundExpression{
    private final BoundUnaryOperatorKind operatorKind;
    private final BoundExpression operand;

    public BoundUnaryExpression(BoundUnaryOperatorKind operator, BoundExpression operand) {
        this.operatorKind = operator;
        this.operand = operand;
    }

    public BoundUnaryOperatorKind getOperatorKind() {
        return operatorKind;
    }

    public BoundExpression getOperand() {
        return operand;
    }

    @Override
    public Class<?> getType() {
        return operand.getType();
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.UNARY_EXPRESSION;
    }
}
