package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LogTest {

    @Test
    public void logarithm() throws InvalidExpressionException {
        assertEquals(11.018368, new Evaluator("log(60984.1)").interpret(new HashMap<>()), 1E-6);
    }

}
