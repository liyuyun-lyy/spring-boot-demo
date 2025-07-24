package com.example.demo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorParameterizedTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "0, 0, 0",
        "-1, -1, -2",
        "100.5, 200.5, 301.0",
        "-50.25, 25.75, -24.5",
        "0.1, 0.2, 0.3",
        "999999, 1, 1000000",
        "-999999, -1, -1000000"
    })
    void addWithVariousInputsShouldReturnCorrectResult(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
        "5, 3, 2",
        "0, 0, 0",
        "-5, -3, -2",
        "100.5, 50.5, 50.0",
        "-50.25, -25.25, -25.0",
        "0.3, 0.1, 0.2",
        "1, 1, 0",
        "-1, -1, 0"
    })
    void subtractWithVariousInputsShouldReturnCorrectResult(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
        "2, 3, 6",
        "0, 5, 0",
        "-2, 3, -6",
        "-2, -3, 6",
        "100.5, 2, 201.0",
        "0.5, 0.5, 0.25",
        "1.5, 2.5, 3.75",
        "999, 0, 0"
    })
    void multiplyWithVariousInputsShouldReturnCorrectResult(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
        "6, 3, 2",
        "10, 4, 2.5",
        "-6, 3, -2",
        "-6, -3, 2",
        "1, 3, 0.3333333333333333",
        "0, 5, 0",
        "100.5, 2, 50.25",
        "1, 1, 1"
    })
    void divideWithVariousInputsShouldReturnCorrectResult(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -0.0, 0.001, -0.001})
    void divideByZeroShouldAlwaysReturnBadRequest(double b) throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "10")
                .param("b", String.valueOf(b)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot divide by zero"));
    }

    @ParameterizedTest
    @CsvSource({
        "0.1, 0.2, 0.3",           // Floating point precision test
        "0.1, 0.1, 0.2",
        "0.3, 0.1, 0.4",
        "1.1, 2.2, 3.3",
        "1.23, 4.56, 5.79",
        "10.99, 20.01, 31.0"
    })
    void addFloatingPointNumbersShouldHandlePrecision(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
        "1e10, 1e10, 2e10",
        "1e-10, 1e-10, 2e-10",
        "1e308, 1e308, 2e308",
        "1e-308, 1e-308, 2e-308"
    })
    void addWithScientificNotationShouldReturnCorrectResult(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }
}