package de.schwarzf.reichenau;

import de.schwarzf.reichenau.codeAnalysis.Compilation;
import de.schwarzf.reichenau.codeAnalysis.Diagnostic;
import de.schwarzf.reichenau.codeAnalysis.EvaluationResult;
import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxTree;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxNode;
import de.schwarzf.reichenau.codeAnalysis.syntax.SyntaxToken;
import de.schwarzf.reichenau.codeSynthesis.JavaSynthesizer;
import de.schwarzf.reichenau.intermediateCode.IntermediateCodeGenerator;
import de.schwarzf.reichenau.intermediateCode.IntermediateCodeNode;
import de.schwarzf.reichenau.intermediateCode.StackMachine;

import java.util.*;

import static de.schwarzf.reichenau.Color.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("""
 
 ░█▀▄░█▀▀░▀█▀░█▀▀░█░█░█▀▀░█▀█░█▀█░█░█
 ░█▀▄░█▀▀░░█░░█░░░█▀█░█▀▀░█░█░█▀█░█░█
 ░▀░▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀▀
 Compiler by SchwarzF""");

        boolean showTree = false;
        boolean showIntermediateCode = false;
        boolean runEvaluator = false;
        boolean runStackMachine = false;
        boolean inputIntermediateCode = false;

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
            else if (line.equals("#imed")) {
                showIntermediateCode = !showIntermediateCode;
                System.out.println("Show intermediate code: " + showIntermediateCode);
                continue;
            }
            else if (line.equals("#eval")) {
                runEvaluator = !runEvaluator;
                System.out.println("Run Evaluator: " + runEvaluator);
                continue;
            }
            else if (line.equals("#stack")) {
                runStackMachine = !runStackMachine;
                System.out.println("Run StackMachine: " + runStackMachine);
                continue;
            }
            else if (line.equals("#clear")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                continue;
            }
            else if (line.equals("#input")) {
                inputIntermediateCode = !inputIntermediateCode;
                System.out.println("Input Intermediate Code and run StackMachine: " + inputIntermediateCode);
                continue;
            }

            if (!inputIntermediateCode) {

                SyntaxTree syntaxTree = SyntaxTree.parse(line);
                Compilation compilation = new Compilation(syntaxTree);
                EvaluationResult result = compilation.evaluate(variables);

                ArrayList<Diagnostic> diagnostics = result.getDiagnostics();

                if (showTree) {
                    prettyPrint(syntaxTree.getRoot(), "", true);
                }

                if (showIntermediateCode || runStackMachine) {

                    IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator(syntaxTree);
                    LinkedList<IntermediateCodeNode> intermediateCode = intermediateCodeGenerator.generateIntermediateCode();

                    if (showIntermediateCode) {

                        String intermediateCodeString = "";
                        for (IntermediateCodeNode node : intermediateCode) {
                            intermediateCodeString += node.toString() + " ";
                        }
                        intermediateCodeString = intermediateCodeString.trim();
                        System.out.println(intermediateCodeString);

                    }

                    if (runStackMachine) {
                        StackMachine stackMachine = new StackMachine(intermediateCode);
                        stackMachine.run();
                        System.out.println("= " + ANSI_BLACK + ANSI_GREEN_BACKGROUND + stackMachine.getResult() + ANSI_RESET);
                    }
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
                else if (runEvaluator) {
                    System.out.println("= " + ANSI_BLACK + ANSI_GREEN_BACKGROUND + result.getValue() + ANSI_RESET);
                }
            }
            else {

                StackMachine stackMachine = new StackMachine(line);
                stackMachine.run();
                System.out.println("= " + ANSI_BLACK + ANSI_GREEN_BACKGROUND + stackMachine.getResult() + ANSI_RESET);

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