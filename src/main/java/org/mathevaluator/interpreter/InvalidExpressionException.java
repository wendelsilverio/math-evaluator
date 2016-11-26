package org.mathevaluator.interpreter;

public class InvalidExpressionException extends Exception {

	private static final long serialVersionUID = 616247988149970779L;

	public InvalidExpressionException(String expression) {
		super(expression);
	}

}
