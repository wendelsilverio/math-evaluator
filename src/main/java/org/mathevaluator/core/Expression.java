package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mathevaluator.util.Node;

public class Expression extends Node<Expression> {

    private String formula;
    private Operator operator;
    private Double result;
    private Map<String, Double> variables = new HashMap<>();

    public Expression(String formula) {
	this.formula = formula;
    }

    public Expression(String formula, Map<String, Double> variables) {
	this(formula);
	this.variables.putAll(variables);
    }

    private Expression(String formula, Expression parent) {
	this(formula);
	this.variables.putAll(parent.variables);
	setParent(parent);
    }

    private void init() throws InvalidExpressionException {
	String frml = removeIllegalCharacters(formula);
	frml = removeBrackets(frml);
	frml = addZero(frml);
	if (checkBrackets(frml) != 0) {
	    throw new InvalidExpressionException("Wrong number of brackets in " + frml);
	}
	result = getDouble(frml);

	if (result != null) {
	    return;
	}

	int sLength = frml.length();
	int inBrackets = 0;
	int startOperator = 0;

	for (int i = 0; i < sLength; i++) {
	    if (frml.charAt(i) == '(') {
		inBrackets++;
	    } else if (frml.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		Operator o = getOperator(frml, i);
		if (o != null) {
		    // if first operator or lower priority operator
		    if ((operator == null) || (operator.getPriority() >= o.getPriority())) {
			operator = o;
			startOperator = i;
		    }
		}
	    }
	}

	if (operator != null) {
	    if (Operator.Type.Function.equals(operator.getType())) {
		if (checkBrackets(frml.substring(operator.getName().length())) == 0) {
		    String expSemOp = frml.substring(operator.getName().length());
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
			addChild(new Expression(exp, this));
		    }
		    return;
		} else {
		    throw new InvalidExpressionException("Error during parsing... missing brackets in " + frml);
		}
	    } else if ((startOperator > 0) && (Operator.Type.Operator.equals(operator.getType()))) {
		addChild(new Expression(frml.substring(0, startOperator), this));
		addChild(new Expression(frml.substring(startOperator + operator.getName().length()), this));
	    }
	}
    }

    private Operator getOperator(String s, int start) {
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

    protected int checkBrackets(String s) {
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

    protected String addZero(String s) {
	if (s.startsWith("+") || s.startsWith("-")) {
	    int sLength = s.length();
	    for (int i = 0; i < sLength; i++) {
		if (getOperator(s, i) != null) {
		    return "0" + s;
		}
	    }
	}

	return s;
    }

    public Double evaluate() throws InvalidExpressionException {
	init();

	if (hasOperator()) {
	    List<Double> values = new ArrayList<>();
	    for (Node<Expression> node : getChildren()) {
		Double result = node.getData().evaluate();
		values.add(result);
	    }
	    setValue(getOperator().calculate(values));
	} else if (!isLeaf() && (getValue() == null)) {
	    throw new InvalidExpressionException(formula);
	} else if (isLeaf() && (getValue() == null)) {
	    throw new InvalidExpressionException("Variable '" + toString() + "' not found!");
	}

	return getValue();
    }

    public static Double evaluate(String formula) throws InvalidExpressionException {
	return new Expression(formula).evaluate();
    }

    protected boolean hasOperator() {
	return operator != null;
    }

    protected boolean hasLeft() {
	return getChildren().get(0) != null;
    }

    protected Expression getLeft() {
	return getChildren().get(0).getData();
    }

    protected boolean hasRight() {
	return getChildren().get(1) != null;
    }

    protected Expression getRight() {
	return getChildren().get(1).getData();
    }

    protected Operator getOperator() {
	return operator;
    }

    protected Double getValue() {
	return result;
    }

    protected void setValue(Double f) {
	result = f;
    }

    public String removeBrackets(String s) {
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

    public String removeIllegalCharacters(String s) {
	char[] illegalCharacters = { ' ' };
	String res = s;

	for (int j = 0; j < illegalCharacters.length; j++) {
	    int i = res.lastIndexOf(illegalCharacters[j], res.length());
	    while (i != -1) {
		String temp = res;
		res = temp.substring(0, i);
		res += temp.substring(i + 1);
		i = res.lastIndexOf(illegalCharacters[j], s.length());
	    }
	}
	return res;
    }

    private Double getDouble(String value) {
	if (value == null) {
	    return null;
	}
	try {
	    return new Double(Double.parseDouble(value));
	} catch (NumberFormatException nfe) {
	    if (variables.containsKey(value)) {
		return variables.get(value);
	    } else {
		return null;
	    }
	}
    }

    public Double getResult() {
	return result;
    }

    @Override
    public String toString() {
	return formula;
    }

    public void add(String variable, Double value) {
	variables.put(variable, value);
    }

}
