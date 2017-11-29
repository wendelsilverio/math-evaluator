package org.mathevaluator.operators;

import org.mathevaluator.operators.Operator;

import java.util.List;

public class Multiplication extends Operator {

    public Multiplication(String name) {
        super(name, 2);
    }

    @Override
    public Double calculate(List<Double> values) {
        return values.get(0) * values.get(1);
    }
}
