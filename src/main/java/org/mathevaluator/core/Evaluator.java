package org.mathevaluator.core;

import static org.mathevaluator.util.ExpressionCleaner.cleanParentheses;
import static org.mathevaluator.util.ExpressionCleaner.cleanSpaces;

import java.util.Map;

import org.mathevaluator.util.ExpressionValidator;

public class Evaluator implements Expression {

    private Expression expressionTree;

    private String formula;

    public Evaluator(String formula) {
	this.formula = formula;
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException {
	String cleanedFormula = null;
	cleanedFormula = cleanSpaces(formula);
	cleanedFormula = cleanParentheses(cleanedFormula);
	ExpressionValidator.validateParentheses(cleanedFormula);

	try {
	    expressionTree = new NumberExp(Double.parseDouble(cleanedFormula));
	} catch (NumberFormatException nfe) {
	    if(variables.containsKey(cleanedFormula)) {
		expressionTree = variables.get(cleanedFormula);
	    }
	}

	if(expressionTree == null) {
	    expressionTree = new OperatorExp(cleanedFormula);
	}

	return expressionTree.interpret(variables);
    }

    @Override
    public String toString() {
	return "Evaluator [formula=" + formula + "]";
    }

}
