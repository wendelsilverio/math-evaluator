package org.mathevaluator.core;

import java.util.HashMap;
import java.util.Map;

public class MathEvaluator {

    private ExpressionNode expressionNode;
    private static Map<String, Double> variables;

    public MathEvaluator() {
	init();
    }

    private void init() {
	variables = new HashMap<>();
    }

    public void add(String variable, Double value) {
	variables.put(variable, value);
    }

    public static Map<String, Double> getVariables() {
	return variables;
    }

    public Double f(String expression) throws InvalidExpressionException {
	if (expression == null) {
	    return null;
	}

	if(variables.isEmpty()) {
	    expressionNode = new ExpressionNode(expression);
	} else {
	    expressionNode = new ExpressionNode(expression);
	}
	return expressionNode.evaluate();
    }

}