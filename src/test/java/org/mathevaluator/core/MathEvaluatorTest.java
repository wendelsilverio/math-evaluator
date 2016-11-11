package org.mathevaluator.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MathEvaluatorTest {

	@Test
	public void onlyNumber() {
		assertEquals(1.0, new MathEvaluator().f("1"), 1E-1);
		assertEquals(1.0, new MathEvaluator().f("1.0"), 1E-1);
		assertEquals(-1.0, new MathEvaluator().f("-1"), 1E-1);
		assertEquals(-1.0, new MathEvaluator().f("-1.0"), 1E-1);
	}

	@Test
	public void addition() {
		assertEquals(2.0, new MathEvaluator().f("1+1"), 1E-1);
		assertEquals(3.0, new MathEvaluator().f("1+1+1"), 1E-1);
	}

	@Test
	public void subtraction() {
		assertEquals(2.0, new MathEvaluator().f("3-1"), 1E-1);
		assertEquals(-3.0, new MathEvaluator().f("3-4-2"), 1E-1);
	}

	@Test
	public void multiplication() {
		assertEquals(6.0, new MathEvaluator().f("2*3"), 1E-1);
	}

	@Test
	public void division() {
		assertEquals(5.0, new MathEvaluator().f("10/2"), 1E-1);
	}

	@Test
	public void sine() {
		assertEquals(0.7071, new MathEvaluator().f("sin(0.7853981633974483)"), 1E-4);/* Radians */
	}

	@Test
	public void cosine() {
		assertEquals(0.7071, new MathEvaluator().f("cos(0.7853981633974483)"), 1E-4); /* Radians */
	}

	@Test
	public void tangent() {
		assertEquals(0.9999, new MathEvaluator().f("tan(0.7853981633974483)"), 1E-4); /* Radians */
	}

	@Test
	public void exponent() {
		assertEquals(148.413159, new MathEvaluator().f("exp(5)"), 1E-6);
	}

	@Test
	public void logarithm() {
		assertEquals(11.018368, new MathEvaluator().f("log(60984.1)"), 1E-6);
	}

	@Test
	public void potency() {
		assertEquals(4.0, new MathEvaluator().f("2^2"), 1E-6);
	}

	@Test
	public void squareRoot() {
		assertEquals(3.0, new MathEvaluator().f("sqrt(9)"), 1E-6);
	}
}
