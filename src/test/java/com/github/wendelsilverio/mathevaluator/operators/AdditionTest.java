package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.MathEvaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AdditionTest {

    @Test
    public void additionOnlyNumbers() throws InvalidExpressionException {
        assertEquals(2.0, new Evaluator("1 + 1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void additionOnlyNumbersMe() throws InvalidExpressionException {
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
}
