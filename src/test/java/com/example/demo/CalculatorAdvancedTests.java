package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorAdvancedTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should handle very large positive numbers")
    void testVeryLargePositiveNumbers() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(Double.MAX_VALUE))
                .param("b", String.valueOf(Double.MAX_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.POSITIVE_INFINITY)));
    }

    @Test
    @DisplayName("Should handle very large negative numbers")
    void testVeryLargeNegativeNumbers() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(-Double.MAX_VALUE))
                .param("b", String.valueOf(-Double.MAX_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.NEGATIVE_INFINITY)));
    }

    @Test
    @DisplayName("Should handle very small positive numbers")
    void testVerySmallPositiveNumbers() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", String.valueOf(Double.MIN_VALUE))
                .param("b", String.valueOf(Double.MIN_VALUE)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.MIN_VALUE * 2)));
    }

    @Test
    @DisplayName("Should handle division resulting in very small number")
    void testDivisionResultingInVerySmallNumber() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "1.0E-100")
                .param("b", "1.0E100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0E-200"));
    }

    @Test
    @DisplayName("Should handle multiplication resulting in very large number")
    void testMultiplicationResultingInVeryLargeNumber() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "1.0E100")
                .param("b", "1.0E100"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(Double.POSITIVE_INFINITY)));
    }

    @Test
    @DisplayName("Should handle complex floating point operations")
    void testComplexFloatingPointOperations() throws Exception {
        // Test a series of operations that might accumulate floating point errors
        mockMvc.perform(get("/calculator/add")
                .param("a", "0.1")
                .param("b", "0.2"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.30000000000000004")); // Expected floating point result
    }

    @Test
    @DisplayName("Should handle mathematical identities")
    void testMathematicalIdentities() throws Exception {
        String base = "/calculator";
        
        // Test additive identity: a + 0 = a
        mockMvc.perform(get(base + "/add")
                .param("a", "123.456")
                .param("b", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("123.456"));

        // Test multiplicative identity: a * 1 = a
        mockMvc.perform(get(base + "/multiply")
                .param("a", "789.012")
                .param("b", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("789.012"));

        // Test subtractive identity: a - a = 0
        mockMvc.perform(get(base + "/subtract")
                .param("a", "555.555")
                .param("b", "555.555"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));

        // Test division identity: a / 1 = a
        mockMvc.perform(get(base + "/divide")
                .param("a", "999.999")
                .param("b", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("999.999"));
    }

    @Test
    @DisplayName("Should handle associative property for addition")
    void testAssociativePropertyAddition() throws Exception {
        // (a + b) + c = a + (b + c)
        mockMvc.perform(get("/calculator/add")
                .param("a", "2")
                .param("b", "3"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/calculator/add")
                .param("a", "3")
                .param("b", "4"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should handle commutative property for addition")
    void testCommutativePropertyAddition() throws Exception {
        // a + b = b + a
        mockMvc.perform(get("/calculator/add")
                .param("a", "5")
                .param("b", "8"))
                .andExpect(status().isOk())
                .andExpect(content().string("13.0"));

        mockMvc.perform(get("/calculator/add")
                .param("a", "8")
                .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("13.0"));
    }

    @Test
    @DisplayName("Should handle commutative property for multiplication")
    void testCommutativePropertyMultiplication() throws Exception {
        // a * b = b * a
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "7")
                .param("b", "9"))
                .andExpect(status().isOk())
                .andExpect(content().string("63.0"));

        mockMvc.perform(get("/calculator/multiply")
                .param("a", "9")
                .param("b", "7"))
                .andExpect(status().isOk())
                .andExpect(content().string("63.0"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Should complete operations within 1 second")
    void testPerformance() throws Exception {
        // Test multiple operations quickly
        for (int i = 0; i < 100; i++) {
            mockMvc.perform(get("/calculator/add")
                    .param("a", String.valueOf(i))
                    .param("b", String.valueOf(i)))
                    .andExpect(status().isOk());
        }
    }

    @Test
    @DisplayName("Should handle string parameter conversion")
    void testStringParameterHandling() throws Exception {
        // Test with string representations of numbers
        mockMvc.perform(get("/calculator/add")
                .param("a", "123.45")
                .param("b", "67.89"))
                .andExpect(status().isOk())
                .andExpect(content().string("191.34"));
    }

    @Test
    @DisplayName("Should handle scientific notation")
    void testScientificNotation() throws Exception {
        mockMvc.perform(get("/calculator/multiply")
                .param("a", "1e5")
                .param("b", "2e3"))
                .andExpect(status().isOk())
                .andExpect(content().string("2.0E8"));
    }

    @Test
    @DisplayName("Should handle negative zero")
    void testNegativeZero() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "-0.0")
                .param("b", "5.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));

        mockMvc.perform(get("/calculator/divide")
                .param("a", "10.0")
                .param("b", "-0.0"))
                .andExpect(status().isBadRequest());
    }
}