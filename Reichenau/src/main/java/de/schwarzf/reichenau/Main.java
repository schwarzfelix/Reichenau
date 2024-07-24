package de.schwarzf.reichenau;

import de.schwarzf.reichenau.codeAnalysis.Compilation;
import de.schwarzf.reichenau.codeAnalysis.Diagnostic;
import de.schwarzf.reichenau.codeAnalysis.EvaluationResult;
import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxNode;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxToken;
import de.schwarzf.synthesis.JavaSynthesizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

        System.out.println("""
 
░█▀▄░█▀▀░▀█▀░█▀▀░█░█░█▀▀░█▀█░█▀█░█░█
░█▀▄░█▀▀░░█░░█░░░█▀█░█▀▀░█░█░█▀█░█░█
░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀▀
 Compiler by SchwarzF""");

        boolean showTree = false;
        boolean synthesize = false;
        HashMap<VariableSymbol, Object> variables = new HashMap<>();

        while (true) {

            System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
            System.out.print("> ");

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.equals("#exit")) {
                break;
            }
            else if (line.equals("#tree")) {
                showTree = !showTree;
                System.out.println("Show parse tree: " + showTree);
                continue;
            }
            else if (line.equals("#synth")) {
                synthesize = !synthesize;
                System.out.println("Synthesize: " + synthesize);
                continue;
            }
            else if (line.equals("#clear")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                continue;
            }

            SyntaxTree syntaxTree = SyntaxTree.parse(line);
            Compilation compilation = new Compilation(syntaxTree);
            EvaluationResult result = compilation.evaluate(variables);

            ArrayList<Diagnostic> diagnostics = result.getDiagnostics();

            if (showTree) {
                prettyPrint(syntaxTree.getRoot(), "", true);
            }

            if (synthesize) {
                JavaSynthesizer synthesizer = new JavaSynthesizer(syntaxTree);
                System.out.println(synthesizer.synthesize());
            }

            if (diagnostics.size() > 0) {

                for (Diagnostic diagnostic : diagnostics) {

                    String prefix = line.substring(0, diagnostic.getSpan().getStart());
                    String error = line.substring(diagnostic.getSpan().getStart(), diagnostic.getSpan().getEnd());
                    String suffix = line.substring(diagnostic.getSpan().getEnd());

                    System.out.println(ANSI_BLACK + ANSI_RED_BACKGROUND + diagnostic.getMessage() + ANSI_RESET);
                    System.out.println("    " + prefix + ANSI_RED + error + ANSI_RESET + suffix);

                }

            }
            else {
                System.out.println("= " + ANSI_BLACK + ANSI_GREEN_BACKGROUND + result.getValue() + ANSI_RESET);
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

        indent += isLast ? "   " : "│  ";

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