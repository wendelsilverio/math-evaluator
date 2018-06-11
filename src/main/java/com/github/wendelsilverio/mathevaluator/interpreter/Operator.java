package com.github.wendelsilverio.mathevaluator.interpreter;

import java.util.List;

public abstract class Operator {

    public enum Type {
        Operator, Function;
    }

    private final String name;
    private final Type type;
    private final int priority;

    public Operator(String name, Type type, int priority) {
        this.name = name;
        this.type = type;
        this.priority = priority;
    }

    public abstract Double calculate(List<Double> values);

    public abstract String getName();

    public Type getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Operator [name=" + name + ", type=" + type + ", priority=" + priority + "]";
    }
}