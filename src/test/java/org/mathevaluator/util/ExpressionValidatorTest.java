package org.mathevaluator.util;

import org.junit.Test;
import org.mathevaluator.core.InvalidExpressionException;

public class ExpressionValidatorTest {

    @Test
    public void parentheses() throws InvalidExpressionException {
	ExpressionValidator.validateParentheses("(A)");
    }

    @Test(expected=InvalidExpressionException.class)
    public void invalidOpenParentheses() throws InvalidExpressionException {
	ExpressionValidator.validateParentheses("A)");
    }

    @Test(expected=InvalidExpressionException.class)
    public void invalidClosedParentheses() throws InvalidExpressionException {
	ExpressionValidator.validateParentheses("(A");
    }

}
