package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;

public class MathEvaluator {

	private ExpressionNode expressionNode;
	private String expression;
	private static List<Operator> operators = null;

	public MathEvaluator() {
		init();
	}

	private void init() {
		if (operators == null) {
			initializeOperators();
		}
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Double f(String expression) {
		this.expression = expression;
		Double value = getValue();
		return value;
	}


	public Double getValue() {
		if (expression == null) {
			return null;
		}

		try {
			expressionNode = new ExpressionNode(expression);
			return evaluate(expressionNode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Double evaluate(ExpressionNode expressionNode) throws MathEvaluatorException {
		if (expressionNode.hasOperator()) {
			Double[] values = new Double[expressionNode.getChildren().size()];
			for (int i = 0; i < expressionNode.getChildren().size(); i++) {
				values[i] = evaluate(expressionNode.getChildren().get(i).getData());
			}
			expressionNode.setValue(evaluateExpression(expressionNode.getOperator(), values));
		} else if (!expressionNode.isLeaf() && (expressionNode.getValue() == null)) {
			throw new MathEvaluatorException("Invalid Expression: " + expressionNode.getString());
		}

		return expressionNode.getValue();
	}

	private static Double evaluateExpression(Operator o, Double[] values) {
		Double res = null;
		String op = o.getOperator();
		if ("+".equals(op)) {
			res = values[0] + values[1];
		} else if ("-".equals(op)) {
			res = values[0] - values[1];
		} else if ("*".equals(op)) {
			res = values[0] * values[1];
		} else if ("/".equals(op)) {
			if ((values[0] == 0.0) && (values[1] == 0.0)) {
				res = 0.0;
			} else {
				res = values[0] / values[1];
			}
		} else if ("^".equals(op)) {
			res = StrictMath.pow(values[0], values[1]);
		} else if ("cos".equals(op)) {
			res =  StrictMath.cos(values[0]);
		} else if ("sin".equals(op)) {
			res = StrictMath.sin(values[0]);
		} else if ("tan".equals(op)) {
			res = StrictMath.tan(values[0]);
		} else if ("sqrt".equals(op)) {
			res = StrictMath.sqrt(values[0]);
		} else if ("log".equals(op)) {
			res = StrictMath.log(values[0]);
		} else if ("exp".equals(op)) {
			res = StrictMath.exp(values[0].doubleValue());
		}

		return res;
	}

	private void initializeOperators() {
		operators = new ArrayList<>();
		operators.add(new Operator("+", 2, 0));
		operators.add(new Operator("-", 2, 0));
		operators.add(new Operator("*", 2, 10));
		operators.add(new Operator("/", 2, 10));
		operators.add(new Operator("sin", 1, 20));
		operators.add(new Operator("cos", 1, 20));
		operators.add(new Operator("tan", 1, 20));
		operators.add(new Operator("exp", 1, 20));
		operators.add(new Operator("log", 1, 20));
		operators.add(new Operator("^", 2, 12));
		operators.add(new Operator("sqrt", 1, 20));
	}

	public static List<Operator> getOperators() {
		return operators;
	}

}