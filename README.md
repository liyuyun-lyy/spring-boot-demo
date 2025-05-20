# Spring Boot Demo

A simple Spring Boot demonstration application providing a basic REST API endpoint.

## Prerequisites

* Java 11
* Maven 3.6+

## Build Instructions

To build the application, run:

```bash
./mvnw clean package
```

## Run Instructions

To run the application locally:

```bash
./mvnw spring-boot:run
```

The application will start on port 8080 by default.

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/checkpreload.htm` | GET | Returns "success" if the application is running properly |

## Technologies Used

* Spring Boot 2.7.12
* Spring Web
* Maven
* Tomcat (embedded)
* JUnit for testing