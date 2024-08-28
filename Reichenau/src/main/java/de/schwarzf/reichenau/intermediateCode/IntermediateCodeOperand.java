package de.schwarzf.reichenau.intermediateCode;

public class IntermediateCodeOperand extends IntermediateCodeNode {
    private final Object value;

    public IntermediateCodeOperand(Object value) {
        this.value = value;
    }

    @Override
    public IntermediateCodeNodeKind getKind() {
        return IntermediateCodeNodeKind.OPERAND;
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
