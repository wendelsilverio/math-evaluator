package com.github.wendelsilverio.mathevaluator.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperatorExpression implements Expression {

    private Operator operator;
    private List<String> expressions;

    public OperatorExpression(String expression) throws InvalidExpressionException {
        operator = OperatorFactory.getOperator(expression);

        if (operator == null) {
            throw new InvalidExpressionException(expression);
        }

        expressions = OperatorFactory.getExpressions(expression, operator);
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
