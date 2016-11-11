package org.mathevaluator.core;

public class Operator {
	@Override
	public String toString() {
		return "Operator [op=" + op + "]";
	}

	String op;
	final int type;
	final int priority;

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

	public String getOp() {
		return op;
	}
}
