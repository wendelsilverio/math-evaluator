package org.mathevaluator.util;

import static org.springframework.util.StringUtils.countOccurrencesOf;

import org.mathevaluator.interpreter.InvalidExpressionException;

public class ExpressionValidator {

    public static void validateParentheses(String formula) throws InvalidExpressionException {
	if(countOccurrencesOf(formula, "(") != countOccurrencesOf(formula, ")")) {
	    throw new InvalidExpressionException("Wrong number of parentheses in '" + formula + "'");
	}
    }

}
