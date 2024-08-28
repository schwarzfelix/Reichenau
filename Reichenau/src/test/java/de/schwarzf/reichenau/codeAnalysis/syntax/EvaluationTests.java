package de.schwarzf.reichenau.codeAnalysis.syntax;

import de.schwarzf.reichenau.codeAnalysis.Compilation;
import de.schwarzf.reichenau.codeAnalysis.EvaluationResult;
import de.schwarzf.reichenau.codeAnalysis.VariableSymbol;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluationTests {

    @Test
    public void mytest(){
        mytest(" 1", 1);
        mytest("+1", 1);
        mytest("-1", -1);

        mytest("14 + 12", 26);
        mytest("12 -  3", 9);
        mytest(" 4 *  2", 8);
        mytest(" 9 /  3", 3);

        mytest("(10)", 10);

        mytest("12 == 3", false);
        mytest("12 != 3", true);
        mytest(" 3 == 3", true);
        mytest(" 3 != 3", false);

        mytest("true  == true" , true);
        mytest("true  == false", false);
        mytest("true  != false", true);
        mytest("false == false", true);
        mytest("false != true" , true);

        mytest("true  && true" , true);
        mytest("true  && false", false);
        mytest("false && true" , false);
        mytest("false && false", false);

        mytest("true  || true" , true);
        mytest("true  || false", true);
        mytest("false || true" , true);
        mytest("false || false", false);

        mytest("  true" , true);
        mytest("  false", false);
        mytest("! true" , false);
        mytest("! false", true);

        mytest("(a = 10) * a", 100);
        mytest("(b = true) && b", true);
    }
    public void mytest(String text, Object expectedValue){

        SyntaxTree syntaxTree = SyntaxTree.parse(text);
        Compilation compilation = new Compilation(syntaxTree);
        HashMap<VariableSymbol, Object> variables = new HashMap<>();
        EvaluationResult result = compilation.evaluate(variables);

        assertEquals(0, result.getDiagnostics().size());
        assertEquals(expectedValue, result.getValue());
    }
}
