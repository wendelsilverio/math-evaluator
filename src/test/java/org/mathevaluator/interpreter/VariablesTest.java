package org.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class VariablesTest {

    @Test
    public void variableAsExpression() throws InvalidExpressionException {
        HashMap<String, Expression> variables = new HashMap<>();
        variables.put("A", new OperatorExpression("B+C"));
        variables.put("B", new NumberExpression(2.0));
        variables.put("C", new NumberExpression(3.0));
        assertEquals(6.0, new Evaluator("A + 1").interpret(variables), 1E-1);
    }

}
