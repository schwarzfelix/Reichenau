package de.schwarzf.reichenau.codeAnalysis;

public final class VariableSymbol {
    private final String name;
    private final Class<?> type;

    public VariableSymbol(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}
