package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mathevaluator.util.Node;

public class ExpressionNode extends Node<ExpressionNode> {

    private String expression;
    private Operator operator;
    private Double result;

    public ExpressionNode(String expression) throws InvalidExpressionException {
	init(expression, null);
    }

    private ExpressionNode(String expression, ExpressionNode parent) throws InvalidExpressionException {
	init(expression, parent);
    }

    private void init(String expression, ExpressionNode parent) throws InvalidExpressionException {
	expression = removeIllegalCharacters(expression);
	expression = removeBrackets(expression);
	expression = addZero(expression);
	if (checkBrackets(expression) != 0) {
	    throw new InvalidExpressionException("Wrong number of brackets in " + expression);
	}
	setParent(parent);
	this.expression = expression;
	result = getDouble(expression);

	if (result != null) {
	    return;
	}

	int sLength = expression.length();
	int inBrackets = 0;
	int startOperator = 0;

	for (int i = 0; i < sLength; i++) {
	    if (expression.charAt(i) == '(') {
		inBrackets++;
	    } else if (expression.charAt(i) == ')') {
		inBrackets--;
	    } else if (inBrackets == 0) {
		Operator o = getOperator(expression, i);
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
		if (checkBrackets(expression.substring(operator.getName().length())) == 0) {
		    String expSemOp = expression.substring(operator.getName().length());
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
			addChild(new ExpressionNode(exp, this));
		    }
		    return;
		} else {
		    throw new InvalidExpressionException("Error during parsing... missing brackets in " + expression);
		}
	    } else if ((startOperator > 0) && (Operator.Type.Operator.equals(operator.getType()))) {
		addChild(new ExpressionNode(expression.substring(0, startOperator), this));
		addChild(new ExpressionNode(expression.substring(startOperator + operator.getName().length()), this));
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
	if (hasOperator()) {
	    List<Double> values = new ArrayList<>();
	    for (Node<ExpressionNode> node : getChildren()) {
		Double result = node.getData().evaluate();
		values.add(result);
	    }
	    setValue(getOperator().calculate(values));
	} else if (!isLeaf() && (getValue() == null)) {
	    throw new InvalidExpressionException(expression);
	} else if (isLeaf() && (getValue() == null)) {
	    throw new InvalidExpressionException("Variable '" + toString() + "' not found!");
	}

	return getValue();
    }

    protected boolean hasOperator() {
	return operator != null;
    }

    protected boolean hasLeft() {
	return getChildren().get(0) != null;
    }

    protected ExpressionNode getLeft() {
	return getChildren().get(0).getData();
    }

    protected boolean hasRight() {
	return getChildren().get(1) != null;
    }

    protected ExpressionNode getRight() {
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

    private Double getDouble(String s) {
	if (s == null) {
	    return null;
	}
	try {
	    return new Double(Double.parseDouble(s));
	} catch (NumberFormatException nfe) {
	    if (MathEvaluator.getVariables().containsKey(s)) {
		return MathEvaluator.getVariables().get(s);
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
	return expression;
    }

}
