package org.mathevaluator;

import static java.lang.String.join;
import static org.mathevaluator.util.ExpressionCleaner.cleanBrackets;
import static org.mathevaluator.util.ExpressionCleaner.cleanSpaces;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;
import org.mathevaluator.interpreter.NumberExpression;

public class MathEvaluator {

    public static Map<String, String> evaluate(String expression, Map<String, String> variables) throws InvalidExpressionException {

        String varArray = null;
        String valueArray = null;
        Map<String, Expression> vars = new HashMap<>();
        for (Entry<String, String> entry : variables.entrySet()) {
            String variable = entry.getKey();
            String value = entry.getValue();
            if (value.matches("\\[[0-9 .,]+\\]")) {
                varArray = variable;
                valueArray = value;
            } else {
                vars.put(variable, new Evaluator(value));
            }
        }

        Map<String, String> results = new HashMap<>();
        if(varArray == null) {
            results.put(expression, new Evaluator(expression).interpret(vars).toString());
        } else {
            String[] valuesStr = cleanBrackets(cleanSpaces(valueArray)).split(",");
            String[] resultsArray = new String[valuesStr.length];
            for (int i = 0; i < valuesStr.length; i++) {
                Map<String, Expression> map = new HashMap<>();
                map.put(varArray, new NumberExpression(Double.valueOf(valuesStr[i])));
                resultsArray[i] = new Evaluator(expression).interpret(map).toString();
            }
            results.put(expression, "["+join(",", resultsArray)+"]");
        }
        return results;
    }

    public static Map<String, String> evaluate(String expression) throws InvalidExpressionException  {
        return evaluate(expression, new HashMap<>());
    }

}
