package de.schwarzf.boco.minsk;

import javafx.beans.binding.NumberExpression;

public class Evaluator {
    ExpressionSyntax root;
    public Evaluator(ExpressionSyntax root) {
        this.root = root;
    }
    public int evaluate() {
        return evaluateExpression(this.root);
    }
    private int evaluateExpression(ExpressionSyntax node) {
        if (node instanceof NumberExpressionSyntax) {
            NumberExpressionSyntax n = (NumberExpressionSyntax)node;
            return n.getNumberToken().getValue();
        }
        if (node instanceof BinaryExpressionSyntax) {
            BinaryExpressionSyntax b = (BinaryExpressionSyntax)node;

            int left = evaluateExpression(b.getLeft());
            int right = evaluateExpression(b.getRight());

            if (b.getOperatorToken().getKind() == SyntaxKind.PlusToken) {
                return left + right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.MinusToken) {
                return left - right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.StarToken) {
                return left * right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.SlashToken) {
                return left / right;
            }
            else {
                throw new IllegalArgumentException("Unexpected binary operator <" + b.getOperatorToken().getKind() + ">");
            }
        }
        throw new IllegalArgumentException("Unexpected node <" + node.getKind() + ">");
    }
}
