package de.schwarzf.boco.minsk.codeAnalysis.binding;

final class BoundLiteralExpression extends BoundExpression{

    private final Object value;
    public BoundLiteralExpression(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Class<?> getType() {
        return value.getClass();
    }

    @Override
    public BoundNodeKind getKind() {
        return BoundNodeKind.LITERAL_EXPRESSION;
    }

}
