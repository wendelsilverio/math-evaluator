package org.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;
import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;
import org.mathevaluator.interpreter.NumberExpression;

public class OperatorsTest {

    @Test
    public void oneInteger() throws Exception {
	assertEquals(1.0, new Evaluator("1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void oneDouble() throws Exception {
	assertEquals(1.0, new Evaluator("1.0").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void additionOnlyNumbers() throws InvalidExpressionException {
	assertEquals(2.0, new Evaluator("1 + 1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void additionOneVariable() throws InvalidExpressionException {
	HashMap<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExpression(1.0));
	assertEquals(2.0, new Evaluator("a + 1").interpret(variables), 1E-1);
    }

    @Test
    public void variableNotFound() throws InvalidExpressionException {
	try {
	    new Evaluator("A + 1").interpret(new HashMap<>());
	    fail();
	} catch (InvalidExpressionException e) {
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

    @Test
    public void subtraction() throws Exception  {
	assertEquals(2.0, new Evaluator("3-1").interpret(new HashMap<>()), 1E-1);
	assertEquals(-3.0, new Evaluator("3-4-2").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void multiplication() throws InvalidExpressionException {
	assertEquals(6.0, new Evaluator("2*3").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void division() throws InvalidExpressionException {
	assertEquals(5.0, new Evaluator("10/2").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void potency() throws InvalidExpressionException {
	Evaluator evaluator = new Evaluator("2^2");
	assertEquals(4.0, evaluator.interpret(new HashMap<>()), 1E-1);
    }

}
