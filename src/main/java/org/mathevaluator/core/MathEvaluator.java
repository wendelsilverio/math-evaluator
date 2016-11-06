package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MathEvaluator {

	public static Number f(String expression) throws MathEvaluatorException {

		try {

			if(!expression.contains("+")) {
				return Integer.valueOf(expression);
			}

			StringTokenizer st = new StringTokenizer(expression, "+");

			List<Integer> numbers = new ArrayList<>();
			while(st.hasMoreTokens()) {
				numbers.add(Integer.parseInt(st.nextToken().trim()));
			}

			Integer r = 0;
			for (Integer n : numbers) {
				r+=n;
			}

			return r;
		} catch (NoSuchElementException nsee) {
			throw new MathEvaluatorException("Invalid expression: " + expression);
		}
	}

}
