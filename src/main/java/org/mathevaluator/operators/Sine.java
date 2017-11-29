package org.mathevaluator.operators;

import java.util.List;

public class Sine extends Operator {

    public Sine() {
        super("sin", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.cos(values.get(0));
    }

    @Override
    public String getName() {
        return "sin";
    }
}
