package org.mathevaluator.util;

import static org.springframework.util.StringUtils.countOccurrencesOf;

public class ExpressionCleaner {

    public static String cleanSpaces(String expression) {
        return expression.replaceAll("\\s", "");
    }

    public static String cleanParentheses(String expression) {
        return cleanDelimiter(expression, "(", ")");
    }

    public static String cleanBrackets(String expression) {
        return cleanDelimiter(expression, "[", "]");
    }

    public static String cleanDelimiter(String expression, String delimiterBegin, String delimiterEnd) {
        String f = expression;
        if ((expression.length() > 2) && f.startsWith(delimiterBegin) && f.endsWith(delimiterEnd)
                && countOccurrencesOf(expression, delimiterBegin) == countOccurrencesOf(expression, delimiterEnd)) {
            f = f.substring(1, f.length() - 1);
        }
        if (!f.equals(expression)) {
            return cleanParentheses(f);
        } else {
            return f;
        }
    }

}
