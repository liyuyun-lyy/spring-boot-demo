package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/calculator";
    }

    @Test
    void testAddEndpoint() {
        String url = getBaseUrl() + "/add?a=5&b=3";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(8.0, response.getBody(), 0.0001);
    }

    @Test
    void testSubtractEndpoint() {
        String url = getBaseUrl() + "/subtract?a=10&b=4";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(6.0, response.getBody(), 0.0001);
    }

    @Test
    void testMultiplyEndpoint() {
        String url = getBaseUrl() + "/multiply?a=7&b=6";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(42.0, response.getBody(), 0.0001);
    }

    @Test
    void testDivideEndpoint() {
        String url = getBaseUrl() + "/divide?a=15&b=3";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5.0, response.getBody(), 0.0001);
    }

    @Test
    void testDivideByZeroEndpoint() {
        String url = getBaseUrl() + "/divide?a=5&b=0";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cannot divide by zero", response.getBody());
    }

    @Test
    void testFloatingPointOperations() {
        // Test addition with floating point numbers
        String addUrl = getBaseUrl() + "/add?a=2.5&b=3.7";
        ResponseEntity<Double> addResponse = restTemplate.getForEntity(addUrl, Double.class);
        assertEquals(6.2, addResponse.getBody(), 0.0001);

        // Test multiplication with floating point numbers
        String multiplyUrl = getBaseUrl() + "/multiply?a=2.5&b=4.0";
        ResponseEntity<Double> multiplyResponse = restTemplate.getForEntity(multiplyUrl, Double.class);
        assertEquals(10.0, multiplyResponse.getBody(), 0.0001);
    }

    @Test
    void testNegativeNumberOperations() {
        // Test addition with negative numbers
        String addUrl = getBaseUrl() + "/add?a=-5&b=-3";
        ResponseEntity<Double> addResponse = restTemplate.getForEntity(addUrl, Double.class);
        assertEquals(-8.0, addResponse.getBody(), 0.0001);

        // Test division with negative numbers
        String divideUrl = getBaseUrl() + "/divide?a=-10&b=2";
        ResponseEntity<Double> divideResponse = restTemplate.getForEntity(divideUrl, Double.class);
        assertEquals(-5.0, divideResponse.getBody(), 0.0001);
    }

    @Test
    void testZeroOperations() {
        // Test multiplication by zero
        String multiplyUrl = getBaseUrl() + "/multiply?a=100&b=0";
        ResponseEntity<Double> multiplyResponse = restTemplate.getForEntity(multiplyUrl, Double.class);
        assertEquals(0.0, multiplyResponse.getBody(), 0.0001);

        // Test addition with zero
        String addUrl = getBaseUrl() + "/add?a=50&b=0";
        ResponseEntity<Double> addResponse = restTemplate.getForEntity(addUrl, Double.class);
        assertEquals(50.0, addResponse.getBody(), 0.0001);
    }

    @Test
    void testLargeNumberOperations() {
        String url = getBaseUrl() + "/multiply?a=1e10&b=2e5";
        ResponseEntity<Double> response = restTemplate.getForEntity(url, Double.class);
        assertEquals(2e15, response.getBody(), 1e13);
    }
}