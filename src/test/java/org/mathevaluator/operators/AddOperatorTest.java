package org.mathevaluator.operators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mathevaluator.core.MathEvaluator;
import org.mathevaluator.core.MathEvaluatorException;

public class AddOperatorTest {

	@Test
	public void onlyIntegerNumbers() throws MathEvaluatorException {
		assertEquals(2, MathEvaluator.f("1+1"));
		assertEquals(3, MathEvaluator.f("1+2"));
		assertEquals(3, MathEvaluator.f("1 + 2"));
	}

}