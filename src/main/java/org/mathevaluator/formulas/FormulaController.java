package org.mathevaluator.formulas;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormulaController {

    private FormulaRepository formulaRepository = new FormulaRepository();

    @RequestMapping(value = "/formulas", method = RequestMethod.GET, produces = "application/json")
    public String getAll() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(formulaRepository.getFormulas());
        return jsonArray.toString();
    }

}
