package de.schwarzf.boco.minsk.codeAnalysis;

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

        if (node instanceof ParenthesizedExpressionSyntax) {
            ParenthesizedExpressionSyntax p = (ParenthesizedExpressionSyntax)node;
            return evaluateExpression(p.getExpression());
        }

        throw new IllegalArgumentException("Unexpected node <" + node.getKind() + ">");
    }
}
