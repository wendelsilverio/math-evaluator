package org.mathevaluator.util;

public class ExpressionValidator {

    public static boolean isValidParentheses(String expression) {
        return expression.chars().filter(ch -> ch == '(').count() == expression.chars().filter(ch -> ch == ')').count();
    }

}
