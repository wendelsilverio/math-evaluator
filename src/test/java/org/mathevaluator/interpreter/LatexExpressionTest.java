package org.mathevaluator.interpreter;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class LatexExpressionTest {

    @Test
    public void multiplication() throws InvalidExpressionException {
        Assert.assertEquals(4, new Evaluator(" $2 \\times 2$").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void division() throws InvalidExpressionException {
        Assert.assertEquals(1, new Evaluator("$2 \\div 2$").interpret(new HashMap<>()).intValue());
    }

    @Test
    public void squareRoot() throws InvalidExpressionException {
        Assert.assertEquals(3, new Evaluator("$\\sqrt 9$").interpret(new HashMap<>()).intValue());
    }
}
