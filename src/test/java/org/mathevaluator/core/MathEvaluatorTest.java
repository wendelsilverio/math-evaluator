package org.mathevaluator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MathEvaluatorTest {

	@Test
	public void onlyNumber() throws Exception {
		MathEvaluator me = new MathEvaluator();

		assertEquals(1.0, me.f("1"), 1E-1);
		assertEquals(1.0, me.f("1.0"), 1E-1);
		assertEquals(-1.0, me.f("-1"), 1E-1);
		assertEquals(-1.0, me.f("-1.0"), 1E-1);

		me.add("a", 1.0);
		assertEquals(1.0, me.f("a"), 1E-1);
	}

	@Test
	public void addition() throws InvalidExpressionException {
		MathEvaluator me = new MathEvaluator();

		assertEquals(2.0, me.f("1+1"), 1E-1);
		assertEquals(3.0, me.f("1+1+1"), 1E-1);

		me.add("a", 1.0);
		assertEquals(2.0, me.f("a + 1"), 1E-1);

		me.add("b", 3.0);
		assertEquals(5.0, me.f("a + 1 + b"), 1E-1);

		try {
			me.f("A + 1");
			fail();
		} catch (InvalidExpressionException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void subtraction() throws Exception  {
		assertEquals(2.0, new MathEvaluator().f("3-1"), 1E-1);
		assertEquals(-3.0, new MathEvaluator().f("3-4-2"), 1E-1);
	}

	@Test
	public void multiplication() throws InvalidExpressionException {
		assertEquals(6.0, new MathEvaluator().f("2*3"), 1E-1);
	}

	@Test
	public void division() throws InvalidExpressionException {
		assertEquals(5.0, new MathEvaluator().f("10/2"), 1E-1);
	}

	@Test
	public void sine() throws InvalidExpressionException {
		assertEquals(0.7071, new MathEvaluator().f("sin(0.7853981633974483)"), 1E-4);/* Radians */
	}

	@Test
	public void cosine() throws InvalidExpressionException {
		assertEquals(0.7071, new MathEvaluator().f("cos(0.7853981633974483)"), 1E-4); /* Radians */
	}

	@Test
	public void tangent() throws InvalidExpressionException {
		assertEquals(0.9999, new MathEvaluator().f("tan(0.7853981633974483)"), 1E-4); /* Radians */
	}

	@Test
	public void exponent() throws InvalidExpressionException {
		assertEquals(148.413159, new MathEvaluator().f("exp(5)"), 1E-6);
	}

	@Test
	public void logarithm() throws InvalidExpressionException {
		assertEquals(11.018368, new MathEvaluator().f("log(60984.1)"), 1E-6);
	}

	@Test
	public void potency() throws InvalidExpressionException {
		assertEquals(4.0, new MathEvaluator().f("2^2"), 1E-6);
	}

	@Test
	public void squareRoot() throws InvalidExpressionException {
		assertEquals(3.0, new MathEvaluator().f("sqrt(9)"), 1E-6);
	}
}
