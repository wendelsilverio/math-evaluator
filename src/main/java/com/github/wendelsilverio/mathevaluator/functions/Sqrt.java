package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Sqrt extends Operator {

    public Sqrt() {
        super("sqrt", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.sqrt(values.get(0));
    }

    @Override
    public String getName() {
        return "sqrt";
    }
}
