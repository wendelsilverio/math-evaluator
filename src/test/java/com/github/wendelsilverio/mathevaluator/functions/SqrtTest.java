package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.MathEvaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SqrtTest {

    @Test
    public void squareRoot() throws InvalidExpressionException {
        assertEquals(3.0, new Evaluator("sqrt(9)").interpret(new HashMap<>()), 1E-6);
    }

    @Test
    public void sqrtOfArray() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("x", "[4,9,16]");
        assertEquals("[2.0,3.0,4.0]", MathEvaluator.evaluate("sqrt(x)", variables).get("sqrt(x)"));
    }

}
