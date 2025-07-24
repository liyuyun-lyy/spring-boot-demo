#!/bin/bash

# Test runner script for Spring Boot Demo project
echo "Running Spring Boot Demo Tests..."
echo "================================="

# Make Maven wrapper executable
chmod +x mvnw

# Run tests with detailed output
echo "Running Maven tests..."
./mvnw test -B

# Check if tests passed
if [ $? -eq 0 ]; then
    echo "================================="
    echo "All tests passed successfully! ✅"
    echo "================================="
    
    # Show test summary
    echo "Test classes created:"
    find src/test/java -name "*.java" -type f | sed 's/.*\///g'
    
    echo ""
    echo "To run tests manually, use:"
    echo "  ./mvnw test"
else
    echo "================================="
    echo "Some tests failed! ❌"
    echo "================================="
    exit 1
fi