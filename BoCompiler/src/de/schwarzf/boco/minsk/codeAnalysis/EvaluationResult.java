package de.schwarzf.boco.minsk.codeAnalysis;

import javax.tools.Diagnostic;
import java.util.ArrayList;

public final class EvaluationResult {

    public final ArrayList<String> diagnostics;
    public final Object value;

    public EvaluationResult(ArrayList<String> diagnostics, Object value) {
        this.diagnostics = diagnostics;
        this.value = value;
    }

    public ArrayList<String> getDiagnostics() {
        return diagnostics;
    }

    public Object getValue() {
        return value;
    }

}
