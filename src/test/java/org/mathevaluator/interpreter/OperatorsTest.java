package org.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class OperatorsTest {

    @Test
    public void oneInteger() throws Exception {
	assertEquals(1.0, new Evaluator("1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void oneDouble() throws Exception {
	assertEquals(1.0, new Evaluator("1.0").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void additionOnlyNumbers() throws InvalidExpressionException {
	assertEquals(2.0, new Evaluator("1 + 1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void subtraction() throws Exception  {
	assertEquals(2.0, new Evaluator("3-1").interpret(new HashMap<>()), 1E-1);
	assertEquals(-3.0, new Evaluator("3-4-2").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void multiplication() throws InvalidExpressionException {
	assertEquals(6.0, new Evaluator("2*3").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void division() throws InvalidExpressionException {
	assertEquals(5.0, new Evaluator("10/2").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void potency() throws InvalidExpressionException {
	assertEquals(4.0, new Evaluator("pow(2,2)").interpret(new HashMap<>()), 1E-1);
    }

}
