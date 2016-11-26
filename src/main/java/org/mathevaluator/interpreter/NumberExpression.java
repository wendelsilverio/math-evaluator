package org.mathevaluator.interpreter;

import java.util.Map;

public class NumberExpression implements Expression {

    private Double value;

    public NumberExpression(Double value) {
	this.value = value;
    }

    @Override
    public Double interpret(Map<String, Expression> variables) {
	return value;
    }

}
