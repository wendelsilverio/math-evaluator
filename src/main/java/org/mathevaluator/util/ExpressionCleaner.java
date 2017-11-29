package org.mathevaluator.util;

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
                && expression.chars().filter(ch->ch == delimiterBegin.charAt(0)).count() == expression.chars().filter(ch->ch==delimiterEnd.charAt(0)).count()) {
            f = f.substring(1, f.length() - 1);
        }
        if (!f.equals(expression)) {
            return cleanParentheses(f);
        } else {
            return f;
        }
    }

}
