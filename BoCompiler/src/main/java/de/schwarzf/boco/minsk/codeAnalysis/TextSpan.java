package de.schwarzf.boco.minsk.codeAnalysis;

public final class TextSpan {

    private final int start;
    private final int length;

    public TextSpan(int start, int length) {
        this.start = start;
        this.length = length;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public int getEnd() {
        return start + length;
    }
}
