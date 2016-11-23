package org.mathevaluator.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionsTest {

    @Test
    public void sine() throws InvalidExpressionException {
	assertEquals(0.7071, Expression.evaluate("sin(0.7853981633974483)"), 1E-4);/* Radians */
    }

    @Test
    public void cosine() throws InvalidExpressionException {
	assertEquals(0.7071, Expression.evaluate("cos(0.7853981633974483)"), 1E-4); /* Radians */
    }

    @Test
    public void tangent() throws InvalidExpressionException {
	assertEquals(0.9999, Expression.evaluate("tan(0.7853981633974483)"), 1E-4); /* Radians */
    }

    @Test
    public void exponent() throws InvalidExpressionException {
	assertEquals(148.413159, Expression.evaluate("exp(5)"), 1E-6);
    }

    @Test
    public void logarithm() throws InvalidExpressionException {
	assertEquals(11.018368, Expression.evaluate("log(60984.1)"), 1E-6);
    }

    @Test
    public void squareRoot() throws InvalidExpressionException {
	assertEquals(3.0, Expression.evaluate("sqrt(9)"), 1E-6);
    }
}
