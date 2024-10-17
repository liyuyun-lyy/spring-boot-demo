package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		int a = 1;
		a = a;
		int b = 1;
		b = b;
		SpringApplication.run(DemoApplication.class, args);
	}

}
