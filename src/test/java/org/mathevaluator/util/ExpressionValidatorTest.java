package org.mathevaluator.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExpressionValidatorTest {

    @Test
    public void parentheses() {
	assertTrue(ExpressionValidator.isValidParentheses("(A)"));
    }

    @Test
    public void invalidOpenParentheses() {
	assertFalse(ExpressionValidator.isValidParentheses("A)"));
    }

    @Test
    public void invalidClosedParentheses() {
	assertFalse(ExpressionValidator.isValidParentheses("(A"));
    }

}
