package org.mathevaluator.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mathevaluator.util.Node;

public class OperatorExpression extends Node<Expression> implements Expression {

    private Operator operator;
    private List<String> expressions;

    public OperatorExpression(String formula) throws InvalidExpressionException {
	operator = OperatorFactory.getOperator(formula);

	if(operator == null) {
	    throw new InvalidExpressionException(formula);
	}

	expressions = OperatorFactory.getExpressions(formula, operator);
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException {
	List<Double> values = new ArrayList<>();
	for (String expression : expressions) {
	    values.add(new Evaluator(expression).interpret(variables));
	}
	return operator.calculate(values);
    }

}
