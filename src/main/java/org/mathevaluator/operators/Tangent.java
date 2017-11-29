package org.mathevaluator.operators;

import java.util.List;

public class Tangent extends Operator {

    public Tangent() {
        super("tan", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.tan(values.get(0));
    }
}
