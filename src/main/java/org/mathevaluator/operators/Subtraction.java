package org.mathevaluator.operators;

import java.util.List;

public class Subtraction extends Operator {

    public Subtraction() {
        super("-", Operator.Type.Operator, 1);
    }

    @Override
    public Double calculate(List<Double> values) {
        return values.get(0) - values.get(1);
    }
}
