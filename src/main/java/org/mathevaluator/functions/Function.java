package org.mathevaluator.functions;

import java.util.List;

public interface Function {

    String getName();

    Double calculate(List<Double> values);

}
