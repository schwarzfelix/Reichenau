package de.schwarzf.boco.minsk.codeAnalysis.binding;

final class BoundBinaryExpression extends BoundExpression{

    private final BoundExpression left;
    private final BoundBinaryOperatorKind operator;
    private final BoundExpression right;

    public BoundBinaryExpression(BoundExpression left, BoundBinaryOperatorKind operator, BoundExpression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public BoundExpression getLeft() {
        return left;
    }

    public BoundBinaryOperatorKind getOperator() {
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
        return left.getType();
    }

}
