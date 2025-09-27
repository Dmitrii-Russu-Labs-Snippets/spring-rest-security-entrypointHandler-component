# Spring REST Security EntryPoint Handler (Component)

Custom `AuthenticationEntryPoint` and `AccessDeniedHandler` implemented as separate `@Component` classes for Spring Security. Returns clean JSON error responses for **401 Unauthorized** and **403 Forbidden**.

---

## Overview

This repository demonstrates a production-friendly pattern: security error handlers implemented as dedicated Spring components.  
Each handler is a separate class annotated with `@Component` (or registered as a bean by component scan) and injected into `SecurityConfig`. This improves **separation of concerns**, **testability**, and **reusability**.

> Implementation difference vs other repos: handlers live in their own classes (`@Component`) rather than inlined as lambdas or provided by factory `@Bean` methods.

---

## Features

- **401 Unauthorized** → handled by a dedicated `AuthenticationEntryPoint` component  
- **403 Forbidden** → handled by a dedicated `AccessDeniedHandler` component  
- JSON responses include `status` and `timestamp` (easy to extend with `path`, `message`, `traceId`)  
- Components are easy to unit test, mock and reuse across multiple projects  
- Can be easily extended with extra fields (e.g., `path`, `traceId`)

---

## Example Error Response

```json
{
  "status": "403 FORBIDDEN",
  "timestamp": "2025-09-27T09:42:03.567Z",
  "path": "/auth/admin"
}
```

## How to Run
```
./mvnw spring-boot:run
```

`Test endpoints:`

Call a protected endpoint without authentication:
```
curl http://localhost:8080/auth/user
```
You’ll get a next JSON error response:
```json
{
  "status": "401 UNAUTHORIZED",
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```
Call a protected endpoint without required role:
```
curl -u ann@gmail.com:1234 http://localhost:8080/auth/admin
```
You’ll get a next JSON error response:
```json
{
  "status": "403 FORBIDDEN",
  "timestamp": "2025-09-27T09:42:03.567Z"
}
```

This project is a minimal reference implementation.

You can copy and adapt the AuthenticationEntryPoint and AccessDeniedHandler into any Spring Boot REST API project.
