package org.mathevaluator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class OperatorsTest {

    @Test
    public void onlyNumber() throws Exception {
	assertEquals(1.0, Expression.evaluate("1"), 1E-1);
	assertEquals(1.0, Expression.evaluate("1.0"), 1E-1);
	assertEquals(-1.0, Expression.evaluate("-1"), 1E-1);
	assertEquals(-1.0, Expression.evaluate("-1.0"), 1E-1);
    }

    @Test
    public void additionOnlyNumbers() throws InvalidExpressionException {
	assertEquals(2.0, Expression.evaluate("1+1"), 1E-1);
	assertEquals(3.0, Expression.evaluate("1+1+1"), 1E-1);
    }

    @Test
    public void additionOneVariable() throws InvalidExpressionException {
	Expression expression = new Expression("a + 1");
	expression.add("a", 1.0);
	assertEquals(2.0, expression.evaluate(), 1E-1);
	try {
	    Expression.evaluate("A + 1");
	    fail();
	} catch (InvalidExpressionException e) {
	    System.out.println(e.getMessage());
	}
    }

    @Test
    public void variableNotFound() throws InvalidExpressionException {
	try {
	    Expression.evaluate("A + 1");
	    fail();
	} catch (InvalidExpressionException e) {
	    System.out.println(e.getMessage());
	}
    }

    @Test
    public void additionTwoVariable() throws InvalidExpressionException {
	Expression expression = new Expression("a + 1 + b");
	expression.add("a", 1.0);
	expression.add("b", 3.0);
	assertEquals(5.0, expression.evaluate(), 1E-1);
    }

    @Test
    public void subtraction() throws Exception  {
	assertEquals(2.0, Expression.evaluate("3-1"), 1E-1);
	assertEquals(-3.0, Expression.evaluate("3-4-2"), 1E-1);
    }

    @Test
    public void multiplication() throws InvalidExpressionException {
	assertEquals(6.0, Expression.evaluate("2*3"), 1E-1);
    }

    @Test
    public void division() throws InvalidExpressionException {
	assertEquals(5.0, Expression.evaluate("10/2"), 1E-1);
    }

    @Test
    public void potency() throws InvalidExpressionException {
	assertEquals(4.0, Expression.evaluate("2^2"), 1E-6);
    }

}
