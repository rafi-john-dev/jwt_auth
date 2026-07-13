\# JWT Authentication System



A secure RESTful authentication API built using Spring Boot, Spring Security, JWT, PostgreSQL, and Swagger/OpenAPI. The application provides user registration, login, JWT-based authentication, and protected API endpoints.



\## Features



\- User Registration

\- User Login

\- JWT Token Generation

\- JWT Token Validation

\- Password Encryption using BCrypt

\- Stateless Authentication

\- Protected REST APIs

\- PostgreSQL Database Integration

\- Swagger/OpenAPI Documentation

\- Cloud Deployment on Render



\---



\## Tech Stack



\- Java 17

\- Spring Boot

\- Spring Security

\- Spring Data JPA

\- Hibernate

\- PostgreSQL

\- JWT (JSON Web Token)

\- Swagger / OpenAPI

\- Maven

\- Render



\---



\## Project Structure



```

src

├── config

├── controller

├── dto

├── entity

├── repository

├── security

├── service

└── resources

```



\---



\## Live Demo



\*\*Application\*\*



https://jwt-auth-backend-35xp.onrender.com/



\*\*Swagger Documentation\*\*



https://jwt-auth-backend-35xp.onrender.com/swagger-ui/index.html



\---



\## API Endpoints



\### Register User



\*\*POST\*\*



```

/api/auth/register

```



Sample Request



```json

{

&#x20; "name": "john",

&#x20; "email": "john@example.com",

&#x20; "password": "Password@123"

}

```



\---



\### Login User



\*\*POST\*\*



```

/api/auth/login

```



Sample Request



```json

{

&#x20; "email": "john",

&#x20; "password": "Password@123"

}

```



Sample Response



```json

{

&#x20; "eyJhbGciOiJIUzI1NiJ9..."

}

```



\---



\### Access Protected APIs



After login:



1\. Copy the JWT token.

2\. Click \*\*Authorize\*\* in Swagger.

3\. Enter:



```

Bearer <your\_token>

```



4\. Execute protected endpoints.



\---



\## Running the Project Locally



\### Clone Repository



```bash

git clone https://github.com/rafi-john-dev/jwt\_auth.git

```



\### Navigate



```bash

cd YOUR\_REPOSITORY

```



\### Configure Database



Update `application.properties`



```properties

spring.datasource.url=jdbc:postgresql://localhost:5432/your\_database

spring.datasource.username=postgres

spring.datasource.password=your\_password

```



\### Run



```bash

mvn spring-boot:run

```



Application starts at



```

http://localhost:8080

```



Swagger



```

http://localhost:8080/swagger-ui/index.html

```



\---



\## Deployment



The application is deployed on Render.



Environment variables used:



```

SPRING\_DATASOURCE\_URL

SPRING\_DATASOURCE\_USERNAME

SPRING\_DATASOURCE\_PASSWORD

```



\---



\## Future Improvements



\- Role-Based Authorization

\- Refresh Tokens

\- Email Verification

\- Password Reset

\- Docker Support

\- Unit \& Integration Testing

\- CI/CD Pipeline



\---



\## Author



\*\*Shaik Rafi John Saida\*\*



GitHub:

https://github.com/rafi-john-dev

