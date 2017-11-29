package org.mathevaluator.operators;

import java.util.List;

public class Division extends Operator {

    public Division() {
        super("/", Operator.Type.Operator, 2);
    }

    @Override
    public Double calculate(List<Double> values) {
        if ((values.get(0) == 0.0) && (values.get(1) == 0.0)) {
            return 0.0;
        } else {
            return values.get(0) / values.get(1);
        }
    }
}
