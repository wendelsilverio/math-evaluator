package org.mathevaluator.operators;

import java.util.List;

public class Sqrt extends Operator {

    public Sqrt() {
        super("sqrt", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.sqrt(values.get(0));
    }
}
