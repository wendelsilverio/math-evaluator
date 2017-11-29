package org.mathevaluator.operators;

import java.util.List;

public class Addition extends Operator {

    public Addition() {
        super("+", Type.Operator, 1);
    }

    @Override
    public Double calculate(List<Double> values) {
        return values.get(0) + values.get(1);
    }

    @Override
    public String getName() {
        return "+";
    }
}

