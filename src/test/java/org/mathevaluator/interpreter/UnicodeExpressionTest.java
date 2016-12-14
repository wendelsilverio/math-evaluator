package org.mathevaluator.interpreter;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class UnicodeExpressionTest {

    @Test
    public void multiplicationPoint() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator("2 ⦁  2").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void multiplicationX() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator("2 × 2").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void division() throws InvalidExpressionException {
        Assert.assertEquals(1, new Evaluator("2 ÷ 2").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void squareRoot() throws InvalidExpressionException {
        Assert.assertEquals(3, new Evaluator("√9").interpret(new HashMap<>()).intValue());
    }

}
