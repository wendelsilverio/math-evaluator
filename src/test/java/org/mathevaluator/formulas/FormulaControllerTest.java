package org.mathevaluator.formulas;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//TODO Verify storage tests to Travis
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class FormulaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    //    @Before
    public void setup() {
        restTemplate.put("/formula", new Formula("squareArea","side^2","side"));
    }

    //    @Test
    public void formulas() {
        String body = restTemplate.getForObject("/formulas", String.class);
        JSONArray jsonArray = new JSONArray(body);
        assertEquals(1, jsonArray.toList().size());
    }

    //    @Test
    public void addFormula() {
        restTemplate.put("/formula", new Formula("squareArea","side^2","side"));
    }

}
