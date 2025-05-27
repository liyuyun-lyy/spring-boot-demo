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
    void divideShouldHandleDivisionByZero() throws Exception {
        mockMvc.perform(get("/calculator/divide")
                .param("a", "6")
                .param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Cannot divide by zero"));
    }

    @Test
    void addShouldReturnBadRequestWhenParameterMissing() throws Exception {
        // missing parameter b
        mockMvc.perform(get("/calculator/add")
                .param("a", "5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturnBadRequestWhenParameterIsNotANumber() throws Exception {
        mockMvc.perform(get("/calculator/add")
                .param("a", "foo")
                .param("b", "3"))
                .andExpect(status().isBadRequest());
    }
}