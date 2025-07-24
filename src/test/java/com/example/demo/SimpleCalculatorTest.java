package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleCalculatorTest {

    @Test
    void testBasicAddition() {
        CalculatorController calculator = new CalculatorController();
        double result = calculator.add(2, 3);
        assertEquals(5.0, result, "2 + 3 should equal 5.0");
    }

    @Test
    void testBasicMultiplication() {
        CalculatorController calculator = new CalculatorController();
        double result = calculator.multiply(4, 5);
        assertEquals(20.0, result, "4 * 5 should equal 20.0");
    }
}