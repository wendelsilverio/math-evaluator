package org.mathevaluator;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluatorTest {

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
