package org.mathevaluator.operators;

import org.mathevaluator.operators.Operator;

import java.util.List;

public class Addition extends Operator {

    public Addition(String name) {
        super(name, 1);
    }

    @Override
    public Double calculate(List<Double> values) {
        return values.get(0) + values.get(1);
    }
}
