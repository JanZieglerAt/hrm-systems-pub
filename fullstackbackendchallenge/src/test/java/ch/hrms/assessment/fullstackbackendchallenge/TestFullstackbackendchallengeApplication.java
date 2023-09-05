package ch.hrms.assessment.fullstackbackendchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFullstackbackendchallengeApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestFullstackbackendchallengeApplication.class).run(args);
	}

}
