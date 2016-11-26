package org.mathevaluator.interpreter;

import java.util.Map;

public class VariableExp implements Expression {

    private String name;

    public VariableExp(String name) {
	this.name = name;
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException {
	if (!variables.containsKey(name)) {
	    return 0.0; // Either return new Number(0).
	}
	return variables.get(name).interpret(variables);
    }

}
