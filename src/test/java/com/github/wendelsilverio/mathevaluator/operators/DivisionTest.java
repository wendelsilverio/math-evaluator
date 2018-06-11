package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class DivisionTest {

    @Test
    public void division() throws InvalidExpressionException {
        assertEquals(5.0, new Evaluator("10/2").interpret(new HashMap<>()), 1E-1);
    }

}
