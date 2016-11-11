package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathEvaluator {

	protected static List<Operator> operators = null;
	private Node node = null;
	private String expression = null;

	public MathEvaluator() {
		init();
	}

	private void init() {
		if (operators == null) {
			initializeOperators();
		}
	}

	public void setExpression(String s) {
		expression = s;
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
			node = new Node(expression);
			return evaluate(node);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Double evaluate(Node n) {
		if (n.hasOperator() && n.hasChild()) {
			if (n.getOperator().getType() == 1) {
				Double[] values = new Double[n.nodeList.size()];
				for (int i = 0; i < n.nodeList.size(); i++) {
					values[i] = evaluate(n.nodeList.get(i));
				}
				n.setValue(evaluateExpression(n.getOperator(), values));
			} else if (n.getOperator().getType() == 2) {
				n.setValue(evaluateExpression(n.getOperator(), evaluate(n.getLeft()), evaluate(n.getRight())));
			}
		} else if (!n.hasChild() && (n.getValue() == null)) {
			throw new ArithmeticException("Variável " + n.getString() + " não encontrada");
		}

		return n.getValue();
	}

	private static Double evaluateExpression(Operator o, Double[] values) {
		Double res = null;
		String op = o.getOperator();


		if ("cos".equals(op)) {
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

	private static Double evaluateExpression(Operator o, Double f1, Double f2) {
		String op = o.getOperator();
		Double res = null;

		if ("+".equals(op)) {
			res = f1 + f2;
		} else if ("-".equals(op)) {
			res = f1 - f2;
		} else if ("*".equals(op)) {
			res = f1 * f2;
		} else if ("/".equals(op)) {
			if ((f1 == 0.0) && (f2 == 0.0)) {
				res = 0.0;
			} else {
				res = f1 / f2;
			}
		} else if ("^".equals(op)) {
			res = StrictMath.pow(f1, f2);
		} else if ("cos".equals(op)) {
			res =  StrictMath.cos(f1);
		} else if ("sin".equals(op)) {
			res = StrictMath.sin(f1);
		} else if ("tan".equals(op)) {
			res = StrictMath.tan(f1);
		} else if ("sqrt".equals(op)) {
			res = StrictMath.sqrt(f1);
		} else if ("log".equals(op)) {
			res = StrictMath.log(f1);
		} else if ("exp".equals(op)) {
			res = StrictMath.exp(f1.doubleValue());
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

	protected List<Operator> getOperators() {
		return operators;
	}

	protected class Operator {
		@Override
		public String toString() {
			return "Operator [op=" + op + "]";
		}

		private String op;
		private final int type;
		private final int priority;

		public Operator(String o, int t, int p) {
			op = o;
			type = t;
			priority = p;
		}

		public String getOperator() {
			return op;
		}

		public void setOperator(String o) {
			op = o;
		}

		public int getType() {
			return type;
		}

		public int getPriority() {
			return priority;
		}
	}

	protected class Node {
		private static final int TWO_VARIABLES = 2;
		private static final int ONE_VARIABLE = 1;
		public String nString = null;
		public Operator nOperator = null;
		public Node nLeft = null;
		public Node nRight = null;
		public Node nParent = null;
		public int nLevel = 0;
		@Override
		public String toString() {
			return "Node [nString=" + nString + "]";
		}

		public Double nValue = null;
		private ArrayList<Node> nodeList;

		public Node(String s) throws Exception {
			init(null, s, 0);
		}

		public Node(Node parent, String s, int level) throws Exception {
			init(parent, s, level);
		}

		private void init(Node parent, String s, int level) throws Exception {
			s = removeIllegalCharacters(s);
			s = removeBrackets(s);
			s = addZero(s);
			if (checkBrackets(s) != 0) {
				throw new Exception("Wrong number of brackets in [" + s + "]");
			}

			nParent = parent;
			nString = s;
			nValue = getDouble(s);
			nLevel = level;
			int sLength = s.length();
			int inBrackets = 0;
			int startOperator = 0;

			for (int i = 0; i < sLength; i++) {
				if (s.charAt(i) == '(') {
					inBrackets++;
				} else if (s.charAt(i) == ')') {
					inBrackets--;
				} else if (inBrackets == 0) {
					Operator o = getOperator(nString, i);
					if (o != null) {
						// if first operator or lower priority operator
						if ((nOperator == null) || (nOperator.getPriority() >= o.getPriority())) {
							nOperator = o;
							startOperator = i;
						}
					}
				}
			}

			if (nOperator != null) {
				if (nOperator.getType() == ONE_VARIABLE) {
					if (checkBrackets(s.substring(nOperator.getOperator().length())) == 0) {
						String expSemOp = s.substring(nOperator.getOperator().length());
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
						nodeList = new ArrayList<>();
						for (String exp : splitExp) {
							nodeList.add(new Node(this, exp, nLevel+1));
						}
						return;
					} else {
						throw new Exception("Error during parsing... missing brackets in [" + s + "]");
					}
				}
				else if ((startOperator > 0) && (nOperator.getType() == TWO_VARIABLES)) {
					nLeft = new Node(this, s.substring(0, startOperator), nLevel + 1);
					nRight = new Node(this, s.substring(startOperator + nOperator.getOperator().length()), nLevel + 1);
				}
			}
		}

		private Operator getOperator(String s, int start) {
			ArrayList<Operator> operators = (ArrayList<Operator>) getOperators();
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

		protected boolean hasChild() {
			return (nLeft != null) || (nRight != null) || ((nodeList!=null) && !nodeList.isEmpty());
		}

		protected boolean hasOperator() {
			return nOperator != null;
		}

		protected boolean hasLeft() {
			return nLeft != null;
		}

		protected Node getLeft() {
			return nLeft;
		}

		protected boolean hasRight() {
			return nRight != null;
		}

		protected Node getRight() {
			return nRight;
		}

		protected Operator getOperator() {
			return nOperator;
		}

		protected int getLevel() {
			return nLevel;
		}

		protected Double getValue() {
			return nValue;
		}

		protected void setValue(Double f) {
			nValue = f;
		}

		protected String getString() {
			return nString;
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

	}

}