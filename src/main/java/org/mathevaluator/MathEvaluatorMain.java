package org.mathevaluator;

import org.mathevaluator.interpreter.InvalidExpressionException;

public class MathEvaluatorMain {

    public static void main(String[] args) throws InvalidExpressionException {
	if (args.length == 0) {
	    printHelp();
	} else {
	    String expression = args[0];
	    System.out.println("Expression: " + expression);
	    System.out.println(MathEvaluator.evaluate(expression));
	}
    }

    private static void printHelp() {
	System.out.println("MathEvaluator");
	System.out.println("Created by Wendel Silverio <wendelsilverio@gmail.com>");
	System.out.println();
	System.out.println("Command: <expression> <variables...>");
    }

}
