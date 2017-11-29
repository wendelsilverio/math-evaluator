package org.mathevaluator.operators;

import java.util.List;

public class Operator {

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

    public Double calculate(List<Double> values) {
        Double res = null;

        switch (name) {
//            case "+":
//                res = values.get(0) + values.get(1);
//                break;
//            case "-":
//                res = values.get(0) - values.get(1);
//                break;
//            case "*":
//                res = values.get(0) * values.get(1);
//                break;
//            case "/":
//                if ((values.get(0) == 0.0) && (values.get(1) == 0.0)) {
//                    res = 0.0;
//                } else {
//                    res = values.get(0) / values.get(1);
//                }
//                break;
//            case "pow":
//                res = StrictMath.pow(values.get(0), values.get(1));
//                break;
//            case "cos":
//                res = StrictMath.cos(values.get(0));
//                break;
//            case "sin":
//                res = StrictMath.sin(values.get(0));
//                break;
//            case "tan":
//                res = StrictMath.tan(values.get(0));
//                break;
//            case "sqrt":
//                res = StrictMath.sqrt(values.get(0));
//                break;
//            case "log":
//                res = StrictMath.log(values.get(0));
//                break;
//            case "exp":
//                res = StrictMath.exp(values.get(0).doubleValue());
//                break;
            default:
                break;
        }

        return res;
    }

    public String getName() {
        return name;
    }

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