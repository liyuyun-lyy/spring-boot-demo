package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationConfigTest {

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
        assertNotNull(environment);
    }

    @Test
    void applicationPropertiesShouldLoad() {
        String port = environment.getProperty("server.port");
        if (port != null) {
            assertEquals("8080", port);
        }
    }

    @Test
    void springApplicationNameShouldBeSet() {
        String appName = environment.getProperty("spring.application.name");
        if (appName != null) {
            assertEquals("demo", appName);
        }
    }
}