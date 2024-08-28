package de.schwarzf.reichenau.intermediateCode;

public class IntermediateCodeOperator extends IntermediateCodeNode {

    private IntermediateCodeNodeKind operator;

    public IntermediateCodeOperator(IntermediateCodeNodeKind operator) {
        this.operator = operator;
    }

    @Override
    public IntermediateCodeNodeKind getKind() {
        return operator;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
