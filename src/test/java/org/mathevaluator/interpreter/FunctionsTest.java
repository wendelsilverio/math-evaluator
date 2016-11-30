package org.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class FunctionsTest {

    @Test
    public void sine() throws InvalidExpressionException {
	assertEquals(0.7071, new Evaluator("sin(0.7853981633974483)").interpret(new HashMap<>()), 1E-4);/* Radians */
    }

    @Test
    public void cosine() throws InvalidExpressionException {
	assertEquals(0.7071, new Evaluator("cos(0.7853981633974483)").interpret(new HashMap<>()), 1E-4); /* Radians */
    }

    @Test
    public void tangent() throws InvalidExpressionException {
	assertEquals(0.9999, new Evaluator("tan(0.7853981633974483)").interpret(new HashMap<>()), 1E-4); /* Radians */
    }

    @Test
    public void exponent() throws InvalidExpressionException {
	assertEquals(148.413159, new Evaluator("exp(5)").interpret(new HashMap<>()), 1E-6);
    }

    @Test
    public void logarithm() throws InvalidExpressionException {
	assertEquals(11.018368, new Evaluator("log(60984.1)").interpret(new HashMap<>()), 1E-6);
    }

    @Test
    public void squareRoot() throws InvalidExpressionException  {
	assertEquals(3.0, new Evaluator("sqrt(9)").interpret(new HashMap<>()), 1E-6);
    }
}
