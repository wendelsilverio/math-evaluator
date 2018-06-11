package com.github.wendelsilverio.mathevaluator.interpreter;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class NumbersTest {

    @Test
    public void oneInteger() throws Exception {
	    assertEquals(1.0, new Evaluator("1").interpret(new HashMap<>()), 1E-1);
    }

    @Test
    public void oneDouble() throws Exception {
    	assertEquals(1.0, new Evaluator("1.0").interpret(new HashMap<>()), 1E-1);
    }

}
