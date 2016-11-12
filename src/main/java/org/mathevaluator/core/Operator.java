package org.mathevaluator.core;

import java.util.List;

public class Operator {

    public enum Type {
	Operator, Function;
    }

    private final String name;
    private final Type type;
    private final int priority;

    public Operator(String name, Type type, int priority) {
	this.name = name;
	this.type = type;
	this.priority = priority;
    }

    public Double calculate(List<Double> values) {
	Double res = null;
	if ("+".equals(name)) {
	    res = values.get(0) + values.get(1);
	} else if ("-".equals(name)) {
	    res = values.get(0) - values.get(1);
	} else if ("*".equals(name)) {
	    res = values.get(0) * values.get(1);
	} else if ("/".equals(name)) {
	    if ((values.get(0) == 0.0) && (values.get(1) == 0.0)) {
		res = 0.0;
	    } else {
		res = values.get(0) / values.get(1);
	    }
	} else if ("^".equals(name)) {
	    res = StrictMath.pow(values.get(0), values.get(1));
	} else if ("cos".equals(name)) {
	    res = StrictMath.cos(values.get(0));
	} else if ("sin".equals(name)) {
	    res = StrictMath.sin(values.get(0));
	} else if ("tan".equals(name)) {
	    res = StrictMath.tan(values.get(0));
	} else if ("sqrt".equals(name)) {
	    res = StrictMath.sqrt(values.get(0));
	} else if ("log".equals(name)) {
	    res = StrictMath.log(values.get(0));
	} else if ("exp".equals(name)) {
	    res = StrictMath.exp(values.get(0).doubleValue());
	}

	return res;
    }

    public String getName() {
	return name;
    }

    public Type getType() {
	return type;
    }

    public int getPriority() {
	return priority;
    }

    @Override
    public String toString() {
	return "Operator [name=" + name + ", type=" + type + ", priority=" + priority + "]";
    }
}