package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorControllerUnitTest {

    private CalculatorController calculatorController;

    @BeforeEach
    void setUp() {
        calculatorController = new CalculatorController();
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTests {

        @Test
        @DisplayName("Should add two positive numbers correctly")
        void addPositiveNumbers() {
            double result = calculatorController.add(5.0, 3.0);
            assertEquals(8.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should add negative numbers correctly")
        void addNegativeNumbers() {
            double result = calculatorController.add(-5.0, -3.0);
            assertEquals(-8.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should add positive and negative numbers correctly")
        void addMixedNumbers() {
            double result = calculatorController.add(5.0, -3.0);
            assertEquals(2.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle zero addition")
        void addWithZero() {
            assertAll(
                () -> assertEquals(5.0, calculatorController.add(5.0, 0.0), 0.0001),
                () -> assertEquals(5.0, calculatorController.add(0.0, 5.0), 0.0001),
                () -> assertEquals(0.0, calculatorController.add(0.0, 0.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle floating point precision")
        void addFloatingPointNumbers() {
            double result = calculatorController.add(0.1, 0.2);
            assertEquals(0.3, result, 0.0001);
        }
    }

    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractionTests {

        @Test
        @DisplayName("Should subtract two positive numbers correctly")
        void subtractPositiveNumbers() {
            double result = calculatorController.subtract(5.0, 3.0);
            assertEquals(2.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should subtract negative numbers correctly")
        void subtractNegativeNumbers() {
            double result = calculatorController.subtract(-5.0, -3.0);
            assertEquals(-2.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle subtracting from zero")
        void subtractFromZero() {
            double result = calculatorController.subtract(0.0, 5.0);
            assertEquals(-5.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle subtracting zero")
        void subtractZero() {
            double result = calculatorController.subtract(5.0, 0.0);
            assertEquals(5.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle same numbers subtraction")
        void subtractSameNumbers() {
            double result = calculatorController.subtract(5.0, 5.0);
            assertEquals(0.0, result, 0.0001);
        }
    }

    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplicationTests {

        @Test
        @DisplayName("Should multiply two positive numbers correctly")
        void multiplyPositiveNumbers() {
            double result = calculatorController.multiply(5.0, 3.0);
            assertEquals(15.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle negative multiplication correctly")
        void multiplyNegativeNumbers() {
            assertAll(
                () -> assertEquals(15.0, calculatorController.multiply(-5.0, -3.0), 0.0001),
                () -> assertEquals(-15.0, calculatorController.multiply(-5.0, 3.0), 0.0001),
                () -> assertEquals(-15.0, calculatorController.multiply(5.0, -3.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle multiplication by zero")
        void multiplyByZero() {
            assertAll(
                () -> assertEquals(0.0, calculatorController.multiply(5.0, 0.0), 0.0001),
                () -> assertEquals(0.0, calculatorController.multiply(0.0, 5.0), 0.0001),
                () -> assertEquals(0.0, calculatorController.multiply(0.0, 0.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle multiplication by one")
        void multiplyByOne() {
            assertAll(
                () -> assertEquals(5.0, calculatorController.multiply(5.0, 1.0), 0.0001),
                () -> assertEquals(5.0, calculatorController.multiply(1.0, 5.0), 0.0001),
                () -> assertEquals(-5.0, calculatorController.multiply(-5.0, 1.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle floating point multiplication")
        void multiplyFloatingPoint() {
            double result = calculatorController.multiply(2.5, 3.5);
            assertEquals(8.75, result, 0.0001);
        }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {

        @Test
        @DisplayName("Should divide two positive numbers correctly")
        void dividePositiveNumbers() {
            double result = calculatorController.divide(6.0, 3.0);
            assertEquals(2.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle negative division correctly")
        void divideNegativeNumbers() {
            assertAll(
                () -> assertEquals(2.0, calculatorController.divide(-6.0, -3.0), 0.0001),
                () -> assertEquals(-2.0, calculatorController.divide(-6.0, 3.0), 0.0001),
                () -> assertEquals(-2.0, calculatorController.divide(6.0, -3.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle division by one")
        void divideByOne() {
            assertAll(
                () -> assertEquals(5.0, calculatorController.divide(5.0, 1.0), 0.0001),
                () -> assertEquals(-5.0, calculatorController.divide(-5.0, 1.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle division resulting in decimal")
        void divideWithDecimalResult() {
            double result = calculatorController.divide(5.0, 2.0);
            assertEquals(2.5, result, 0.0001);
        }

        @Test
        @DisplayName("Should throw exception when dividing by zero")
        void divideByZeroShouldThrowException() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorController.divide(5.0, 0.0)
            );
            assertEquals("Cannot divide by zero", exception.getMessage());
        }

        @Test
        @DisplayName("Should handle zero divided by non-zero")
        void divideZeroByNonZero() {
            double result = calculatorController.divide(0.0, 5.0);
            assertEquals(0.0, result, 0.0001);
        }

        @Test
        @DisplayName("Should handle floating point division")
        void divideFloatingPoint() {
            double result = calculatorController.divide(7.5, 2.5);
            assertEquals(3.0, result, 0.0001);
        }
    }

    @Nested
    @DisplayName("Edge Cases and Special Values")
    class EdgeCasesTests {

        @Test
        @DisplayName("Should handle very large numbers")
        void handleVeryLargeNumbers() {
            double large = 1e308;
            assertAll(
                () -> assertEquals(Double.POSITIVE_INFINITY, calculatorController.add(large, large), 0.0001),
                () -> assertEquals(0.0, calculatorController.subtract(large, large), 0.0001),
                () -> assertEquals(Double.POSITIVE_INFINITY, calculatorController.multiply(large, 2.0), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle very small numbers")
        void handleVerySmallNumbers() {
            double small = 1e-308;
            assertAll(
                () -> assertEquals(2e-308, calculatorController.add(small, small), 1e-310),
                () -> assertEquals(1e-308, calculatorController.multiply(small, 1.0), 1e-310),
                () -> assertEquals(1.0, calculatorController.divide(small, small), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle infinity values")
        void handleInfinity() {
            assertAll(
                () -> assertEquals(Double.POSITIVE_INFINITY, calculatorController.add(Double.POSITIVE_INFINITY, 1.0), 0.0001),
                () -> assertEquals(Double.NEGATIVE_INFINITY, calculatorController.add(Double.NEGATIVE_INFINITY, -1.0), 0.0001),
                () -> assertEquals(Double.NaN, calculatorController.multiply(0.0, Double.POSITIVE_INFINITY), 0.0001)
            );
        }

        @Test
        @DisplayName("Should handle NaN values")
        void handleNaN() {
            assertAll(
                () -> assertTrue(Double.isNaN(calculatorController.add(Double.NaN, 1.0))),
                () -> assertTrue(Double.isNaN(calculatorController.subtract(1.0, Double.NaN))),
                () -> assertTrue(Double.isNaN(calculatorController.multiply(Double.NaN, 1.0))),
                () -> assertTrue(Double.isNaN(calculatorController.divide(Double.NaN, 1.0)))
            );
        }
    }
}