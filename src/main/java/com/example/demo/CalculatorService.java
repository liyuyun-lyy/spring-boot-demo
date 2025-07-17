package com.example.demo;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Tool(name = "add", description = "Add two numbers")
    public double add(double a, double b) {
        return a + b;
    }

    @Tool(name = "subtract", description = "Subtract two numbers")
    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    public double abs(double a) {
        return Math.abs(a);
    }

    public double max(double a, double b) {
        return Math.max(a, b);
    }

    public double min(double a, double b) {
        return Math.min(a, b);
    }

    public double ceil(double a) {
        return Math.ceil(a);
    }
}
