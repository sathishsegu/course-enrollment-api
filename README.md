# 🎓 Course Enrollment Management REST API

A **production-style backend REST API** built using **Spring Boot and MySQL** to manage **Courses, Categories, Students, and Enrollments**.

This project was developed as part of a structured backend learning journey, focusing on **real-world backend architecture, JPA relationship modeling, and clean API design** rather than just simple CRUD operations.

The goal of this project is to demonstrate how scalable, relational, and maintainable backend APIs are designed in real applications.

---

## 🚀 Key Features

- RESTful APIs for managing **Categories, Courses, Students, and Enrollments**
- Clean layered architecture (Controller → Service → Repository)
- DTO-based request and response handling (no entity exposure)
- Global exception handling with proper HTTP status codes
- Input validation using Bean Validation (`@NotBlank`, `@Email`, etc.)
- Advanced JPA relationships:
  - `OneToMany` (Category → Course)
  - `ManyToOne` (Course → Category)
  - `OneToOne` (Course → CourseDetail)
  - Join Entity (`Enrollment`) for Many-to-Many modeling
- `@ElementCollection` usage for storing course syllabus topics
- Enum mapping using `EnumType.STRING`
- Pagination and sorting for scalable data retrieval
- ModelMapper integration to reduce boilerplate mapping code
- Unique constraints and data integrity enforcement
- Clean Git workflow with feature branches and meaningful commits
- Stability-first engineering decisions
- Dockerized application with MySQL
- One command startup using Docker Compose
- Production-style setup

---

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate / JPA**
- **MySQL**
- **Maven**
- **ModelMapper**
- **Bean Validation (Jakarta Validation)**
- **Swagger / OpenAPI**
- **Docker**
- **Docker Compose**

---

## 🧱 Project Architecture

```text
controller  →  service  →  repository
     ↓            ↓           ↓
    DTOs       Business     Database
               Logic
```

The project follows a clean layered architecture ensuring:

- Separation of concerns  
- Testability  
- Maintainability  
- Scalability  

---

##  🐳 Docker Setup
Run the entire application (API + MySQL) using Docker.
###

  - Docker Installed
  - Docker Compose Installed

### ▶️ Run with Docker
``` bash
git clone https://github.com/sathishsegu/course-enrollment-api.git
cd course-enrollment-api

docker compose up --build
```
### This will:

- Build the Spring Boot image
- Start MySQL container
- Connect API to database automatically

### 🛑 Stop Containers
```bash
docker compose down
```
---
## ▶️ How to Run the Project Without Docker (Local Setup)

1. Clone the repository:
   ```bash
   git clone https://github.com/sathishsegu/course-enrollment-api.git
   ```
2. Configure MySQL credentials in `application.properties`
3. Create the database:
   ```sql
   CREATE DATABASE course_db;
   ```
4. Run the Spring Boot application
5. Test APIs using Postman or any REST client

---

## 🗂 Domain Model Overview

```text
Category (1) -------- (N) Course
Course (1) ---------- (1) CourseDetail
CourseDetail (1) ---- (N) Syllabus Topics
Student (1) --------- (N) Enrollment (N) --------- (1) Course
```

### Highlights

- Enrollment is modeled as a **join entity** instead of using `@ManyToMany`
- Course syllabus is stored using `@ElementCollection`
- Course level is modeled using an **Enum**
- Cascade and orphan removal used where appropriate

---

## 📘 API Overview

### ➕ Create Student

**POST** `/api/students`

#### Request:
```json
{
  "studentName": "Sathish",
  "email": "sathish@example.com"
}
```

#### Response:
`201 CREATED`

---

### ➕ Enroll Student to Course

**POST** `/api/enrollments`

#### Request:
```json
{
  "studentId": 1,
  "courseId": 2
}
```

#### Response:
`201 CREATED`

---

### 📄 Get Students Enrolled in a Course (Paginated)

**GET** `/api/courses/{courseId}/students`

#### Query Parameters:
- `page` (default: 0)
- `size` (default: 5)
- `sortBy` (default: studentName)
- `sortDir` (asc / desc)

#### Example:
```
GET /api/courses/1/students?page=0&size=5&sortBy=studentName&sortDir=asc
```

#### Response:
```json
{
  "content": [],
  "pageNumber": 0,
  "pageSize": 5,
  "totalElements": 0,
  "totalPage": 0,
  "isLastPage": true
}
```

---

### 📄 Get Courses by Category

**GET** `/api/categories/{categoryId}/courses`

Supports pagination and sorting.

---

### Additional APIs Implemented

- Category CRUD APIs  
- Course CRUD APIs  
- Student CRUD APIs  
- CourseDetail (OneToOne) APIs  
- Fetch courses by student  
- Fetch students by course  
- Duplicate enrollment prevention  
- Duplicate email prevention  

---

## ⚠️ Exception Handling

- Centralized exception handling using `@ControllerAdvice`
- Proper HTTP status codes:
  - `400 BAD REQUEST` (Validation errors)
  - `404 NOT FOUND`
  - `409 CONFLICT` (Duplicate email / duplicate enrollment)
  - `201 CREATED`
  - `204 NO CONTENT`

This keeps controllers clean and APIs consistent.

---

## 🔒 Data Integrity & Design Decisions

- Unique constraint on student email
- Unique constraint on `(student_id, course_id)` in Enrollment
- Enum stored as `STRING` for stability
- LAZY fetching used to optimize performance
- Cascade and orphan removal used for dependent entities

---

## 🧪 Testing

- APIs tested using Postman
- Both success and failure scenarios verified
- Pagination and sorting thoroughly tested
- Validation and exception handling verified
- Duplicate constraint scenarios tested

---

## 📈 Learning Outcomes

Through this project, I gained hands-on experience in:

- Designing production-ready REST APIs  
- Modeling complex JPA relationships  
- Implementing OneToOne, OneToMany, and Join Entities  
- Preventing data integrity issues  
- Using Enum and ElementCollection effectively  
- Handling pagination and sorting  
- Writing clean layered architecture  
- Using Git with professional workflow  
- Debugging real-world backend issues  

---

## 👨‍💻 Author

**Sathish Kumar Segu**

Aspiring Java Backend Developer  

📌 Open to Backend Developer Opportunities  
