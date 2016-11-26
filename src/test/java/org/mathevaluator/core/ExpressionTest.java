package org.mathevaluator.core;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ExpressionTest {

    @Test
    public void calculateWithParentheses() throws InvalidExpressionException {
	Evaluator evaluator = new Evaluator("a * (1 + b)");
	Map<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExp(2.0));
	variables.put("b", new NumberExp(3.0));
	assertEquals(8.0, evaluator.interpret(variables), 1E-1);
    }

    @Test
    public void calculateWithBrackets() throws InvalidExpressionException {
	Evaluator evaluator = new Evaluator("[a * (1 + b)]^2");
	Map<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExp(2.0));
	variables.put("b", new NumberExp(3.0));
	assertEquals(64.0, evaluator.interpret(variables), 1E-1);
    }

    @Test
    public void calculateWithBraces() throws InvalidExpressionException {
	Evaluator evaluator = new Evaluator("{[a * (1 + b)]^2}*2");
	Map<String, Expression> variables = new HashMap<>();
	variables.put("a", new NumberExp(2.0));
	variables.put("b", new NumberExp(3.0));
	assertEquals(128.0, evaluator.interpret(variables), 1E-1);
    }

}
