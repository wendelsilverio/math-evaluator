package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Multiplication extends Operator {

    public Multiplication() {
        super("*", Operator.Type.Operator, 2);
    }

    @Override
    public Double calculate(List<Double> values) {
        return values.get(0) * values.get(1);
    }

    @Override
    public String getName() {
        return "*";
    }
}
