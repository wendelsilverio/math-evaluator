package org.mathevaluator.core;

import java.util.Map;

public interface Expression {

    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException;

}
