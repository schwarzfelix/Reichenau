package de.schwarzf.boco.minsk.codeAnalysis;

import de.schwarzf.boco.minsk.codeAnalysis.syntax.*;

public final class Evaluator {
    ExpressionSyntax root;
    public Evaluator(ExpressionSyntax root) {
        this.root = root;
    }
    public int evaluate() {
        return evaluateExpression(this.root);
    }
    private int evaluateExpression(ExpressionSyntax node) {

        if (node instanceof LiteralExpressionSyntax) {
            LiteralExpressionSyntax n = (LiteralExpressionSyntax)node;
            return n.getLiteralToken().getValue();
        }

        if (node instanceof UnaryExpressionSyntax) {
            UnaryExpressionSyntax u = (UnaryExpressionSyntax)node;

            int operand = evaluateExpression(u.getOperand());

            if (u.getOperatorToken().getKind() == SyntaxKind.PLUS_TOKEN) {
                return operand;
            }
            else if (u.getOperatorToken().getKind() == SyntaxKind.MINUS_TOKEN) {
                return -operand;
            }
            else {
                throw new IllegalArgumentException("Unexpected unary operator <" + u.getOperatorToken().getKind() + ">");
            }
        }

        if (node instanceof BinaryExpressionSyntax) {
            BinaryExpressionSyntax b = (BinaryExpressionSyntax)node;

            int left = evaluateExpression(b.getLeft());
            int right = evaluateExpression(b.getRight());

            if (b.getOperatorToken().getKind() == SyntaxKind.PLUS_TOKEN) {
                return left + right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.MINUS_TOKEN) {
                return left - right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.STAR_TOKEN) {
                return left * right;
            }
            else if (b.getOperatorToken().getKind() == SyntaxKind.SLASH_TOKEN) {
                return left / right;
            }
            else {
                throw new IllegalArgumentException("Unexpected binary operator <" + b.getOperatorToken().getKind() + ">");
            }
        }

        if (node instanceof ParenthesizedExpressionSyntax) {
            ParenthesizedExpressionSyntax p = (ParenthesizedExpressionSyntax)node;
            return evaluateExpression(p.getExpression());
        }

        throw new IllegalArgumentException("Unexpected node <" + node.getKind() + ">");
    }
}
