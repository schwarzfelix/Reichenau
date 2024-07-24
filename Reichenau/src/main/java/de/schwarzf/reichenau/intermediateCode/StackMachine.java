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

            switch (node.getKind()) {
                case IntermediateCodeNodeKind.OPERAND -> stack.push(((IntermediateCodeOperand)node).getValue());
                case IntermediateCodeNodeKind.ADDITION -> stack.push((int)stack.pop() + (int)stack.pop());
                case IntermediateCodeNodeKind.SUBTRACTION -> stack.push((int)stack.pop() - (int)stack.pop());
                case IntermediateCodeNodeKind.MULTIPLICATION -> stack.push((int)stack.pop() * (int)stack.pop());
                case IntermediateCodeNodeKind.DIVISION -> stack.push((int)stack.pop() / (int)stack.pop());
                case IntermediateCodeNodeKind.LOGICAL_AND -> stack.push((boolean)stack.pop() && (boolean)stack.pop());
                case IntermediateCodeNodeKind.LOGICAL_OR -> stack.push((boolean)stack.pop() || (boolean)stack.pop());
                case IntermediateCodeNodeKind.EQUALS -> stack.push(stack.pop().equals(stack.pop()));
                case IntermediateCodeNodeKind.NOT_EQUALS -> stack.push(!stack.pop().equals(stack.pop()));
            }

            System.out.println("Stack after " + node.getKind() + ": " + stack);
        }
    }

    public Object getResult() {
        return stack.pop();
    }

}
