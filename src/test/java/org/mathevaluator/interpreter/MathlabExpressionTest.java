package org.mathevaluator.interpreter;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathlabExpressionTest {

    @Test
    public void pointPlus() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator("2 .+ 2").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void plus() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator("plus(2,2)").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void pointMinus() throws InvalidExpressionException {
        Assert.assertEquals(0, new Evaluator("2 .- 2").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void minus() throws InvalidExpressionException {
        Assert.assertEquals(0, new Evaluator("minus(2,2)").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void multiplication() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator("times(2,2)").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void division() throws InvalidExpressionException {
        Assert.assertEquals(1, new Evaluator("rdivide(2,2)").interpret(new HashMap<>()).intValue());
    }
}
