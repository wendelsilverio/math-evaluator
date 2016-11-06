package org.mathevaluator.api;

import org.json.JSONObject;
import org.mathevaluator.core.MathEvaluator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

	@RequestMapping(value = "me", method = RequestMethod.GET, produces = "application/json")
	public String function(@RequestParam(value = "f") String expression) {
		JSONObject json = new JSONObject();
		json.append("r", MathEvaluator.f(expression));
		return json.toString();
	}

}
