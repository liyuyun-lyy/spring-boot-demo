package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
class DemoApplicationUnitTest {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> {
            // Spring context should load without issues
        });
    }

    @Test
    void mainMethodShouldStartApplication() {
        assertDoesNotThrow(() -> {
            DemoApplication.main(new String[]{});
        });
    }

    @Test
    void mainMethodWithArgsShouldStartApplication() {
        assertDoesNotThrow(() -> {
            DemoApplication.main(new String[]{"--server.port=8081"});
        });
    }
}