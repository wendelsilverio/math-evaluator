package com.github.wendelsilverio.mathevaluator.operators;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Power extends Operator {

    public Power() {
        super("^", Type.Operator, 2);
    }

    @Override
    public Double calculate(List<Double> values) {
        return new com.github.wendelsilverio.mathevaluator.functions.Power().calculate(values);
    }

    @Override
    public String getName() {
        return "^";
    }
}
