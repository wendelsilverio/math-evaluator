package wendelsilverio.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="https://wendelsilverio.github.io")
@SpringBootApplication
public class MathEvaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathEvaluatorApplication.class, args);
	}
}
