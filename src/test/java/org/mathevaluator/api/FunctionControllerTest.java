package org.mathevaluator.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public class FunctionControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void function() {
		String body = restTemplate.getForObject("/me?f=1+1", String.class);
		assertThat(body).isEqualTo("{\"r\":[2]}");
	}

}
