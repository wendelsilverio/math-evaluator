package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SineTest {

    @Test
    public void sine() throws InvalidExpressionException {
        assertEquals(0.7071, new Evaluator("sin(0.7853981633974483)").interpret(new HashMap<>()), 1E-4);/* Radians */
    }

}
