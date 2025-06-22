# DevConnect Backend API

A Spring Boot-based RESTful API for a **developer networking platform**, where developers can register, post content, and message each other securely.

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security (JWT Authentication)**
- **Spring Data JPA + Hibernate**
- **MySQL (prod) / H2 (test)**
- **JUnit 5**

---

## 📌 Features

- 🔐 **User Authentication** (Register & Login using JWT)
- 📝 **Post Management** (Create, Read, Update, Delete)
- 💬 **Messaging System** (Send & view messages)
- 🔒 **Role-based Access Control**

---

## 🗂️ Project Structure

```
com.devconnect
├── controller
│   ├── AuthController.java
│   ├── PostController.java
│   └── MessageController.java
├── model
│   ├── User.java
│   ├── Post.java
│   └── Message.java
├── repository
│   ├── UserRepository.java
│   ├── PostRepository.java
│   └── MessageRepository.java
├── service
│   └── JwtService.java
├── config
│   └── SecurityConfig.java
└── DevConnectBackendApplication.java
```

---

## 📦 Setup Instructions

### 1. Clone the repo

```bash
git clone https://github.com/your-username/DevConnect-Backend.git
cd DevConnect-Backend
```

### 2. Update MySQL credentials in `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devconnect
    username: root
    password: yourpassword
```

### 3. Run the app

```bash
./mvnw spring-boot:run
```

The app will start at: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Running Tests

The test suite uses an in-memory H2 database.

```bash
./mvnw test
```

---

## 🔐 API Endpoints

### Authentication
| Method | Endpoint              | Description         |
|--------|-----------------------|---------------------|
| POST   | `/api/auth/register`  | Register new user   |
| POST   | `/api/auth/login`     | Login and get token |

### Posts
| Method | Endpoint               | Description                  |
|--------|------------------------|------------------------------|
| GET    | `/api/posts`           | Get all posts                |
| GET    | `/api/posts/user/{id}` | Get posts by a specific user |
| POST   | `/api/posts`           | Create post (auth required)  |
| PUT    | `/api/posts/{id}`      | Update post (auth required)  |
| DELETE | `/api/posts/{id}`      | Delete post (auth required)  |

### Messages
| Method | Endpoint           | Description                     |
|--------|--------------------|---------------------------------|
| POST   | `/api/messages`    | Send message (auth required)    |
| GET    | `/api/messages/my` | View my messages (auth required)|

🔑 Use the token from `/login` in the `Authorization` header:

```
Authorization: Bearer <your-token>
```

---

## 🧑‍💻 Author

**Neha Kumari**  
GitHub: [@nehasingh-1](https://github.com/nehasingh-1)  
Email: ns2068062@gmail.com

---

## 📄 License

This project is licensed under the MIT License.
