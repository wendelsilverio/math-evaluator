package org.mathevaluator.api;

import java.util.Map;
import java.util.Map.Entry;

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
	public String function(@RequestParam Map<String, String> params) {
		JSONObject json = new JSONObject();
		try {
			MathEvaluator me = new MathEvaluator();
			for (Entry<String, String> entry : params.entrySet()) {
				if(entry.getKey().equals("f") || entry.getValue().isEmpty()) {
					continue;
				}
				me.add(entry.getKey(), Double.valueOf(entry.getValue()));
			}

			json.append("result", me.f(params.get("f")));
		} catch (JSONException | InvalidExpressionException e) {
			json.append("error", e.getMessage());
			e.printStackTrace();
		}
		return json.toString();
	}

}
