package de.schwarzf.reichenau.intermediateCode;

import java.util.Queue;
import java.util.Stack;

public class StackMachine {

    private Queue<IntermediateCodeNode> input;
    private Stack<Object> stack;

    public StackMachine(Queue<IntermediateCodeNode> input) {
        this.input = input;
        stack = new Stack<>();
    }

    public StackMachine(String input) {
        this.input = IntermediateCodeParser.parse(input);
        stack = new Stack<>();
    }

    public void run() {
        while (!input.isEmpty()) {
            IntermediateCodeNode node = input.poll();

            Object left = null;
            Object right = null;

            switch (node.getKind()) {
                case ADDITION:
                case SUBTRACTION:
                case MULTIPLICATION:
                case DIVISION:
                case LOGICAL_AND:
                case LOGICAL_OR:
                case EQUALS:
                case NOT_EQUALS:
                    // Pop the operands in the correct order
                    right = stack.pop();
                    left = stack.pop();
                    break;
                case IDENTITY:
                case NEGATION:
                case LOGICAL_NEGATION:
                    right = stack.pop();
                    break;
            }

            switch (node.getKind()) {
                case OPERAND -> stack.push(((IntermediateCodeOperand)node).getValue());
                case ADDITION -> stack.push((int)left + (int)right);
                case SUBTRACTION -> stack.push((int)left - (int)right);
                case MULTIPLICATION -> stack.push((int)left * (int)right);
                case DIVISION -> stack.push((int)left / (int)right);
                case LOGICAL_AND -> stack.push((boolean)left && (boolean)right);
                case LOGICAL_OR -> stack.push((boolean)left || (boolean)right);
                case EQUALS -> stack.push(left.equals(right));
                case NOT_EQUALS -> stack.push(!left.equals(right));
                case IDENTITY -> stack.push((int)right);
                case NEGATION -> stack.push(-(int)right);
                case LOGICAL_NEGATION -> stack.push(!(boolean)right);
            }

            System.out.println("Stack after " + node.getKind() + ": " + stack);
        }
    }

    public Object getResult() {
        return stack.pop();
    }

}
