# Spring JPA Web Demo

A traditional Spring Framework project (not Spring Boot) demonstrating:
- Spring Web MVC with Java-based configuration
- Spring JPA with Hibernate
- H2 In-Memory Database
- RESTful API endpoints
- Tomcat servlet container

## Project Structure

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/          # Java-based Configuration Classes
│   │   │   ├── WebAppInitializer.java   # Replaces web.xml
│   │   │   ├── RootConfig.java          # Root application context
│   │   │   ├── WebConfig.java           # Web MVC configuration
│   │   │   └── DataInitializer.java     # Sample data setup
│   │   ├── controller/      # REST Controllers
│   │   ├── entity/          # JPA Entities
│   │   ├── repository/      # Data Access Layer (DAO)
│   │   └── service/         # Business Logic Layer
│   ├── resources/           # Configuration resources
│   └── webapp/              # Static web resources
└── test/                    # Test classes
```

## Configuration Approach

This project uses **Java-based configuration** instead of XML:
- **`WebAppInitializer`**: Replaces `web.xml`, implements `WebApplicationInitializer`
- **`RootConfig`**: Root application context with `@Configuration`, `@EnableTransactionManagement`
- **`WebConfig`**: Web MVC configuration with `@EnableWebMvc`, `WebMvcConfigurer`

### Key Benefits of Java Configuration:
- **Type Safety**: Compile-time checking of configuration
- **IDE Support**: Better autocomplete and refactoring
- **Debugging**: Easier to debug configuration issues
- **Modern Approach**: Industry standard for Spring applications
- **Testability**: Easier to unit test configuration classes

## Requirements

- Java 17 or higher
- Gradle 7.0 or higher

## Running the Application

### Using Gradle with Gretty Plugin (Recommended)

1. **Start the application:**
   ```bash
   ./gradlew appRun
   ```

2. **Access the application:**
   - Base URL: `http://localhost:8080/spring-demo`
   - Health Check: `http://localhost:8080/spring-demo/api/users/health`

### Building WAR file for Tomcat deployment

1. **Build the WAR file:**
   ```bash
   ./gradlew build
   ```

2. **Deploy to Tomcat:**
   - Copy `build/libs/spring-jpa-web-demo-1.0.0.war` to Tomcat's `webapps` directory
   - Start Tomcat
   - Access at `http://localhost:8080/spring-jpa-web-demo`

## API Endpoints

Base URL: `http://localhost:8080/spring-demo/api/users`

### Health Check
- **GET** `/health` - Application health status

### User Management
- **GET** `/` - Get all users
- **GET** `/active` - Get active users only
- **GET** `/{id}` - Get user by ID
- **GET** `/username/{username}` - Get user by username
- **POST** `/` - Create new user
- **PUT** `/{id}` - Update user
- **DELETE** `/{id}` - Delete user
- **PATCH** `/{id}/activate` - Activate user
- **PATCH** `/{id}/deactivate` - Deactivate user

### Utility Endpoints
- **GET** `/count` - Get total user count
- **GET** `/exists/username/{username}` - Check if username exists
- **GET** `/exists/email/{email}` - Check if email exists

## Testing the API

### 1. Health Check
```bash
curl http://localhost:8080/spring-demo/api/users/health
```

### 2. Get All Users
```bash
curl http://localhost:8080/spring-demo/api/users
```

### 3. Create a New User
```bash
curl -X POST http://localhost:8080/spring-demo/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User"
  }'
```

### 4. Get User by ID
```bash
curl http://localhost:8080/spring-demo/api/users/1
```

### 5. Update User
```bash
curl -X PUT http://localhost:8080/spring-demo/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "updateduser",
    "email": "updated@example.com",
    "firstName": "Updated",
    "lastName": "User"
  }'
```

### 6. Delete User
```bash
curl -X DELETE http://localhost:8080/spring-demo/api/users/1
```

## Sample Data

The application automatically initializes with 5 sample users:
1. john_doe (john.doe@example.com)
2. jane_smith (jane.smith@example.com)  
3. bob_wilson (bob.wilson@example.com)
4. alice_brown (alice.brown@example.com)
5. charlie_davis (charlie.davis@example.com) - *deactivated*

## Configuration

### Database (H2 In-Memory)
- **Driver:** org.h2.Driver
- **URL:** jdbc:h2:mem:testdb
- **Username:** sa
- **Password:** (empty)
- **Console:** Not enabled (H2 console not configured)

### JPA/Hibernate Settings
- **Dialect:** H2Dialect
- **DDL:** create-drop (recreates schema on each startup)
- **Show SQL:** true (SQL queries logged to console)

## Technology Stack

- **Framework:** Spring Framework 6.1.0
- **Configuration:** Java-based (@Configuration, @EnableWebMvc, WebApplicationInitializer)
- **Web:** Spring Web MVC
- **ORM:** Spring ORM with Hibernate 6.4.0
- **Database:** H2 2.2.224 (In-Memory)
- **Connection Pool:** HikariCP 5.1.0
- **JSON:** Jackson 2.16.0 with JSR310 support
- **Validation:** Hibernate Validator 8.0.1
- **Logging:** SLF4J + Logback
- **Build Tool:** Gradle 
- **Servlet Container:** Tomcat (via Gretty plugin)

## Notes

- This is a traditional Spring Framework project (not Spring Boot)
- **Uses Java-based configuration** instead of XML
- Implements declarative transaction management with `@Transactional`
- Uses EntityManager for JPA operations
- In-memory database resets on application restart
- Modern Spring configuration approach with type safety and IDE support
