package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Exp extends Operator {
    public Exp() {
        super("exp", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.exp(values.get(0).doubleValue());
    }

    @Override
    public String getName() {
        return "exp";
    }
}
