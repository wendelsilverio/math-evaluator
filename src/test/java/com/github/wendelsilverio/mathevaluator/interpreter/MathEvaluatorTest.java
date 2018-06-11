package com.github.wendelsilverio.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.github.wendelsilverio.mathevaluator.MathEvaluator;
import org.junit.Test;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluatorTest {





    @Test
    public void secondDegree() throws InvalidExpressionException {
        Map<String, String> variables = new HashMap<>();
        variables.put("x", "[1,2,3]");
        assertEquals("[4.0,9.0,16.0]", MathEvaluator.evaluate("pow(x,2) + 2*x + 1", variables).get("pow(x,2) + 2*x + 1"));
    }



}
