package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Cosine extends Operator {

    public Cosine() {
        super("cos", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.cos(values.get(0));
    }

    @Override
    public String getName() {
        return "cos";
    }
}
