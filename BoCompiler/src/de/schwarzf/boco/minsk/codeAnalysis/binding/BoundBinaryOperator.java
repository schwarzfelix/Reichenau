package de.schwarzf.boco.minsk.codeAnalysis.binding;

import de.schwarzf.boco.minsk.codeAnalysis.syntax.SyntaxKind;

public final class BoundBinaryOperator {

    private final SyntaxKind syntaxKind;
    private final BoundBinaryOperatorKind kind;
    private final Class<?> leftType;
    private final Class<?> rightType;
    private final Class<?> resultType;

    public BoundBinaryOperator(SyntaxKind syntaxKind, BoundBinaryOperatorKind kind, Class<?> leftType, Class<?> rightType, Class<?> resultType) {
        this.syntaxKind = syntaxKind;
        this.kind = kind;
        this.leftType = leftType;
        this.rightType = rightType;
        this.resultType = resultType;
    }

    public BoundBinaryOperator(SyntaxKind syntaxKind, BoundBinaryOperatorKind kind, Class<?> type) {
        this(syntaxKind, kind, type, type, type);
    }

    public static BoundBinaryOperator[] operators = {
        new BoundBinaryOperator(SyntaxKind.PLUS_TOKEN,                BoundBinaryOperatorKind.ADDITION,       Integer.class),
        new BoundBinaryOperator(SyntaxKind.MINUS_TOKEN,               BoundBinaryOperatorKind.SUBTRACTION,    Integer.class),
        new BoundBinaryOperator(SyntaxKind.STAR_TOKEN,                BoundBinaryOperatorKind.MULTIPLICATION, Integer.class),
        new BoundBinaryOperator(SyntaxKind.SLASH_TOKEN,               BoundBinaryOperatorKind.DIVISION,       Integer.class),

        new BoundBinaryOperator(SyntaxKind.AMPERSAND_AMPERSAND_TOKEN, BoundBinaryOperatorKind.LOGICAL_AND,    Boolean.class),
        new BoundBinaryOperator(SyntaxKind.PIPE_PIPE_TOKEN,           BoundBinaryOperatorKind.LOGICAL_OR,     Boolean.class),
    };

    public static BoundBinaryOperator bind(SyntaxKind syntaxKind, Class<?> leftType, Class<?> rightType) {
        for (BoundBinaryOperator op : operators) {
            if (op.syntaxKind == syntaxKind && op.leftType == leftType && op.rightType == rightType) {
                return op;
            }
        }
        return null;
    }

    public SyntaxKind getSyntaxKind() {
        return syntaxKind;
    }

    public BoundBinaryOperatorKind getKind() {
        return kind;
    }

    public Class<?> getLeftType() {
        return leftType;
    }

    public Class<?> getRightType() {
        return rightType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public static BoundBinaryOperator[] getOperators() {
        return operators;
    }
}
