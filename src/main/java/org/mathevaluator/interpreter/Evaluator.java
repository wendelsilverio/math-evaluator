package org.mathevaluator.interpreter;

import static org.mathevaluator.util.ExpressionCleaner.cleanParentheses;
import static org.mathevaluator.util.ExpressionCleaner.cleanSpaces;
import static org.mathevaluator.util.ExpressionValidator.isValidParentheses;

import java.util.Map;

import org.mathevaluator.formulas.FormulaExpression;
import org.mathevaluator.formulas.FormulaRepository;
import org.mathevaluator.operators.OperatorExpression;

public class Evaluator implements Expression {

    private Expression expressionTree;
    private String expression;

    private FormulaRepository formulaRepository = new FormulaRepository();

    public Evaluator(String expression) {
	this.expression = expression;
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException  {
	String cleanedExpression = null;
	cleanedExpression = cleanSpaces(expression);
	cleanedExpression = cleanParentheses(cleanedExpression);
	if(!isValidParentheses(cleanedExpression)) {
	    throw new InvalidExpressionException("Wrong number of parentheses in '" + expression + "'");
	}

	if (isNumberExpression(cleanedExpression)) {
	    expressionTree = new NumberExpression(Double.parseDouble(cleanedExpression));
	} else if(variables.containsKey(cleanedExpression)) {
	    expressionTree = variables.get(cleanedExpression);
	} else if (formulaRepository.isFormula(cleanedExpression)) {
	    expressionTree = new FormulaExpression(cleanedExpression);
	} else {
	    expressionTree = new OperatorExpression(cleanedExpression);
	}

	return expressionTree.interpret(variables);
    }

    private boolean isNumberExpression(String formula) {
	try {
	    Double.parseDouble(formula);
	} catch (NumberFormatException e) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Evaluator [formula=" + expression + "]";
    }

}