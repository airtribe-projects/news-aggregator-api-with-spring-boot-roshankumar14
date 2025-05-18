Overview  

This project is a basic Spring Boot REST API for managing users, their news preferences, and fetching news articles using external APIs. It includes secure JWT-based authentication, validation, and exception handling. 

Features 

User registration and login using Spring Security + JWT 

In-memory database for user info and preferences 

External news API integration (via feign client) 

CRUD operations for user preferences- 

Clean error handling and validation 

Tested with Postman  

Tech Stack 

Java 17+ 

Spring Boot (Web, Security, Validation, lombok) 

JWT  

H2 In-Memory Database 

Feign client (Spring cloud) 

 

Dependencies 

org.springframework.boot:spring-boot-starter-data-jpa 

org.springframework.boot:spring-boot-starter-security 

org.springframework.boot:spring-boot-starter-web 

org.springframework.cloud:spring-cloud-starter-openfeign 

jakarta.validation:jakarta.validation-api:3.0.2 

com.h2database:h2 

org.projectlombok:lombok:1.18.34 

org.projectlombok:lombok:1.18.34 

 

API Endpoints 

Authentication 

Post - /register: Register a new user 

Get - /login: Log in and receive a JWT token 

Post - /verfiyToken: Verify the token 

 

 

User Preferences: 

GET /preferences - Get logged-in user's preferences 

PUT /preferences - Update news preferences 

 

News 

 GET /news - Fetch news based on user preferences External News APIs (examples)-  GNews (https://gnews.io/) 

 

Exception Handling 

Invalid credentials- Unauthorized access 

Validation errors- Not found resources Input  

 

Validation 

Uses @Valid, @NotBlank, @Email in DTOs 

 

 Testing 

Postman for API testing 

 

Postman 

URL - http://localhost:8080/v1/news/register 

Payload - {"userName": "Kumar@14", 

  "emailId": "roshanKumar14@gmail.com", 

  "password": "password123", 

  "newsPreferences": ["ai", "sports"], 

  "language":"ENG"} 

 

URL - http://localhost:8080/v1/news/verifyToken 

Sample Payload - http://localhost:8080/v1/news/verifyToken?genToken=e81f8564-efea-4010-b814-0018cff77bd4 

 

URL - http://localhost:8080/v1/news/login 

Sample Payload - http://localhost:8080/v1/news/login?userName=Kumar@14&password=password123 

URL - http://localhost:8080/v1/news/preference?userName=Kumar@13 

URL - http://localhost:8080/v1/news/preference?userName=Kumar@14&preference=politics		 

		 

		 

		 

	 

          
