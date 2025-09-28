# Spring REST Security EntryPoint Handler (Component)

Custom `AuthenticationEntryPoint` and `AccessDeniedHandler` implemented as separate `@Component` classes for Spring Security. Returns clean JSON error responses for **401 Unauthorized** and **403 Forbidden**.

---

## Overview

This repository demonstrates a production-friendly pattern: security error handlers implemented as dedicated Spring components.  
Each handler is a separate class annotated with `@Component` (or registered as a bean by component scan) and injected into `SecurityConfig`. This improves **separation of concerns**, **testability**, and **reusability**.

> Implementation difference vs other repos: handlers live in their own classes (`@Component`) rather than inlined as [lambdas](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-lambda) or provided by [factory @Bean methods](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-bean).

---

## Features

- **401 Unauthorized** → handled by a dedicated `AuthenticationEntryPoint` component  
- **403 Forbidden** → handled by a dedicated `AccessDeniedHandler` component  
- JSON responses include `status` and `timestamp` (easy to extend with `path`, `message`, `traceId`)  
- Components are easy to unit test, mock and reuse across multiple projects 

---

## Example JSON responses

**401 Unauthorized** (Content-Type: `application/json`)
```json
{
  "status": 401,
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```
**403 Forbidden** (Content-Type: `application/json`)
```json
{
  "status": 403,
  "timestamp": "2025-09-27T09:41:21.124Z"
}
```

Note: examples are minimal — in your project you can (and probably should) add message, path, and traceId for better observability.

---

## How to Run

```
./mvnw spring-boot:run
```

---

## Example curl

401 (no credentials)

```
curl -i http://localhost:8080/auth/user
```

401 (wrong credentials)

```
curl -i -u wrong:wrong http://localhost:8080/auth/user
```

403 (authenticated but not authorized)

```
curl -i -u ann:1234 http://localhost:8080/auth/admin
```

200 (authorized)

```
curl -i -u jack:123 http://localhost:8080/auth/admin
```

---

## Related

- [spring-rest-security-entrypointHandler-lambda](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-lambda) — compact implementation using lambdas in SecurityConfig  
- [spring-rest-security-entrypointHandler-bean](https://github.com/Dmitrii-Russu-Labs-Snippets/spring-rest-security-entrypointHandler-bean) — same handlers registered as beans via factory methods

