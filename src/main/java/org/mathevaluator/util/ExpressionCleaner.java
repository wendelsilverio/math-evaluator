package org.mathevaluator.util;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class ExpressionCleaner {

    public static String cleanSpaces(String formula) {
	return formula.replaceAll("\\s", "");
    }

    public static String cleanParentheses(String formula) {
	String f = formula;
	if ((formula.length() > 2) && f.startsWith("(") && f.endsWith(")")
		&& countOccurrencesOf(formula, "(") == countOccurrencesOf(formula, ")")) {
	    f = f.substring(1, f.length() - 1);
	}
	if (!f.equals(formula)) {
	    return cleanParentheses(f);
	} else {
	    return f;
	}
    }

}
