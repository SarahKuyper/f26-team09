# PrestoPaws Backend API

# UML Class Diagram

![Class Diagram](./docs/uml-diagram.png)

# API Overview

**Version:** 1.0
**Last Updated:** October 17, 2026

**Base URL:**

- local: http://localhost:8080/api

JSON request bodies are validated. The endpoint tables list applicable error codes: `400 Bad Request` for invalid input, `404 Not Found` for missing resources, and `201 Created` for successful resource creation.

## Provider and Item Management (Implemented by Sarah)

| Method | Path | Purpose | Response code | Error codes | User Story |
| ------ | ---- | ------- | ------------- | ----------- | ---------- |
| `POST` | `/api/providers` | Register a new provider business profile | `201 Created` | `400` | [US-20](./docs/PrestoPaws_Requirements.md#us-20--register-and-manage-business-profile) |
| `GET`  | `/api/providers/{id}` | Read a provider profile | `200 OK` | `404` | [US-20](./docs/PrestoPaws_Requirements.md#us-20--register-and-manage-business-profile) |
| `POST` | `/api/items` | Provider publishes a new item for sale | `201 Created` | `400, 404` | [US-20](./docs/PrestoPaws_Requirements.md#us-20--register-and-manage-business-profile) |
| `GET`  | `/api/items` | Browse all available items (for the shop page) | `200 OK` | — | [US-20](./docs/PrestoPaws_Requirements.md#us-20--register-and-manage-business-profile) |

### Example Provider Request

```http
POST /api/providers
Content-Type: application/json

{
  "name": "Pretty Kitties",
  "email": "contact@prettyKitties.com",
  "password": "supersecretpassword123"
}
