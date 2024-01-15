package de.schwarzf.boco;

import de.schwarzf.boco.minsk.Parser;
import de.schwarzf.boco.minsk.SyntaxNode;
import de.schwarzf.boco.minsk.SyntaxToken;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        System.out.println("BoCo: SchwarzF's BoCompiler for the BoCode Programming Language");
        System.out.print("Expression> ");

        // read input from console
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();

        // print the scanned string
        System.out.println("Processing: " + input);

        Parser parser = new Parser(input);
        SyntaxNode expression = parser.parse();

        prettyPrint(expression, "");

        if (parser.getDiagnostics().size() > 0) {
            for (String diagnostic : parser.getDiagnostics()) {
                System.out.println(diagnostic);
            }
        }
    }

    public static void prettyPrint(SyntaxNode node, String indent) {

        System.out.print(indent + node.getType());
        //System.out.println(node.getType() + ": " + node.toString());

        if (node instanceof SyntaxToken && ((SyntaxToken) node).getValue() != null) {
            System.out.print(" = " + ((SyntaxToken) node).getValue());
        }

        System.out.println();

        indent += "    ";

        for (SyntaxNode child : node.getChildren()) {
            prettyPrint(child, indent);
        }
    }
}