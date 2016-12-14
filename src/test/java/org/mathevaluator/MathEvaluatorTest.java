package org.mathevaluator;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mathevaluator.formulas.Formula;
import org.mathevaluator.formulas.FormulaRepository;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluatorTest {

    private FormulaRepository formulaRepository = new FormulaRepository();

    @Before
    public void setup() {
        formulaRepository.save(new Formula("rectangleArea", "base*height", "base", "height"));
        System.out.println(formulaRepository.getFileData());
    }

    @Test
    public void additionOnlyNumbers() throws InvalidExpressionException {
        assertEquals("2.0", MathEvaluator.evaluate("1 + 1").get("1 + 1"));
    }

    @Test
    public void additionWithVariable() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("A", "1");
        assertEquals("2.0", MathEvaluator.evaluate("A + 1", variables).get("A + 1"));
    }

    @Test
    public void rectangleArea() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("base", "2");
        variables.put("height", "3");
        assertEquals("6.0", MathEvaluator.evaluate("rectangleArea(base,height)", variables).get("rectangleArea(base,height)"));
    }

    @Test
    public void rectangleAreaAlias() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("A", "3");
        variables.put("B", "4");
        assertEquals("15.0", MathEvaluator.evaluate("rectangleArea(A,B) + 3", variables).get("rectangleArea(A,B) + 3"));
    }

    @Test
    public void rectangleAreaWithoutVariable() throws InvalidExpressionException {
        assertEquals("11.0", MathEvaluator.evaluate("rectangleArea(2,4) + 3").get("rectangleArea(2,4) + 3"));
    }

    @Test
    public void variableAsArray() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("A", "[1,2,3]");
        assertEquals("[2.0,3.0,4.0]", MathEvaluator.evaluate("A+1", variables).get("A+1"));
    }

    @Test
    public void secondDegree() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("x", "[1,2,3]");
        assertEquals("[4.0,9.0,16.0]", MathEvaluator.evaluate("x^2 + 2*x + 1", variables).get("x^2 + 2*x + 1"));
    }

    @Test
    public void sqrtOfArray() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("x", "[4,9,16]");
        assertEquals("[2.0,3.0,4.0]", MathEvaluator.evaluate("sqrt(x)", variables).get("sqrt(x)"));
    }

}
