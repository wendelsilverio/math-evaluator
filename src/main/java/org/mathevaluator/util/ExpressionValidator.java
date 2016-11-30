package org.mathevaluator.util;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class ExpressionValidator {

    public static boolean isValidParentheses(String expression) {
	return countOccurrencesOf(expression, "(") == countOccurrencesOf(expression, ")");
    }

}
