package de.schwarzf.boco;

import de.schwarzf.boco.minsk.Parser;
import de.schwarzf.boco.minsk.SyntaxNode;
import de.schwarzf.boco.minsk.SyntaxToken;
import de.schwarzf.boco.minsk.SyntaxTree;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        System.out.println("BoCo: SchwarzF's BoCompiler for the BoCode Programming Language");
        while (true) {

            System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
            System.out.print("Expression> ");

            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();

            Parser parser = new Parser(input);
            SyntaxTree syntaxTree = parser.parse();

            prettyPrint(syntaxTree.getRoot(), "", true);

            if (syntaxTree.getDiagnostics().length > 0) {
                for (String diagnostic : syntaxTree.getDiagnostics()) {
                    System.out.println(ANSI_RED + diagnostic + ANSI_RESET);
                }
            }

        }
    }

    public static void prettyPrint(SyntaxNode node, String indent, boolean isLast) {

        String marker = isLast ? "└──" : "├──";

        System.out.print(indent + marker + node.getKind());

        if (node instanceof SyntaxToken && ((SyntaxToken) node).getValue() != null) {
            System.out.print(" = " + ((SyntaxToken) node).getValue());
        }

        System.out.println();

        indent += isLast ? "    " : "│   ";

        // get last child of node
        SyntaxNode lastChild = null;
        for (SyntaxNode element : node.getChildren()) {
            lastChild = element;
        }

        for (SyntaxNode child : node.getChildren()) {
            prettyPrint(child, indent, child == lastChild);
        }
    }
}