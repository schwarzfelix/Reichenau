package de.schwarzf.reichenau.codeAnalysis;

import java.util.ArrayList;

public final class EvaluationResult {

    public final ArrayList<Diagnostic> diagnostics;
    public final Object value;

    public EvaluationResult(ArrayList<Diagnostic> diagnostics, Object value) {
        this.diagnostics = diagnostics;
        this.value = value;
    }

    public ArrayList<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    public Object getValue() {
        return value;
    }

}
