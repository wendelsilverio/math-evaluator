package org.mathevaluator.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class FunctionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    public void f() {
	String body = restTemplate.getForObject("/?f=1+1", String.class);
	assertThat(body).isEqualTo("{\"result\":[2]}");
    }

    //@Test
    public void fWithVariables() {
	String body = restTemplate.getForObject("/?f=a+b&a=2&b=3", String.class);
	assertThat(body).isEqualTo("{\"result\":[5]}");
    }

}
