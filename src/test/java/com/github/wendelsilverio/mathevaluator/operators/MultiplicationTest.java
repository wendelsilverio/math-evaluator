package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import com.github.wendelsilverio.mathevaluator.interpreter.InvalidExpressionException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MultiplicationTest {

    @Test
    public void multiplication() throws InvalidExpressionException {
        assertEquals(6.0, new Evaluator("2*3").interpret(new HashMap<>()), 1E-1);
    }
}
