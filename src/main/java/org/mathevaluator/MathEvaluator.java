package org.mathevaluator;

import java.util.HashMap;
import java.util.Map;

import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluator {

    public static Map<String, Double> evaluate(String expression, Map<String, String> variables) throws InvalidExpressionException {
	HashMap<String, Expression> vars = new HashMap<>();
	variables.entrySet().forEach(entry-> {
	    vars.put(entry.getKey(), new Evaluator(entry.getValue()));
	});

	Map<String, Double> results = new HashMap<>();
	results.put(expression, new Evaluator(expression).interpret(vars));
	return results;
    }

    public static Map<String, Double> evaluate(String expression) throws InvalidExpressionException  {
	return evaluate(expression, new HashMap<>());
    }

}
