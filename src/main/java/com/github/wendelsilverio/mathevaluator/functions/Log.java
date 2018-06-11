package com.github.wendelsilverio.mathevaluator.functions;

import com.github.wendelsilverio.mathevaluator.interpreter.Operator;

import java.util.List;

public class Log extends Operator {
    public Log() {
        super("log", Operator.Type.Function, 4);
    }

    @Override
    public Double calculate(List<Double> values) {
        return StrictMath.log(values.get(0));
    }

    @Override
    public String getName() {
        return "log";
    }
}
