package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PowerTest {

    @Test
    public void potency() throws InvalidExpressionException {
        assertEquals(4.0, new Evaluator("2^2").interpret(new HashMap<>()), 1E-1);
    }

}
