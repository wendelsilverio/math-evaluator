package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Evaluator;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SubtractionTest {

    @Test
    public void subtraction() throws Exception  {
        assertEquals(2.0, new Evaluator("3-1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void subtractionThreeValues() throws Exception  {
        assertEquals(-3.0, new Evaluator("3-4-2").interpret(new HashMap<>()), 1E-1);
    }

}
