package org.mathevaluator.operators;

import java.util.List;

public class Cosine extends Operator {

    public Cosine() {
        super("cos", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.cos(values.get(0));
    }
}
