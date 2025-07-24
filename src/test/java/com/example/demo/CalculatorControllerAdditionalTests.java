package com.example.demo;

import org.junit.jupiter.api.Test;
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
class CalculatorControllerAdditionalTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "10.5, 2.5, 13.0",
            "-5.0, 3.0, -2.0",
            "0.0, 0.0, 0.0",
            "999999.999, 0.001, 1000000.0"
    })
    void addShouldHandleVariousNumbers(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
            "10.5, 2.5, 8.0",
            "-5.0, 3.0, -8.0",
            "0.0, 5.0, -5.0",
            "999999.999, 0.001, 999999.998"
    })
    void subtractShouldHandleVariousNumbers(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
            "10.5, 2.0, 21.0",
            "-5.0, 3.0, -15.0",
            "0.0, 5.0, 0.0",
            "999.999, 2.0, 1999.998"
    })
    void multiplyShouldHandleVariousNumbers(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @CsvSource({
            "10.0, 2.0, 5.0",
            "-15.0, 3.0, -5.0",
            "0.0, 5.0, 0.0",
            "7.5, 2.5, 3.0"
    })
    void divideShouldHandleVariousNumbers(double a, double b, double expected) throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", String.valueOf(a))
                .param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "", "12.5.3", "NaN", "Infinity"})
    void addShouldHandleInvalidParameters(String invalidValue) throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", invalidValue)
                .param("b", "5"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "", "12.5.3", "NaN", "Infinity"})
    void subtractShouldHandleInvalidParameters(String invalidValue) throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", invalidValue)
                .param("b", "5"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "", "12.5.3", "NaN", "Infinity"})
    void multiplyShouldHandleInvalidParameters(String invalidValue) throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", invalidValue)
                .param("b", "5"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abc", "", "12.5.3", "NaN", "Infinity"})
    void divideShouldHandleInvalidParameters(String invalidValue) throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", invalidValue)
                .param("b", "5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void divideWithMissingParametersShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "5"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/calculator/divide")
                .param("b", "5"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/calculator/divide"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void divideByVerySmallNumberShouldWork() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "1")
                .param("b", "0.000001"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000000.0"));
    }
}