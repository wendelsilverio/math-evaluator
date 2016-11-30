package org.mathevaluator.formulas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONArray;

@RestController
public class FormulaController {

    private FormulaRepository formulaRepository = new FormulaRepository();

    @RequestMapping(value = "/formulas", method = RequestMethod.GET, produces = "application/json")
    public String getAll() {
	JSONArray jsonArray = new JSONArray();
	jsonArray.addAll(formulaRepository.getFormulas());
	return jsonArray.toString();
    }

    @RequestMapping(value = "/formula", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody String addFormula(Formula formula) {
	System.out.println("NAME: "+formula.getName());
	System.out.println("EXPRESSION: "+formula.getExpression());
	return "OK";
    }

}
