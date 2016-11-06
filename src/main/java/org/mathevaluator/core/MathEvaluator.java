package org.mathevaluator.core;

import java.util.StringTokenizer;

public class MathEvaluator {

	public static Number f(String expression) {
		StringTokenizer st = new StringTokenizer(expression, "+");

		String valueA = st.nextToken().trim();
		String valueB = st.nextToken().trim();

		Integer a = Integer.parseInt(valueA);
		Integer b = Integer.parseInt(valueB);

		return a + b;
	}

}
