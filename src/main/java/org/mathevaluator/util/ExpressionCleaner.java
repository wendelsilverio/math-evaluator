package org.mathevaluator.util;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class ExpressionCleaner {

    public static String cleanSpaces(String expression) {
	return expression.replaceAll("\\s", "");
    }

    public static String cleanParentheses(String expression) {
	String f = expression;
	if ((expression.length() > 2) && f.startsWith("(") && f.endsWith(")")
		&& countOccurrencesOf(expression, "(") == countOccurrencesOf(expression, ")")) {
	    f = f.substring(1, f.length() - 1);
	}
	if (!f.equals(expression)) {
	    return cleanParentheses(f);
	} else {
	    return f;
	}
    }

}
