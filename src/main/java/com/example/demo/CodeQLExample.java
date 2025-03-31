package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class CodeQLExample {

    CodeQLExample() {
        System.out.println("CodeQLExample constructor");
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // Potential null pointer dereference
        String name = getName(names, 5);
        System.out.println(name.length());  // This will throw NullPointerException

        // Incorrect use of equals
        if (name.equals("Alice")) {  // This will throw NullPointerException if name is null
            System.out.println("Found Alice!");
        }

        // Potential SQL Injection vulnerability
        String userInput = "Robert'); DROP TABLE Students;--";
        String query = "SELECT * FROM Users WHERE name = '" + userInput + "'";
        System.out.println(query);
    }

    private static String getName(List<String> names, int index) {
        // Returns the name at the given index, but does not check for out-of-bounds
        return names.get(index);
    }

    public String getName() {
        return "CodeQLExample";
    }
}
