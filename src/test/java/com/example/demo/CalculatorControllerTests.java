package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("8.0"));
    }

    @Test
    void subtractShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void multiplyShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("15.0"));
    }

    @Test
    void divideShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "6")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0"));
    }

    @Test
    void divideByZeroShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "5")
                .param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot divide by zero"));
    }

    @Test
    void addWithNegativeNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "-5")
                .param("b", "-3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-8.0"));
    }

    @Test
    void subtractWithNegativeNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", "-5")
                .param("b", "-3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2.0"));
    }

    @Test
    void multiplyWithNegativeNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "-5")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-15.0"));
    }

    @Test
    void divideWithNegativeNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "-6")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2.0"));
    }

    @Test
    void addWithZeroShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "0")
                .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void multiplyWithZeroShouldReturnZero() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "100")
                .param("b", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void addWithFloatingPointNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "3.14159")
                .param("b", "2.71828"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.85987"));
    }

    @Test
    void divideWithFloatingPointResultShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "10")
                .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.3333333333333335"));
    }

    @Test
    void subtractResultingInZeroShouldReturnZero() throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", "5")
                .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void multiplyWithVerySmallNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "0.001")
                .param("b", "0.002"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0E-6"));
    }
}
