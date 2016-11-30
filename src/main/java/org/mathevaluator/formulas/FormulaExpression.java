package org.mathevaluator.formulas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;
import org.mathevaluator.interpreter.NumberExpression;

public class FormulaExpression implements Expression {

    private FormulaRepository formulaRepository = new FormulaRepository();
    private Set<Formula> formulas;
    private Formula formula;
    private List<String> expressions;

    public FormulaExpression(String expression) throws InvalidExpressionException {
	formulas = formulaRepository.getFormulas();

	formula = getFormula(expression);

	if(formula == null) {
	    throw new InvalidExpressionException(expression);
	}

	expressions = getExpressions(expression, formula);
    }

    @Override
    public Double interpret(Map<String, Expression> variables) throws InvalidExpressionException {
	List<Double> values = new ArrayList<>();

	for (String expression : expressions) {
	    values.add(new Evaluator(expression).interpret(variables));
	}

	Map<String, Expression> formulaVariables = new HashMap<>();
	for (int i = 0; i < expressions.size(); i++) {
	    formulaVariables.put(formula.getVariables()[i], new NumberExpression(values.get(i)));
	}

	return new Evaluator(formula.getExpression()).interpret(formulaVariables);
    }

    private List<String> getExpressions(String expression, Formula formula) throws InvalidExpressionException {
	List<String> expressions = new ArrayList<>();
	if (formula != null) {
	    if (checkBrackets(expression.substring(formula.getName().length())) != 0) {
		throw new InvalidExpressionException("Error during parsing... missing brackets in " + expression);
	    }
	    String expSemOp = expression.substring(formula.getName().length());
	    String expSemBrackets = removeBrackets(expSemOp);
	    List<String> splitExp = new ArrayList<>();
	    int j = 0;

	    int inBrackets = 0;
	    for (int i = 0; i < expSemBrackets.length(); i++) {
		if (expSemBrackets.charAt(i) == '(') {
		    inBrackets++;
		} else if (expSemBrackets.charAt(i) == ')') {
		    inBrackets--;
		} else if ((inBrackets == 0) && (expSemBrackets.charAt(i) == ',')) {
		    splitExp.add(expSemBrackets.substring(j, i));
		    j = i + 1;
		}
	    }

	    splitExp.add(expSemBrackets.substring(j));
	    for (String exp : splitExp) {
		expressions.add(exp);
	    }
	}
	return expressions;
    }

    private Formula getFormula(String expression) {
	Formula formula = null;
	int sLength = expression.length();
	int inBrackets = 0;

	for (int i = 0; i < sLength; i++) {
	    if (expression.charAt(i) == '(') {
		inBrackets++;
	    } else if (expression.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		Formula f = getFormula(expression, i);
		if (f != null) {
		    // if first operator or lower priority operator
		    if ((formula == null)) {
			formula = f;
		    }
		}
	    }
	}
	return formula;
    }

    private Formula getFormula(String s, int start) {
	Formula selectedFormula = null;
	int indexOp = Integer.MAX_VALUE;
	for (Formula formula : formulas) {
	    Pattern p = Pattern.compile(formula.getName() + "\\(");
	    Matcher m = p.matcher(s);
	    if (m.find() && ((s.indexOf(formula.getName()) < indexOp)
		    || (s.indexOf(formula.getName()) == indexOp && formula.getName().length() > 1))) {
		selectedFormula = formula;
		indexOp = s.indexOf(formula.getName());
	    }
	}
	return selectedFormula;
    }

    public static String removeBrackets(String s) {
	String res = s;
	if ((s.length() > 2) && res.startsWith("(") && res.endsWith(")")
		&& (checkBrackets(s.substring(1, s.length() - 1)) == 0)) {
	    res = res.substring(1, res.length() - 1);
	}
	if (!res.equals(s)) {
	    return removeBrackets(res);
	} else {
	    return res;
	}
    }

    protected static int checkBrackets(String s) {
	int sLength = s.length();
	int inBracket = 0;

	for (int i = 0; i < sLength; i++) {
	    if ((s.charAt(i) == '(') && (inBracket >= 0)) {
		inBracket++;
	    } else if (s.charAt(i) == ')') {
		inBracket--;
	    }
	}

	return inBracket;
    }

}
