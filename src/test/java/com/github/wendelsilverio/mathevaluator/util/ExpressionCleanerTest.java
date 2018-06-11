package com.github.wendelsilverio.mathevaluator.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpressionCleanerTest {

    @Test
    public void cleanSpaces() {
	assertEquals("A+1", ExpressionCleaner.cleanSpaces("A + 1"));
    }

    @Test
    public void cleanParentheses() {
	assertEquals("A+1", ExpressionCleaner.cleanParentheses("(A+1)"));
    }

    @Test
    public void cleanWithoutParentheses() {
	assertEquals("A+1", ExpressionCleaner.cleanParentheses("A+1"));
    }

    @Test
    public void cleanInvalidCloseParentheses() {
	assertEquals("A+1)", ExpressionCleaner.cleanParentheses("A+1)"));
    }

    @Test
    public void cleanInvalidOpenParentheses() {
	assertEquals("(A+1", ExpressionCleaner.cleanParentheses("(A+1"));
    }

}
