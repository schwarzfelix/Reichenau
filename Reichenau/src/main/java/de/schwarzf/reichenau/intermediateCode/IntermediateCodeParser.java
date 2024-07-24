package de.schwarzf.reichenau.intermediateCode;

import java.util.LinkedList;

public class IntermediateCodeParser {
    public static LinkedList<IntermediateCodeNode> parse(String input) {
        String[] tokens = input.split("\\s+");
        LinkedList<IntermediateCodeNode> result = new LinkedList<>();

        for (String token : tokens) {
            switch (token) {
                case "ADDITION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.ADDITION));
                    break;
                case "SUBTRACTION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.SUBTRACTION));
                    break;
                case "MULTIPLICATION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.MULTIPLICATION));
                    break;
                case "DIVISION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.DIVISION));
                    break;
                case "LOGICAL_AND":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.LOGICAL_AND));
                    break;
                case "LOGICAL_OR":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.LOGICAL_OR));
                    break;
                case "EQUALS":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.EQUALS));
                    break;
                case "NOT_EQUALS":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.NOT_EQUALS));
                    break;
                case "IDENTITY":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.IDENTITY));
                    break;
                case "NEGATION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.NEGATION));
                    break;
                case "LOGICAL_NEGATION":
                    result.add(new IntermediateCodeOperator(IntermediateCodeNodeKind.LOGICAL_NEGATION));
                    break;
                default:
                    if (token.equals("true") || token.equals("false")) {
                        result.add(new IntermediateCodeOperand(Boolean.parseBoolean(token)));
                    } else {
                        result.add(new IntermediateCodeOperand(Integer.parseInt(token)));
                    }
            }
        }
        return result;
    }
}
