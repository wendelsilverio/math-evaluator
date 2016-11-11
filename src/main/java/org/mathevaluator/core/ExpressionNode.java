package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mathevaluator.util.Node;

public class ExpressionNode extends Node<ExpressionNode> {

	private static final int TWO_VARIABLES = 2;
	private static final int ONE_VARIABLE = 1;

	private String expression;
	private Operator operator;
	private Double result;

	public ExpressionNode(String expression) throws Exception {
		init(null, expression);
	}

	public ExpressionNode(ExpressionNode parent, String expression) throws Exception {
		init(parent, expression);
	}

	private void init(ExpressionNode parent, String s) throws Exception {
		s = removeIllegalCharacters(s);
		s = removeBrackets(s);
		s = addZero(s);
		if (checkBrackets(s) != 0) {
			throw new Exception("Wrong number of brackets in [" + s + "]");
		}
		setParent(parent);
		expression = s;
		result = getDouble(s);

		if(result!=null) {
			return;
		}

		int sLength = s.length();
		int inBrackets = 0;
		int startOperator = 0;

		for (int i = 0; i < sLength; i++) {
			if (s.charAt(i) == '(') {
				inBrackets++;
			} else if (s.charAt(i) == ')') {
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
			if (operator.getType() == ONE_VARIABLE) {
				if (checkBrackets(s.substring(operator.getOperator().length())) == 0) {
					String expSemOp = s.substring(operator.getOperator().length());
					String expSemBrackets = removeBrackets(expSemOp);
					List<String> splitExp = new ArrayList<>();
					int j = 0;

					for (int i = 0; i < expSemBrackets.length(); i++) {
						if (expSemBrackets.charAt(i) == '(') {
							inBrackets++;
						} else if (expSemBrackets.charAt(i) == ')') {
							inBrackets--;
						} else if ((inBrackets == 0) && (expSemBrackets.charAt(i) == ',')) {
							splitExp.add(expSemBrackets.substring(j,i));
							j = i+1;
						}
					}
					splitExp.add(expSemBrackets.substring(j));
					for (String exp : splitExp) {
						addChild(new ExpressionNode(this, exp));
					}
					return;
				} else {
					throw new Exception("Error during parsing... missing brackets in [" + s + "]");
				}
			}
			else if ((startOperator > 0) && (operator.getType() == TWO_VARIABLES)) {
				addChild(new ExpressionNode(this, s.substring(0, startOperator)));
				addChild(new ExpressionNode(this, s.substring(startOperator + operator.getOperator().length())));
			}
		}
	}

	private Operator getOperator(String s, int start) {
		List<Operator> operators = MathEvaluator.getOperators();
		Operator operat = null;
		int indexOp = Integer.MAX_VALUE;
		for (Operator operator : operators) {
			if (operator.getType() == 1) {
				Pattern p = Pattern.compile(operator.op + "\\(");
				Matcher m = p.matcher(s);
				if (m.find() && ((s.indexOf(operator.op) < indexOp) || (s.indexOf(operator.op) == indexOp && operator.op.length() > 1))) {
					operat = operator;
					indexOp = s.indexOf(operator.op);
				}
			}else{
				String temp = s.substring(start);
				if (temp.startsWith(operator.getOperator())) {
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

	protected String getString() {
		return expression;
	}

	public String removeBrackets(String s) {
		String res = s;
		if ((s.length() > 2) && res.startsWith("(") && res.endsWith(")") && (checkBrackets(s.substring(1, s.length() - 1)) == 0)) {
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
			return null;
		}
	}

	public Double getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "Expression: " + expression;
	}

}
