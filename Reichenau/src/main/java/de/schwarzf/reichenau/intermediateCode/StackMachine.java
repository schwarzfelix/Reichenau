package de.schwarzf.reichenau.intermediateCode;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class StackMachine {

    private Queue<IntermediateCodeNode> input;
    private Stack<Object> stack;

    public StackMachine(Queue<IntermediateCodeNode> input) {
        this.input = input;
        stack = new Stack<>();
    }

    public void execute() {
        while (!input.isEmpty()) {

        }
    }

}
