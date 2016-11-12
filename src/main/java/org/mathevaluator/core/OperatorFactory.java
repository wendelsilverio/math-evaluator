package org.mathevaluator.core;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Operator> getOperators() {
	if(operators == null) {
	    initializeOperators();
	}
	return operators;
    }

}
