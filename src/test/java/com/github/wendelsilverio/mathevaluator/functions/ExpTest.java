package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ExpTest {

    @Test
    public void exponent() throws InvalidExpressionException {
        assertEquals(148.413159, new Evaluator("exp(5)").interpret(new HashMap<>()), 1E-6);
    }

}
