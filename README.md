# 🛒 E-commerce Lite API (Spring Boot + MongoDB)

A lightweight, secure, and extendable RESTful backend for an e-commerce application built with **Spring Boot**, **MongoDB**, and **Spring Security (JWT)**.

---

## 🚀 Features

- 🔐 User registration & login with JWT authentication
- 🛍️ Product listing, creation, update, deletion
- 🧺 In-memory cart simulation (for demo)
- 📦 Order placement and history
- 🧾 Role-based access control (User/Admin)
- 📡 RESTful API design

---

## 🛠️ Tech Stack

| Layer        | Tech                           |
|--------------|--------------------------------|
| Language     | Java 17                        |
| Framework    | Spring Boot                    |
| Database     | MongoDB                        |
| Security     | Spring Security + JWT          |
| Build Tool   | Maven                          |
| Testing Tool | Postman (API testing)          |

---

## 📁 Project Structure

```
ecommerce-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ishaan/
│   │   │           └── ecommerce_api/
│   │   │               ├── controller/       # API endpoints (Auth, Product, Order)
│   │   │               ├── dto/              # DTOs for requests and responses
│   │   │               ├── entity/           # MongoDB document models (User, Product, OrderItem, etc.)
│   │   │               ├── repository/       # MongoRepository interfaces
│   │   │               ├── security/         # JWT filters, utils, config
│   │   │               └── service/          # Core business logic and interfaces
│   │   └── resources/
│   │       └── application.properties        # MongoDB URI and app-level configs
├── .gitignore
├── pom.xml                                   # Maven configuration
└── README.md
```


---


---

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/ecommerce-api.git
cd ecommerce-api
```

### 2. Install Maven (if not installed)

👉 [Download Maven](https://maven.apache.org/download.cgi)

After downloading, add it to your system's `PATH`, then verify the installation:

```bash
mvn -v
```

### 3. Set up MongoDB
- Local MongoDB: Ensure it's running on `localhost:27017`
- Or use MongoDB Atlas: Replace URI in `application.properties`

### 4. Configure Application

Inside `src/main/resources/application.properties`:

```properties
spring.application.name=ecommerce-api
spring.data.mongodb.uri=mongodb://localhost:27017/ecommerce-api
```

### 5. Run the App

```bash
mvn clean install -U
mvn spring-boot:run
```

---

## 🔐 Authentication Flow

**1. REGISTER**: `POST /auth/register`

**2. LOGIN**: `POST /auth/login`  
- Returns JWT Token

**3. Use JWT token**: Add to request header:
```makefile
Authorization: Bearer <token>
```
