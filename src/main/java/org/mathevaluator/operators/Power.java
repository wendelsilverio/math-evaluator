package org.mathevaluator.operators;

import java.util.List;

public class Power extends Operator {

    public Power() {
        super("pow", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.pow(values.get(0), values.get(1));
    }

    @Override
    public String getName() {
        return "pow";
    }
}
