package org.mathevaluator.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;
import org.mathevaluator.interpreter.Evaluator;
import org.mathevaluator.interpreter.Expression;
import org.mathevaluator.interpreter.InvalidExpressionException;
import org.mathevaluator.interpreter.NumberExpression;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public String function(@RequestParam Map<String, String> params) {
        JSONObject json = new JSONObject();
        try {
            Evaluator evaluator = new Evaluator(params.get("f"));
            Map<String, Expression> variables = new HashMap<>();
            for (Entry<String, String> entry : params.entrySet()) {

                if(entry.getKey().equals("f") || entry.getValue().isEmpty()) {
                    continue;
                }
                variables.put(entry.getKey(), new NumberExpression(Double.valueOf(entry.getValue())));
            }

            json.append("result", evaluator.interpret(variables));
        } catch (JSONException | InvalidExpressionException e) {
            json.append("error", e.getMessage());
            e.printStackTrace();
        }
        return json.toString();
    }

}
