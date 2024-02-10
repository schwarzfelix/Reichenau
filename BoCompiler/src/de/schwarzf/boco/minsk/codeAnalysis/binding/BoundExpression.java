package de.schwarzf.boco.minsk.codeAnalysis.binding;

abstract class BoundExpression extends BoundNode {
    public abstract Class<?> getType();
}
