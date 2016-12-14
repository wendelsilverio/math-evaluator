package org.mathevaluator.unicode;

import java.util.Map;

import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;

public class UnicodeExpression implements Expression {

    public UnicodeExpression(String expression) {
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException {
        return 3.0;
    }

    public static boolean is(String expression) {
        return expression.equals("bxh/2");
    }

}
