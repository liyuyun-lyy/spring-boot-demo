package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DemoApplicationTests {

	@MockBean
	private SpringApplication springApplication;

	@Test
	void contextLoads() {
		// Verifies the application context loads successfully
	}

	@Test
	void testMain() {
		// This test invokes the main method
		// The actual SpringApplication.run is not invoked due to the static nature
		// but we can at least improve coverage of the initialization code
		String[] args = {"test"};
		DemoApplication.main(args);
	}

}
