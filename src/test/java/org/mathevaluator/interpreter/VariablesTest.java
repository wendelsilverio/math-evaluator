package org.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;
import org.mathevaluator.operators.OperatorExpression;

public class VariablesTest {

    @Test
    public void variableAsExpression() throws InvalidExpressionException {
	HashMap<String, Expression> variables = new HashMap<>();
	variables.put("A", new OperatorExpression("B+C"));
	variables.put("B", new NumberExpression(2.0));
	variables.put("C", new NumberExpression(3.0));
	assertEquals(6.0, new Evaluator("A + 1").interpret(variables), 1E-1);
    }

    @Test
    public void additionOneVariable() throws InvalidExpressionException  {
	HashMap<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExpression(1.0));
	assertEquals(2.0, new Evaluator("a + 1").interpret(variables), 1E-1);
    }

    @Test
    public void variableNotFound() {
	try {
	    new Evaluator("A + 1").interpret(new HashMap<>());
	    fail();
	} catch (InvalidExpressionException e) {
	    /* OK! */
	    System.out.println(e);
	}
    }

    @Test
    public void additionTwoVariable() throws InvalidExpressionException {
	HashMap<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExpression(1.0));
	variables.put("b", new NumberExpression(3.0));
	assertEquals(5.0, new Evaluator("a + 1 + b").interpret(variables), 1E-1);
    }
}
