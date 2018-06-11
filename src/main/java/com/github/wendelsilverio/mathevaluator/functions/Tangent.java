package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Tangent extends Operator {

    public Tangent() {
        super("tan", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.tan(values.get(0));
    }

    @Override
    public String getName() {
        return "tan";
    }
}
