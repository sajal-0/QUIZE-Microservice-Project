# QUIZE Microservice Project

## 📌 Overview
The **QUIZE Microservice Project** is a Spring Boot-based microservices application for creating and managing quizzes.  
It demonstrates **service discovery**, **API Gateway routing**, **service-to-service communication**, and **score calculation** for submitted answers.

---

## 🏗 Architecture
The project follows a **microservices architecture** with the following components:

1. **Eureka Server**  
   - Acts as the service registry where all other services register themselves.
   - Enables service discovery without hardcoding URLs.

2. **API Gateway**  
   - Single entry point for all client requests.
   - Routes requests to the appropriate microservice via service names from Eureka.

3. **Question Service**  
   - Handles CRUD operations for quiz questions.
   - Provides questions for a quiz and correct answers for score evaluation.

4. **Quiz Service**  
   - Manages quiz creation and linking questions.
   - Accepts submitted answers from users and calculates scores by calling Question Service.

---

## ⚙ Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud Gateway**
- **Spring Data JPA**
- **Feign Client**
- **H2 / MySQL Database**
- **Maven**

---

## 🔄 Project Flow
1. **Service Registration**: All services register with **Eureka Server**.
2. **Client Request**: Client sends requests to **API Gateway**.
3. **Routing**: API Gateway forwards the request to the target microservice.
4. **Question Fetching**: Quiz Service requests questions from Question Service.
5. **Score Calculation**: User submits answers → Quiz Service fetches correct answers → Calculates and returns the score.

---

## 📊 Score Calculation Logic
When a user submits a quiz:
1. Quiz Service receives submitted answers.
2. Calls Question Service (via Feign Client) to get correct answers.
3. Compares each answer and increments score if correct.
4. Returns the **final score** to the user.

---

## 📁 Project Structure
