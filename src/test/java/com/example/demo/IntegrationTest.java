package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    void calculatorAddEndpointShouldWork() {
        String url = "http://localhost:" + port + "/calculator/add?a=5&b=3";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("8.0");
    }

    @Test
    void calculatorSubtractEndpointShouldWork() {
        String url = "http://localhost:" + port + "/calculator/subtract?a=5&b=3";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("2.0");
    }

    @Test
    void calculatorMultiplyEndpointShouldWork() {
        String url = "http://localhost:" + port + "/calculator/multiply?a=5&b=3";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("15.0");
    }

    @Test
    void calculatorDivideEndpointShouldWork() {
        String url = "http://localhost:" + port + "/calculator/divide?a=6&b=3";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("2.0");
    }

    @Test
    void calculatorDivideByZeroShouldReturnBadRequest() {
        String url = "http://localhost:" + port + "/calculator/divide?a=5&b=0";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isEqualTo("Cannot divide by zero");
    }

    @Test
    void checkPreloadEndpointShouldReturnSuccess() {
        String url = "http://localhost:" + port + "/checkpreload.htm";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("success");
    }

    @Test
    void toolsEndpointShouldReturnList() {
        String url = "http://localhost:" + port + "/tools";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("bash");
        assertThat(response.getBody()).contains("str_replace_editor");
    }
}