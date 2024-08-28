package de.schwarzf.reichenau.codeAnalysis.binding;

public final class BoundBinaryExpression extends BoundExpression{

    private final BoundExpression left;
    private final BoundBinaryOperator operator;
    private final BoundExpression right;

    public BoundBinaryExpression(BoundExpression left, BoundBinaryOperator operator, BoundExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public BoundExpression getLeft() {
        return left;
    }

    public BoundBinaryOperator getOperator() {
        return operator;
    }

    public BoundExpression getRight() {
        return right;
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.BINARY_EXPRESSION;
    }

    @Override
    public Class<?> getType() {
        return operator.getType();
    }

}
