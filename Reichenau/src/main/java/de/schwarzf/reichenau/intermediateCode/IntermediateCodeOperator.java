package de.schwarzf.reichenau.intermediateCode;

public class IntermediateCodeOperator extends IntermediateCodeNode {

    private IntermediateCodeKind operator;

    public IntermediateCodeOperator(IntermediateCodeKind operator) {
        this.operator = operator;
    }

    @Override
    public IntermediateCodeKind getKind() {
        return operator;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
