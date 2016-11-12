package org.mathevaluator.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.mathevaluator.core.InvalidExpressionException;
import org.mathevaluator.core.MathEvaluator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public String function(@RequestParam(required=true, value = "f") String expression) {
		JSONObject json = new JSONObject();
		try {
			json.append("result", new MathEvaluator().f(expression));
		} catch (JSONException | InvalidExpressionException e) {
			json.append("error", e.getMessage());
			e.printStackTrace();
		}
		return json.toString();
	}

}
