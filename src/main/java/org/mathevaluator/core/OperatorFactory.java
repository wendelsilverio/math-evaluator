package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperatorFactory {

    private static List<Operator> operators;

    protected static void initializeOperators() {
	operators = new ArrayList<>();
	operators.add(new Operator("+", Operator.Type.Operator, 0));
	operators.add(new Operator("-", Operator.Type.Operator, 0));
	operators.add(new Operator("*", Operator.Type.Operator, 10));
	operators.add(new Operator("/", Operator.Type.Operator, 10));
	operators.add(new Operator("sin", Operator.Type.Function, 20));
	operators.add(new Operator("cos", Operator.Type.Function, 20));
	operators.add(new Operator("tan", Operator.Type.Function, 20));
	operators.add(new Operator("exp", Operator.Type.Function, 20));
	operators.add(new Operator("log", Operator.Type.Function, 20));
	operators.add(new Operator("^", Operator.Type.Operator, 12));
	operators.add(new Operator("sqrt", Operator.Type.Function, 20));
    }

    static Operator getOperator(String s, int start) {
	List<Operator> operators = OperatorFactory.getOperators();
	Operator operat = null;
	int indexOp = Integer.MAX_VALUE;
	for (Operator operator : operators) {
	    if (operator.getType().equals(Operator.Type.Function)) {
		Pattern p = Pattern.compile(operator.getName() + "\\(");
		Matcher m = p.matcher(s);
		if (m.find() && ((s.indexOf(operator.getName()) < indexOp)
			|| (s.indexOf(operator.getName()) == indexOp && operator.getName().length() > 1))) {
		    operat = operator;
		    indexOp = s.indexOf(operator.getName());
		}
	    } else {
		String temp = s.substring(start);
		if (temp.startsWith(operator.getName())) {
		    return operator;
		}
	    }
	}
	return operat;
    }

    public static List<String> getExpressions(String formula) throws InvalidExpressionException {
	int sLength = formula.length();
	int inBrackets = 0;
	int startOperator = 0;

	Operator operator = null;
	for (int i = 0; i < sLength; i++) {
	    if (formula.charAt(i) == '(') {
		inBrackets++;
	    } else if (formula.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		Operator o = getOperator(formula, i);
		if (o != null) {
		    // if first operator or lower priority operator
		    if ((operator == null) || (operator.getPriority() >= o.getPriority())) {
			operator = o;
			startOperator = i;
		    }
		}
	    }
	}

	List<String> expressions = new ArrayList<>();
	if (operator != null) {
	    if (Operator.Type.Function.equals(operator.getType())) {
		if (checkBrackets(formula.substring(operator.getName().length())) != 0) {
		    throw new InvalidExpressionException("Error during parsing... missing brackets in " + formula);
		}
		String expSemOp = formula.substring(operator.getName().length());
		String expSemBrackets = removeBrackets(expSemOp);
		List<String> splitExp = new ArrayList<>();
		int j = 0;

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
	    } else if ((startOperator > 0) && (Operator.Type.Operator.equals(operator.getType()))) {
		expressions.add(formula.substring(0, startOperator));
		expressions.add(formula.substring(startOperator + operator.getName().length()));
	    }
	}
	return expressions;
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

    public static List<Operator> getOperators() {
	if(operators == null) {
	    initializeOperators();
	}
	return operators;
    }

    public static Operator getOperator(String formula) throws InvalidExpressionException {
	Operator operator = null;
	int sLength = formula.length();
	int inBrackets = 0;

	for (int i = 0; i < sLength; i++) {
	    if (formula.charAt(i) == '(') {
		inBrackets++;
	    } else if (formula.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		Operator o = getOperator(formula, i);
		if (o != null) {
		    // if first operator or lower priority operator
		    if ((operator == null) || (operator.getPriority() >= o.getPriority())) {
			operator = o;
		    }
		}
	    }
	}
	return operator;
    }

    public static List<String> getExpressions(String formula, Operator operator) throws InvalidExpressionException {

	int inBrackets = 0;

	int startOperator = getStartOperatorIndex(formula, operator);

	List<String> expressions = new ArrayList<>();
	if (operator != null) {
	    if (Operator.Type.Function.equals(operator.getType())) {
		if (checkBrackets(formula.substring(operator.getName().length())) != 0) {
		    throw new InvalidExpressionException("Error during parsing... missing brackets in " + formula);
		}
		String expSemOp = formula.substring(operator.getName().length());
		String expSemBrackets = removeBrackets(expSemOp);
		List<String> splitExp = new ArrayList<>();
		int j = 0;

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
	    } else if ((startOperator > 0) && (Operator.Type.Operator.equals(operator.getType()))) {
		expressions.add(formula.substring(0, startOperator));
		expressions.add(formula.substring(startOperator + operator.getName().length()));
	    }
	}
	return expressions;
    }

    public static int getStartOperatorIndex(String formula, Operator operator) throws InvalidExpressionException {
	int sLength = formula.length();
	int inBrackets = 0;
	int startOperator = 0;

	for (int i = 0; i < sLength; i++) {
	    if (formula.charAt(i) == '(') {
		inBrackets++;
	    } else if (formula.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		if (OperatorFactory.getOperator(formula, i) != null) {
		    // if first operator or lower priority operator
		    if ((operator == null) || (operator.getPriority() >= OperatorFactory.getOperator(formula, i).getPriority())) {
			startOperator = i;
		    }
		}
	    }
	}
	return startOperator;
    }

}
