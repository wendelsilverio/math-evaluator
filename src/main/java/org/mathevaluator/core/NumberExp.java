package org.mathevaluator.core;

import java.util.Map;

public class NumberExp implements Expression {

    private Double value;

    public NumberExp(Double value) {
	this.value = value;
    }

    @Override
    public Double interpret(Map<String, Expression> variables) {
	return value;
    }

}
