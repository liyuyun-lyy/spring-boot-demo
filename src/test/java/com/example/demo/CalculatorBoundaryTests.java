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
class CalculatorBoundaryTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addWithMaxDoubleValuesShouldReturnInfinity() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(Double.MAX_VALUE))
                .param("b", String.valueOf(Double.MAX_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.POSITIVE_INFINITY)));
    }

    @Test
    void multiplyWithMaxDoubleValuesShouldReturnInfinity() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", String.valueOf(Double.MAX_VALUE))
                .param("b", String.valueOf(Double.MAX_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.POSITIVE_INFINITY)));
    }

    @Test
    void subtractSameNumbersShouldReturnZero() throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", "42.5")
                .param("b", "42.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    void divideVerySmallNumbersShouldReturnCorrectResult() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "1.0E-10")
                .param("b", "1.0E-5"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0E-5"));
    }

    @Test
    void multiplyByOneShouldReturnSameNumber() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "123.456")
                .param("b", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("123.456"));
    }

    @Test
    void divideByOneShouldReturnSameNumber() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "987.654")
                .param("b", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("987.654"));
    }

    @Test
    void addZeroToNumberShouldReturnSameNumber() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "999.999")
                .param("b", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("999.999"));
    }

    @Test
    void subtractZeroFromNumberShouldReturnSameNumber() throws Exception {
        mockMvc.perform(get("/calculator/subtract")
                .param("a", "555.555")
                .param("b", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("555.555"));
    }

    @Test
    void divideNegativeByNegativeShouldReturnPositive() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "-10")
                .param("b", "-2"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void multiplyNegativeByNegativeShouldReturnPositive() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "-7")
                .param("b", "-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("56.0"));
    }
}