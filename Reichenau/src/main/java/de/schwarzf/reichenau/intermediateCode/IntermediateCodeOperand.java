package de.schwarzf.reichenau.intermediateCode;

import de.schwarzf.reichenau.codeAnalysis.binding.BoundNodeKind;

public class IntermediateCodeOperand extends IntermediateCodeNode {
    private final Object value;

    public IntermediateCodeOperand(Object value) {
        this.value = value;
    }

    @Override
    public IntermediateCodeKind getKind() {
        return IntermediateCodeKind.OPERAND;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return value.getClass();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
